package org.example;

import Control.Controller;
import Model.CartItem;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Cart implements Initializable {
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
    }

    private ObservableList<CartItem> getInitialTableData() {

        List list = new ArrayList();
        int i=1;
        for (String name: ((Model.Customer)(Controller.getOurController().getLoggedInAccount())).getCart().keySet()) {
            CartItem cartItem = new CartItem(Product.getProductWithName(name));
            cartItem.setItemNo(i);
            cartItem.setHowMany(((Model.Customer)(Controller.getOurController().getLoggedInAccount())).getCart().get(name));
            cartItem.setTotalPrice(cartItem.getHowMany() * cartItem.getPrice());
            list.add(cartItem);
            i++;
        }

        //just for test
        ArrayList<String> tags = new ArrayList<>();
        ArrayList<String> sellers = new ArrayList<>();
        Product product = new Product( "name", "company", 1000, "category", "description", 5, tags, sellers);
        CartItem cartItem = new CartItem(product);
        cartItem.setItemNo(i);
        cartItem.setHowMany(10);
        cartItem.setTotalPrice(cartItem.getHowMany() * cartItem.getPrice());
        list.add(cartItem);

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

}
