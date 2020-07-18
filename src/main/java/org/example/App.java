package org.example;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;


public class App extends Application {

    private static Scene scene;
    private boolean isFirstManagerCreatedOrNot;
    private static Stage stage;
    public static Model.Customer defaultCustomer = new Model.Customer("default", String.valueOf(123));
    private static Socket socket;
    public  static DataInputStream dataInputStream;
    public  static DataOutputStream dataOutputStream;
    public static ObjectOutputStream outObject;
    public static ObjectInputStream inObject;
    public static String token;




    @Override
    public void start(Stage stage) throws IOException {
        socket = new Socket("localhost", 8888);
        try {
            outObject = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            inObject = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = "music\\backgroundMusic.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(10);
        this.stage = stage;
        sendMessageToServer("setCurrentAccount", "");
        sendObjectToServer(defaultCustomer);

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
//                System.out.println(System.getProperty("user.dir") + "\\" + Manager.class);
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

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage(){
        return stage;
    }

    public static void sendMessageToServer(String typeOfRequest, String contentOfRequest) {
        try {
            dataOutputStream.writeUTF(token + " " + typeOfRequest + " " + contentOfRequest);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendObjectToServer(Object obj) {
        try {
            outObject.writeObject(obj);
            outObject.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}