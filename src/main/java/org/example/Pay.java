package org.example;

import Control.Controller;
import Model.Off;
import Model.Seller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Pay implements Initializable {

    ArrayList<TextField> textFields = new ArrayList<>();

    public TextField addressField;
    public TextField phoneField;
    public TextField offCodeField;
    public Button payButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void checkPhone(ActionEvent actionEvent) {
        if (phoneField.getText()!=null && !phoneField.getText().trim().equalsIgnoreCase("")) {
            offCodeField.setVisible(true);
            payButton.setVisible(true);
        }
    }

    public void checkAddr(ActionEvent actionEvent) {
        if (addressField.getText()!=null && !addressField.getText().trim().equalsIgnoreCase("")) {
            offCodeField.setVisible(true);
            payButton.setVisible(true);
        }
    }

    public void pay(ActionEvent actionEvent) {
        if (offCodeField.getText() != null) {
            Controller.getOurController().pay(offCodeField.getText().trim());
        } else {
            Controller.getOurController().pay("");
        }
    }

    public void switchToAccountPage(ActionEvent actionEvent) throws IOException {
        if (Controller.getOurController().getLoggedInAccount() == null) {
            LoginCreate.setBeforeRoot("main");
            App.setRoot("login-create");
        } else {
            switch (Controller.getOurController().getLoggedInAccount().getClass().toString()) {
                case "class Model.Manager":
                    App.setRoot("manager");
                    break;
                case "class Model.Customer":
                    App.setRoot("customer");
                    break;
                case "class Model.Seller":
                    App.setRoot("seller");
                    break;
            }
        }
    }
}
