package org.example;

import Model.Account;
import Model.CartItem;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Cart implements Initializable {
    int totalPriceInt = 0;
    @FXML
    public TableColumn<CartItem, Integer> itemCol;
    @FXML
    public TableColumn<CartItem, String> nameCol;
    @FXML
    public TableColumn<CartItem, Double> priceCol;
    @FXML
    public TableColumn<CartItem, Integer> howManyCol;
    @FXML
    public TableColumn<CartItem, Double> totalPriceCol;
    @FXML
    public TableColumn<CartItem, Button> addCol;
    @FXML
    public TableColumn<CartItem, Button> decreaseCol;
    @FXML
    public TableView<CartItem> table;
    public TextField totalPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemCol.setCellValueFactory(new PropertyValueFactory<>("ItemNo"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("Cost"));
        howManyCol.setCellValueFactory(new PropertyValueFactory<>("HowMany"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));
        addCol.setCellValueFactory(new PropertyValueFactory<>("AddButton"));
        decreaseCol.setCellValueFactory(new PropertyValueFactory<>("DecreaseButton"));
        ObservableList<CartItem> data = getInitialTableData();
        table.setItems(data);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ////////////////////Pak NAKONI HA JOZVE CODE///////////////////////////
//        //Listener for selection to go to product page
//        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            if (newSelection != null) {
//                App.setRoot(newSelection.getName());
//            }
//        });

        totalPrice.setText(String.valueOf(totalPriceInt));
    }

    private ObservableList<CartItem> getInitialTableData() {

        List list = new ArrayList();
        int i=1;
        String names = null;
        try {
            App.dataOutputStream.writeUTF(App.token + " " + "getCartKeySet");
            App.dataOutputStream.flush();
            names = App.dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set set;
        for (String name: names.split(" ")){
            try {
                App.dataOutputStream.writeUTF(App.token + " " + name);
                App.dataOutputStream.flush();
                String message = App.dataInputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //get product from server
            App.sendMessageToServer("getProductWithBarcode", name);
            Model.Product product = null;
            try {
                Object obj;
                if ((obj = App.inObject.readObject()) != null) {
                    product = ((Product) obj);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (product != null) {
                CartItem cartItem = new CartItem(Product.getProductWithBarcode(name));
                cartItem.setItemNo(i);
                App.sendMessageToServer("getCart", "");
                HashMap<String, Integer> cart = null;
                try {
                     cart = ((HashMap<String, Integer>)App.inObject.readObject());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                cartItem.setHowMany(cart.get(name));
                cartItem.setTotalPrice(cartItem.getHowMany() * cartItem.getPrice());
                list.add(cartItem);
                totalPriceInt += cartItem.getHowMany() * cartItem.getPrice();
                i++;
            }
        }

        //just for test
        ArrayList<String> tags = new ArrayList<>();
        ArrayList<String> sellers = new ArrayList<>();

        ObservableList<CartItem> data = FXCollections.observableArrayList(list);

        return data;
    }

    public void pay(ActionEvent actionEvent) {
        try {
            App.setRoot("pay");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToAccountPage(ActionEvent actionEvent) throws IOException {
        App.sendMessageToServer("getCurrentAccount", "");
        Account account = null;
        try {
             account = ((Account)App.inObject.readObject());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (account.equals(App.defaultCustomer)) {
            LoginCreate.setBeforeRoot("main");
            App.setRoot("login-create");
        } else {
            switch (account.getUserName().substring(0, 2)) {
                case "man":
                    App.setRoot("manager");
                    break;
                case "cus":
                    App.setRoot("customer");
                    break;
                case "sel":
                    App.setRoot("seller");
                    break;
            }
        }
    }
}
