package org.example;

import java.io.IOException;

import Control.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    public void switchToAccountSection() throws IOException {
        if (Controller.getOurController().getLoggedInAccount().equals(App.defaultCustomer)) {
            LoginCreate.setBeforeRoot("main");
            App.setRoot("login-create");
            return;
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

    public void switchToProductsPage() {
        try {
            App.setRoot("ProductsPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToOffsPagePage() {
        ProductsPage.calledFromOff = true;
        try {
            App.setRoot("ProductsPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToCart(ActionEvent actionEvent) {
        try {
            App.setRoot("cart");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
