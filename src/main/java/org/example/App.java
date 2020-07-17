package org.example;

import Control.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class App {

    public static void main(String[] args) {
        new ClientImpl().run();
    }

    static class ClientImpl extends Application{
        private static Scene scene;
        private boolean isFirstManagerCreatedOrNot;
        private static Stage stage;
        public static Model.Customer defaultCustomer;
        Scanner scanner;
        DataOutputStream dataOutputStream;
        DataInputStream dataInputStream;
        Socket socket;
        boolean hasSignedIn = false;

        private void requestSingIn() throws IOException {
            String username = scanner.nextLine();
            System.out.println("Enter Password:");
            String password = scanner.nextLine();
            String message = "SignIn" + username + "," + password;
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
            String response = dataInputStream.readUTF();
            if (response.equals("Failure")) {
                System.err.println("Incorrect Credentials! Please Try Again.");
            } else {
                System.out.println("Successfully logged in!");
                hasSignedIn = true;
            }
        }

        private void handleConnection() {
            try {
                scanner = new Scanner(System.in);
                dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                String userInput = "";
                while (!userInput.equalsIgnoreCase("logout")) {
                    while (!hasSignedIn) {
                        requestSingIn();
                    }
                    System.out.println("Enter one of these commands : ViewList, TakeCourse(CourseName), DropCourse(CourseName), MyCourse, Logout");
                    userInput = scanner.nextLine();
                    dataOutputStream.writeUTF(userInput);
                    dataOutputStream.flush();
                    String response = dataInputStream.readUTF();
                    System.out.println(response);
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        @Override
        public void start(Stage stage) throws IOException {

            try {
                socket = new Socket("127.0.0.1", 8888);
                System.out.println("Successfully connected to server!");
                handleConnection();
            } catch (IOException e) {
                System.err.println("Error starting client!");
            }
            initializeScene(stage);
        }

        public void run() {
            try {
                start(getStage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void initializeScene(Stage stage) throws IOException {
            defaultCustomer = new Model.Customer("default", String.valueOf(123));
            String path = "music\\backgroundMusic.mp3";
            Media media = new Media(new File(path).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            mediaPlayer.setCycleCount(10);
            this.stage = stage;
            Controller.getOurController().setLoggedInAccount(defaultCustomer);
            if (checkInitializedOrNot()) {
                scene = new Scene(loadFXML("main"));
            } else {
                scene = new Scene(loadFXML("initialization"));
            }
            stage.setTitle("A D S store");
            stage.setScene(scene);
            stage.show();
        }

        private boolean checkInitializedOrNot() {
            File directory = new File(System.getProperty("user.dir") + "\\" + "class Model.Manager");
            if (directory.isDirectory()) {
                String[] files = directory.list();
                if (files.length > 0) {
                    isFirstManagerCreatedOrNot = true;
                }
                else {
                    isFirstManagerCreatedOrNot = false;
                }
            }
            return isFirstManagerCreatedOrNot;
        }

        static void setRoot(String fxml) throws IOException {
            scene.setRoot(loadFXML(fxml));
        }

        static Parent loadFXML(String fxml) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
            return fxmlLoader.load();
        }

        public static Stage getStage(){
            return stage;
        }
    }

}