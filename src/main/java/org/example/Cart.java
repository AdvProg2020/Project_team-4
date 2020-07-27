package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.EOFException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Cart implements Initializable {
    int totalPriceInt = 0;
    @FXML
    public TableColumn<Model.CartItem, Integer> itemCol;
    @FXML
    public TableColumn<Model.CartItem, String> nameCol;
    @FXML
    public TableColumn<Model.CartItem, Double> priceCol;
    @FXML
    public TableColumn<Model.CartItem, Integer> howManyCol;
    @FXML
    public TableColumn<Model.CartItem, Double> totalPriceCol;
    @FXML
    public TableColumn<Model.CartItem, Button> addCol;
    @FXML
    public TableColumn<Model.CartItem, Button> decreaseCol;
    @FXML
    public TableView<Model.CartItem> table;
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
        ObservableList<Model.CartItem> data = getInitialTableData();
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

    private ObservableList<Model.CartItem> getInitialTableData() {

        List list = new ArrayList();
        int i=1;
        String names = null;
        try {
            App.sendMessageToServer("getCartKeySet", "");
            names = App.dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("here");
        System.out.println(names);
//        assert names != null;
        if(!names.equals(""))
            for (String name: names.split(" ")){
//            try {
//                App.dataOutputStream.writeUTF(App.token + " " + name);
//                App.dataOutputStream.flush();
//                String message = App.dataInputStream.readUTF();
//            } catch (EOFException e){
//                e.printStackTrace();
//            }catch (IOException e) {
//                e.printStackTrace();
//            }

            //get product from server
            App.sendMessageToServer("getProductWithBarcode", name);
            Model.Product product = null;
            try {
                Object obj;
                if ((obj = App.inObject.readObject()) != null) {
                    product = ((Model.Product) obj);
                }
            }catch (EOFException e){
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (product != null) {
                Model.CartItem cartItem = new Model.CartItem(product);
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

        ObservableList<Model.CartItem> data = FXCollections.observableArrayList(list);
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
        String type = App.dataInputStream.readUTF();
        Model.Account account = null;
        try {
             account = ((Model.Account)App.inObject.readObject());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (account.equals(App.defaultCustomer)) {
            LoginCreate.setBeforeRoot("main");
            App.setRoot("login-create");
        } else {
            switch (type) {
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

    public void back(ActionEvent actionEvent) throws IOException {
        App.setRoot("main");
    }
}
