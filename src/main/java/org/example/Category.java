package org.example;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

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
    public TableColumn<Model.Category, String> nameColumn;
    @FXML
    public TableColumn<Model.Category, String> tagsColumn;
    @FXML
    public TableColumn<Model.Category, String> productsColumn;
    @FXML
    public TableColumn<Model.Category, String> subCategoriesColumn;

    public void add(ActionEvent actionEvent) throws InterruptedException, IOException, ClassNotFoundException {
        if (checkInfoEntrance(name, tags))return;
        if (checkInfoEntrance(name, products))return;
        ArrayList<String> subCategoriesArray = new ArrayList<>();
        for (String string: subCategories.getText().trim().split(" ")) {
            subCategoriesArray.add(string);
        }
        ArrayList<String> tagsArray = new ArrayList<>(Arrays.asList(tags.getText().trim().split(" ")));
        ArrayList<String> productsArray = new ArrayList<>(Arrays.asList(products.getText().trim().split(" ")));
        App.sendMessageToServer("createCategory", name.getText().trim());
        App.sendObjectToServer(subCategoriesArray);
        App.sendObjectToServer(tagsArray);
        App.sendObjectToServer(productsArray);
        Model.Category result = null;
        try {
            result = ((Model.Category)App.inObject.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (result != null) {
            table.getItems().add(result);
        }
    }

    public void remove(ActionEvent actionEvent) throws IOException, InterruptedException, ClassNotFoundException {
        App.sendMessageToServer("getCategoryByName", table.getSelectionModel().getSelectedItem().getName());
        Model.Category category = (Model.Category) App.inObject.readObject();
        boolean result =false;
        if (category != null) {
            App.sendMessageToServer("removeCategory", category.getName());
            result = (boolean) App.inObject.readObject();
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
        App.sendMessageToServer("showCategories", "");
        ArrayList<Model.Category> categories = null;
        try {
            categories = (ArrayList<Model.Category>) App.inObject.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<Model.Category> categoriesObserveAbleList = FXCollections.observableArrayList(categories);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        subCategoriesColumn.setCellValueFactory(new PropertyValueFactory<>("SubCategories"));
        productsColumn.setCellValueFactory(new PropertyValueFactory<>("Products"));
        tagsColumn.setCellValueFactory(new PropertyValueFactory<>("Tags"));
        table.setItems(categoriesObserveAbleList);
        table.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        subCategoriesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        productsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tagsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void changeName(TableColumn.CellEditEvent<Model.Category, String> cellEditEvent) throws InterruptedException, IOException, ClassNotFoundException {
        App.sendMessageToServer("getCategoryByName", table.getSelectionModel().getSelectedItem().getName());
        Thread.currentThread().wait(100);
        Model.Category category = (Model.Category) App.inObject.readObject();
        category.setName(cellEditEvent.getNewValue().toString());
        ////////////Also for the server
        App.sendMessageToServer("setNameOfCategory", cellEditEvent.getNewValue());
        App.sendObjectToServer(category);


        ////SAVED IN THE SERVER
//        SaveAndLoad.getSaveAndLoad().saveGenerally();
    }

    public void changeTags(TableColumn.CellEditEvent<Model.Category, String> cellEditEvent) throws InterruptedException, IOException, ClassNotFoundException {
        App.sendMessageToServer("getCategoryByName", table.getSelectionModel().getSelectedItem().getName());
        Thread.currentThread().wait(100);
        Model.Category category = (Model.Category) App.inObject.readObject();ArrayList<String> tags = new ArrayList<>(Arrays.asList(cellEditEvent.getNewValue().toString().split(" ")));
        category.setTags(tags);
        App.sendObjectToServer(tags);
        App.sendMessageToServer("setTagsOfCategory", cellEditEvent.getNewValue());
        App.sendObjectToServer(category);
    }

    public void changeProducts(TableColumn.CellEditEvent<Model.Category, String> cellEditEvent) throws InterruptedException, IOException, ClassNotFoundException {
        App.sendMessageToServer("getCategoryByName", table.getSelectionModel().getSelectedItem().getName());
        Thread.currentThread().wait(100);
        Model.Category category = (Model.Category) App.inObject.readObject();ArrayList<String> tags = new ArrayList<>(Arrays.asList(cellEditEvent.getNewValue().toString().split(" ")));
        ArrayList<String> products = new ArrayList<>(Arrays.asList(cellEditEvent.getNewValue().toString().split(" ")));
        category.setProducts(products);
        App.sendObjectToServer(products);
        App.sendMessageToServer("setProductsOfCategory", cellEditEvent.getNewValue());
        App.sendObjectToServer(category);
    }

    public void changeSubCategories(TableColumn.CellEditEvent<Model.Category, String> cellEditEvent) throws InterruptedException, IOException, ClassNotFoundException {
        App.sendMessageToServer("getCategoryByName", table.getSelectionModel().getSelectedItem().getName());
        Thread.currentThread().wait(100);
        Model.Category category = (Model.Category) App.inObject.readObject();ArrayList<String> tags = new ArrayList<>(Arrays.asList(cellEditEvent.getNewValue().toString().split(" ")));
        ArrayList<String> subCategories = new ArrayList<>(Arrays.asList(cellEditEvent.getNewValue().toString().split(" ")));
        category.setSubCategories(subCategories);
        App.sendObjectToServer(subCategories);
        App.sendMessageToServer("setSubCategoriesOfCategory", cellEditEvent.getNewValue());
        App.sendObjectToServer(category);
    }

    public void switchtoAccountPage(ActionEvent actionEvent) throws IOException {
        getCurrentAccountInClient();
    }

    static void getCurrentAccountInClient() throws IOException {
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
