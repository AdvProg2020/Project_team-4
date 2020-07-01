package org.example;

import Control.Controller;
import Model.*;
import Model.Customer;
import View.Menu.MainMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.CharArrayReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class App extends Application {

    private static Scene scene;
    private boolean isFirstManagerCreatedOrNot;
    public static Customer defaultCustomer = new Customer("default", String.valueOf(123));

    @Override
    public void start(Stage stage) throws IOException {
        Controller.getOurController().setLoggedInAccount(defaultCustomer);
        handleData();
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

    private void handleData() {
        File file1 = new File(String.valueOf("class Model.Customer"));
        file1.mkdir();
        File file2 = new File("class Model.Manager");
        file2.mkdir();
        File file3 = new File("class Model.Seller");
        file3.mkdir();
        File file4 = new File("class java.util.ArrayList");
        file4.mkdirs();
        Controller.readOffCodesFromFile();
        Controller.readRequestsFromFile();
        Controller.readOffsFromFile();
        Controller.readProductsFromFile();
        Controller.readCategoriesFromFile();
//        MainMenu mainMenu = new MainMenu();
//        mainMenu.execute();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}