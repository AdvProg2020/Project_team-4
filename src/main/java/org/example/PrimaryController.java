package org.example;

import java.io.IOException;

import Control.Controller;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToAccountSection() throws IOException {
        if (Controller.getOurController().getLoggedInAccount() == null) {
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
