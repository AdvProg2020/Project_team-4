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
    public TableView<Model.Account> table;
    public Button remove;

    @FXML
    public void add(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        if (checkInfoEntrance(user, pass))return;
        App.sendMessageToServer("controllerCreateNewManagerAccountFromManager", user.getText().trim() + " " + pass.getText().trim());
        int result = (int) App.inObject.readObject();
//                Controller.getOurController().controllerCreateNewManagerAccountFromManager(user.getText().trim(), pass.getText().trim());
        System.out.println(result);
        if (result == 1) {
            App.sendMessageToServer("getAccountWithName", user.getText().trim());
            Model.Account account = (Model.Account) App.inObject.readObject();
            table.getItems().add(account);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] users1 = null;
        App.sendMessageToServer("getusers", "");
        App.sendObjectToServer(Manager.class);
        try {
            users1 = (String[]) App.inObject.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<String> userNames = Arrays.asList(users1);
        List<Model.Account> users = new ArrayList<>();
        for (String userName: userNames) {
            App.sendMessageToServer("getAccountWithName", userName);
            Model.Account account = null;
            try {
                account = (Model.Account) App.inObject.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            users.add(account);
        }
        String[] users2 = null;
        App.sendMessageToServer("getusers", "");
        App.sendObjectToServer(Customer.class);
        try {
            users2 = (String[]) App.inObject.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        userNames = Arrays.asList(users2);
        for (String userName: userNames) {
            App.sendMessageToServer("getAccountWithName", userName);
            Model.Account account = null;
            try {
                account = (Model.Account) App.inObject.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            users.add(account);
        }
        String[] users3 = null;
        App.sendMessageToServer("getusers", "");
        App.sendObjectToServer(Seller.class);
        try {
            users3 = (String[]) App.inObject.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        userNames = Arrays.asList(users3);
        for (String userName: userNames) {
            App.sendMessageToServer("getAccountWithName", userName);
            Model.Account account = null;
            try {
                account = (Model.Account) App.inObject.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            users.add(account);
        }
        ObservableList<Model.Account> customers = FXCollections.observableArrayList(users);
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
    public void remove(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        App.sendMessageToServer("getAccountWithName", table.getSelectionModel().getSelectedItem().getUserName());
        Model.Account account = (Model.Account) App.inObject.readObject();
//                getAccountWithName(table.getSelectionModel().getSelectedItem().getUserName());
        int result = 0;
        if (account != null) {
            App.sendMessageToServer("controllerDeleteAnUser", account.getUserName());
            result = (int) App.inObject.readObject();
//                    Controller.getOurController().controllerDeleteAnUser(account.getUserName());
        }
        if (result == 1) {
            ObservableList<Model.Account> allProducts;
            ObservableList<Model.Account> singleProduct;
            allProducts = table.getItems();
            singleProduct = table.getSelectionModel().getSelectedItems();
            singleProduct.forEach(allProducts::remove);
        }
        System.out.println(result);
    }

    public void switchToAccountPage(ActionEvent actionEvent) throws IOException {
        Category.getCurrentAccountInClient();
    }
}
