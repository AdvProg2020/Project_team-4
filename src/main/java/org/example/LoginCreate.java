package org.example;

import Control.Controller;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginCreate {
    private static String beforeRoot;
    @FXML
    private TextField userSign;
    @FXML
    private PasswordField passSign;
    @FXML
    private Button createButton;
    @FXML
    private TextField userReq;
    @FXML
    private PasswordField passReq;
    @FXML
    private Button reqButton;
    @FXML
    private TextField userLogin;
    @FXML
    private PasswordField passLogin;
    @FXML
    private Button loginButton;
    @FXML
    private Button logoutButton;


    EventHandler createButtonHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            if (checkInfoEntrance(userSign, createButton, passSign, createButtonHandler)) return;
            int result = Controller.getOurController().controllerNewAccount("customer", userSign.getText(), passSign.getText());
            showResult(result, "registered");
        }
    };

    private boolean checkInfoEntrance(TextField userSign, Button createButton, TextField passSign, EventHandler eventHandler) {
        if (userSign.getText() == null || userSign.getText().trim().equalsIgnoreCase("")) {
            checkEntrance("username");
            createButton.setOnAction(eventHandler);
            return true;
        }
        if (passSign.getText() == null || passSign.getText().trim().equalsIgnoreCase("")){
            checkEntrance("password");
            createButton.setOnAction(eventHandler);
            return true;
        }
        return false;
    }

    EventHandler requestButtonHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            if (checkInfoEntrance(userReq, reqButton, passReq, requestButtonHandler)) return;
            int result = Controller.getOurController().controllerNewAccount("seller", userReq.getText(), passReq.getText());
            showResult(result, "request sent");
        }
    };

    private void showResult(int result, String error) {
        if (result == 4) {
            Alert a = new Alert(Alert.AlertType.NONE);

            // set alert type
            a.setAlertType(Alert.AlertType.WARNING);

            a.setContentText("username token already");
            // show the dialog
            a.show();
        } else {
            Alert a = new Alert(Alert.AlertType.NONE);

            // set alert type
            a.setAlertType(Alert.AlertType.WARNING);

            a.setContentText(error + " successfully");
            // show the dialog
            a.show();
        }
    }

    EventHandler loginButtonHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            if (checkInfoEntrance(userLogin, loginButton, passLogin, loginButtonHandler)) return;
            int result = Controller.getOurController().controllerLogin(userLogin.getText().trim(), passLogin.getText().trim());
            System.out.println(result);
            if (result == 2 || result == 3) {
                Alert a = new Alert(Alert.AlertType.NONE);

                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);

                a.setContentText("username or passWord is wrong");
                // show the dialog
                a.show();
                return;
            } else if (result == 1){
                try {
                    logoutButton.setDisable(false);
                    App.setRoot(beforeRoot);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    EventHandler logoutButtonHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            int result = Controller.getOurController().logout();
            if (result == 1) {
                Alert a = new Alert(Alert.AlertType.NONE);

                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);

                a.setContentText("you're not logged in");
                // show the dialog
                a.show();
            } else {
                Alert a = new Alert(Alert.AlertType.NONE);

                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);

                a.setContentText("logged out successfully");
                // show the dialog
                a.show();
            }
        }
    };

    public void initialize(){
        createButton.setOnAction(createButtonHandler);
        reqButton.setOnAction(requestButtonHandler);
        loginButton.setOnAction(loginButtonHandler);
        logoutButton.setOnAction(logoutButtonHandler);
    }


    public void checkEntrance(String error) {
        Alert a = new Alert(Alert.AlertType.NONE);

        // set alert type
        a.setAlertType(Alert.AlertType.WARNING);

        a.setContentText("Enter "+ error+ " first");
        // show the dialog
        a.show();
    }

    @FXML
    public void createCustomer() {
        if (!(userSign.getText() == null) || userSign.getText().trim().equalsIgnoreCase("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("enter username first");
            alert.show();
            return;
        }
        else if (!(passSign.getText() == null) || passSign.getText().trim().equalsIgnoreCase("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("enter username first");
            alert.show();
            return;
        }
    }

    public static void setBeforeRoot(String root) {
        beforeRoot = root;
    }
}
