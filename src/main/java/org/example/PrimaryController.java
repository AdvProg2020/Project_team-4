package org.example;

import java.io.IOException;

import Control.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController {

    private App.ClientImpl clientImpl;

    public void setClientImpl(App.ClientImpl clientImpl) {
        this.clientImpl = clientImpl;
    }

    @FXML
    public void switchToAccountSection() throws IOException {
        if (Controller.getOurController().getLoggedInAccount().equals(App.defaultCustomer)) {
            LoginCreate.setBeforeRoot("main");
            clientImpl.setRoot("login-create");
            return;
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

    public void switchToProductsPage() {
        try {
            clientImpl.setRoot("ProductsPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToOffsPagePage() {
        ProductsPage.calledFromOff = true;
        try {
            clientImpl.setRoot("ProductsPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToCart(ActionEvent actionEvent) {
        try {
            clientImpl.setRoot("cart");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
