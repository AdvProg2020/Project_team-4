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

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static Model.Account.getAccountWithName;

public class Category implements Initializable {

    @FXML
    public TextField name;
    @FXML
    public TextField tags;
    @FXML
    public TextField subCategories;
    @FXML
    public TextField products;
    @FXML
    public Button add;
    @FXML
    public Button remove;
    @FXML
    public TableView<Model.Category> table;
    @FXML
    public TableColumn nameColumn;
    @FXML
    public TableColumn tagsColumn;
    @FXML
    public TableColumn productsColumn;
    @FXML
    public TableColumn subCategoriesColumn;

    public void add(ActionEvent actionEvent) {
        if (checkInfoEntrance(name, tags))return;
        if (checkInfoEntrance(name, products))return;
        ArrayList<String> subCategoriesArray = new ArrayList<>();
        for (String string: subCategories.getText().trim().split(" ")) {
            Model.Category category = Model.Category.getCategoryByName(string);
            if (category!=null) {
                subCategoriesArray.add(string);
            }
        }
        ArrayList<String> tagsArray = new ArrayList<>(Arrays.asList(tags.getText().trim().split(" ")));
        ArrayList<String> productsArray = new ArrayList<>(Arrays.asList(products.getText().trim().split(" ")));
        Model.Category result = Controller.getOurController().createCategory(name.getText().trim(), subCategoriesArray, tagsArray, productsArray);
        System.out.println(result);
        if (result != null) {
            table.getItems().add(result);
        }
    }

    public void remove(ActionEvent actionEvent) {
        Model.Category category = Model.Category.getCategoryByName(table.getSelectionModel().getSelectedItem().getName());
        boolean result =false;
        if (category != null) {
            result = Controller.getOurController().removeCategory(category.getName());
        }
        if (result) {
            ObservableList<Model.Category> allProducts;
            ObservableList<Model.Category> singleProduct;
            allProducts = table.getItems();
            singleProduct = table.getSelectionModel().getSelectedItems();
            singleProduct.forEach(allProducts::remove);
        }
        System.out.println(result);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Model.Category> categories = Controller.getOurController().showCategories();
        ObservableList<Model.Category> categoriesObserveAbleList = FXCollections.observableArrayList(categories);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tagsColumn.setCellValueFactory(new PropertyValueFactory<>("SubCategories"));
        subCategoriesColumn.setCellValueFactory(new PropertyValueFactory<>("Products"));
        productsColumn.setCellValueFactory(new PropertyValueFactory<>("Tags"));
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setItems(categoriesObserveAbleList);
        table.setEditable(true);
    }

    public void changeName(TableColumn.CellEditEvent cellEditEvent) {
        Model.Category category = table.getSelectionModel().getSelectedItem();
        category.setName(cellEditEvent.getNewValue().toString());
        SaveAndLoad.getSaveAndLoad().saveGenerally();
    }

    public void changeTags(TableColumn.CellEditEvent cellEditEvent) {
        Model.Category category = table.getSelectionModel().getSelectedItem();
        ArrayList<String> tags = new ArrayList<>(Arrays.asList(cellEditEvent.getNewValue().toString().split(" ")));
        category.setTags(tags);
        SaveAndLoad.getSaveAndLoad().saveGenerally();
    }

    public void changeProducts(TableColumn.CellEditEvent cellEditEvent) {
        Model.Category category = table.getSelectionModel().getSelectedItem();
        ArrayList<String> products = new ArrayList<>(Arrays.asList(cellEditEvent.getNewValue().toString().split(" ")));
        category.setProducts(products);
        SaveAndLoad.getSaveAndLoad().saveGenerally();
    }

    public void changeSubCategories(TableColumn.CellEditEvent cellEditEvent) {
        Model.Category category = table.getSelectionModel().getSelectedItem();
        ArrayList<String> subCategories = new ArrayList<>(Arrays.asList(cellEditEvent.getNewValue().toString().split(" ")));
        category.setSubCategories(subCategories);
        SaveAndLoad.getSaveAndLoad().saveGenerally();
    }
}
