package org.example;

import Model.Manager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Initialization {
    @FXML
    private TextField initUserName;
    private String userName;
    @FXML
    private TextField initPassWord;
    private String passWord;
    @FXML
    private Button initButton;

    EventHandler initButtonHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            System.out.println(userName);
            System.out.println(passWord);
            if (userName == null || userName.equalsIgnoreCase("")) {
                checkEntrance(initButton, "username");
                initButton.setOnAction(initButtonHandler);
                return;
            }
            if (passWord == null || passWord.equalsIgnoreCase("")){
                checkEntrance(initButton, "password");
                initButton.setOnAction(initButtonHandler);
                return;
            }
            Manager.addANewManager(initUserName.getText().trim(), initPassWord.getText().trim(), false);
            try {
                App.setRoot("primary");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    public void initialize(){
       initUserName.textProperty().addListener(new ChangeListener<String>() {
           @Override
           public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
               if (!(t1==null) || !t1.trim().equalsIgnoreCase("")) {
                   userName = t1.trim();
               }
           }
       });
        initPassWord.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!(t1==null) || !t1.trim().equalsIgnoreCase("")) {
                    passWord = t1.trim();
                }
            }
        });
        initButton.setOnAction(initButtonHandler);
    }


    @FXML
    public void init() throws IOException {
    }

    private void checkEntrance(Button button, String error) {
        Alert a = new Alert(Alert.AlertType.NONE);

        // set alert type
        a.setAlertType(Alert.AlertType.WARNING);

        a.setContentText("Enter "+ error+ " first");
        // show the dialog
        a.show();
    }
}
