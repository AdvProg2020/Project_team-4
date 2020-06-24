package org.example;

import Control.Controller;
import Model.*;
import Model.Customer;
import Model.Manager;
import Model.Seller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static Model.Account.getAccountWithName;

public class UsersManaging implements Initializable {

    @FXML
    public TableColumn<Model.Account, String> userName;
    @FXML
    public TextField user;
    @FXML
    public TextField pass;
    @FXML
    public Button addButton;
    @FXML
    public TableView<Account> table;
    public Button remove;

    @FXML
    public void add(ActionEvent actionEvent) {
        if (checkInfoEntrance(user, pass))return;
        int result = Controller.getOurController().controllerCreateNewManagerAccountFromManager(user.getText().trim(), pass.getText().trim());
        System.out.println(result);
        if (result == 1) {
            table.getItems().add(Account.getAccountWithName(user.getText().trim()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> userNames = Arrays.asList(Controller.getOurController().getusers(Manager.class));
        List<Account> users = new ArrayList<>();
        for (String userName: userNames) {
            Account account = getAccountWithName(userName);
            users.add(account);
        }
        userNames = Arrays.asList(Controller.getOurController().getusers(Customer.class));
        for (String userName: userNames) {
            Account account = getAccountWithName(userName);
            users.add(account);
        }
        userNames = Arrays.asList(Controller.getOurController().getusers(Seller.class));
        for (String userName: userNames) {
            Account account = getAccountWithName(userName);
            users.add(account);
        }
        ObservableList<Account> customers = FXCollections.observableArrayList(users);
        userName.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setItems(customers);
        table.setEditable(true);
    }

    public void checkEntrance(String error) {
        Alert a = new Alert(Alert.AlertType.NONE);

        // set alert type
        a.setAlertType(Alert.AlertType.WARNING);

        a.setContentText("Enter "+ error+ " first");
        // show the dialog
        a.show();
    }

    private boolean checkInfoEntrance(TextField userSign, TextField passSign) {
        if (userSign.getText() == null || userSign.getText().trim().equalsIgnoreCase("")) {
            checkEntrance("full info");
            return true;
        }
        if (passSign.getText() == null || passSign.getText().trim().equalsIgnoreCase("")){
            checkEntrance("full info");
            return true;
        }
        return false;
    }

    @FXML
    public void remove(ActionEvent actionEvent) {
        Account account = getAccountWithName(table.getSelectionModel().getSelectedItem().getUserName());
        int result = 0;
        if (account != null) {
            result = Controller.getOurController().controllerDeleteAnUser(account.getUserName());
        }
        if (result == 1) {
            ObservableList<Account> allProducts;
            ObservableList<Account> singleProduct;
            allProducts = table.getItems();
            singleProduct = table.getSelectionModel().getSelectedItems();
            singleProduct.forEach(allProducts::remove);
        }
        System.out.println(result);
    }
}
