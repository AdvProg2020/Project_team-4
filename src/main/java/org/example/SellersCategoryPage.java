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
import java.util.ResourceBundle;

public class SellersCategoryPage implements Initializable {

    @FXML
    public TableView<Model.Category> table;
    @FXML
    public TableColumn<Model.Category, String> nameColumn;
    @FXML
    public TableColumn<Model.Category, String> tagsColumn;
    @FXML
    public TableColumn<Model.Category, String> productsColumn;
    @FXML
    public TableColumn<Model.Category, String> subCategoriesColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        App.sendMessageToServer("showCategories", "");

        ArrayList<Model.Category> categories = null;
        try {
            categories = (ArrayList<Model.Category>) App.inObject.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        Controller.getOurController().showCategories();
        ObservableList<Model.Category> categoriesObserveAbleList = FXCollections.observableArrayList(categories);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        subCategoriesColumn.setCellValueFactory(new PropertyValueFactory<>("SubCategories"));
        productsColumn.setCellValueFactory(new PropertyValueFactory<>("Products"));
        tagsColumn.setCellValueFactory(new PropertyValueFactory<>("Tags"));
        table.setItems(categoriesObserveAbleList);
    }


    public void switchtoAccountPage(ActionEvent actionEvent) throws IOException {
        App.sendMessageToServer("getCurrentAccount", "");
        Model.Account account = null;
        String type = App.dataInputStream.readUTF();
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
}
