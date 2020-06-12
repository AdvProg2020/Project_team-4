package org.example;

import Control.Controller;
import Model.Manager;
import Model.Seller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginCreate {
    @FXML
    private TextField userSign;
    @FXML
    private TextField passSign;
    @FXML
    private Button createButton;
    @FXML
    private TextField userReq;
    @FXML
    private TextField passReq;
    @FXML
    private Button reqButton;
    @FXML
    private TextField userLogin;
    @FXML
    private TextField passLogin;
    @FXML
    private Button loginButton;

    EventHandler createButtonHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            if (checkInfoEntrance(userSign, createButton, passSign)) return;
            int result = Controller.getOurController().controllerNewAccount("customer", userSign.getText(), passSign.getText());
            showResult(result, "registered");
        }
    };

    private boolean checkInfoEntrance(TextField userSign, Button createButton, TextField passSign) {
        if (userSign.getText() == null || userSign.getText().trim().equalsIgnoreCase("")) {
            checkEntrance("username");
            createButton.setOnAction(createButtonHandler);
            return true;
        }
        if (passSign.getText() == null || passSign.getText().trim().equalsIgnoreCase("")){
            checkEntrance("password");
            createButton.setOnAction(createButtonHandler);
            return true;
        }
        return false;
    }

    EventHandler requestButtonHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            if (checkInfoEntrance(userReq, reqButton, passReq)) return;
            int result = Controller.getOurController().controllerNewAccount(Seller.class.toString(), userReq.getText(), passReq.getText());
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

    EventHandler createuttonHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            if (userSign.getText() == null || userSign.getText().trim().equalsIgnoreCase("")) {
                checkEntrance("username");
                createButton.setOnAction(createButtonHandler);
                return;
            }
            if (passSign.getText() == null || passSign.getText().trim().equalsIgnoreCase("")){
                checkEntrance("password");
                createButton.setOnAction(createButtonHandler);
                return;
            }
            Controller.getOurController().controllerNewAccount(Customer.class.toString(), userSign.getText(), passSign.getText());
            try {
                App.setRoot("primary");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    public void initialize(){
        createButton.setOnAction(createButtonHandler);
        reqButton.setOnAction(requestButtonHandler);
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
}
