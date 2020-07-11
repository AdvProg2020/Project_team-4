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

    private App.ClientImpl clientImpl;

    public void setClientImpl(App.ClientImpl clientImpl) {
        this.clientImpl = clientImpl;
    }

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

    public void pay(ActionEvent actionEvent) throws IOException {
        boolean result;
        if (offCodeField.getText() != null) {
            result = Controller.getOurController().pay(offCodeField.getText().trim());
        } else {
            result = Controller.getOurController().pay("");
        }
        if (result) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("pay was successful");
            alert.show();
            clientImpl.setRoot("customer");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("pay was not successful maybe you have not the enough credit.");
            alert.show();
            clientImpl.setRoot("customer");
        }
    }

    public void switchToAccountPage(ActionEvent actionEvent) throws IOException {
        if (Controller.getOurController().getLoggedInAccount().equals(App.defaultCustomer)) {
            LoginCreate.setBeforeRoot("main");
            clientImpl.setRoot("login-create");
        } else {
            switch (Controller.getOurController().getLoggedInAccount().getClass().toString()) {
                case "class Model.Manager":
                    clientImpl.setRoot("manager");
                    break;
                case "class Model.Customer":
                    clientImpl.setRoot("customer");
                    break;
                case "class Model.Seller":
                    clientImpl.setRoot("seller");
                    break;
            }
        }
    }
}
