package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

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
            if (Product.getProductWithBarcode(name) != null) {
                CartItem cartItem = new CartItem(Product.getProductWithBarcode(name));
                cartItem.setItemNo(i);
                cartItem.setHowMany(((Model.Customer)(Controller.getOurController().getCurrentAccount())).getCart().get(name));
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
        if (Controller.getOurController().getCurrentAccount().equals(App.defaultCustomer)) {
            LoginCreate.setBeforeRoot("main");
            App.setRoot("login-create");
        } else {
            switch (Controller.getOurController().getCurrentAccount().getClass().toString()) {
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
