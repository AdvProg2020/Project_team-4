package org.example;

import Model.Account;
import Model.Product;
import Model.Seller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import Control.Controller;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SellersProductPage implements Initializable {
    public TextField descriptionField;
    public TableColumn<Product, String> byersColumn;
    ArrayList<TextField> textFields = new ArrayList<>();

    public TableView<Product> table;
    public TableColumn<Product, String> nameColumn;
    public TableColumn<Product, String> categoryColumn;
    public TableColumn<Product, String> companyColumn;
    public TableColumn<Product, Double> costColumn;
    public TableColumn<Product, Integer> amountColumn;
    public TableColumn<Product, ArrayList<String>> tagsColumn;
    public TextField nameField;
    public TextField categoryField;
    public TextField companyFiled;
    public TextField costField;
    public TextField amountField;
    public TextField tagsField;
    public Button editButton;
    public Button removeButton;

    public void edit(ActionEvent actionEvent) {
        if (nameField.getText() != null && !nameField.getText().trim().equalsIgnoreCase("") && Product.getProductWithBarcode(nameField.getText().trim()) != null) {
            Product.getProductWithBarcode(nameField.getText().trim()).setSellers(((Seller)(Controller.getOurController().getLoggedInAccount())).getUserName());
            ((Seller) Controller.getOurController().getLoggedInAccount()).setProducts(nameField.getText().trim());
            return;
        }
        if (checkInfoEntrance())return;
        ArrayList<String> tagsArray = new ArrayList<>();
        for (String tag: tagsField.getText().trim().split(" ")) {
            tagsArray.add(tag);
        }
        Controller.createProductRequest(nameField.getText().trim(), companyFiled.getText().trim(), Integer.parseInt(costField.getText().trim()), categoryField.getText().trim(), descriptionField.getText().trim(), Integer.parseInt(amountField.getText().trim()), tagsArray, Controller.getOurController().getLoggedInAccount().getUserName());
    }

    public void remove(ActionEvent actionEvent) {
        ObservableList<Product> selectedItem = table.getSelectionModel().getSelectedItems();
        Controller.getOurController().removeProductFromSellerProducts(selectedItem.get(0).getProductBarcode());
        ObservableList<Product> allProducts;
        ObservableList<Product> singleProduct;
        allProducts = table.getItems();
        singleProduct = table.getSelectionModel().getSelectedItems();
        singleProduct.forEach(allProducts::remove);
    }

    private boolean checkInfoEntrance() {
        for (TextField textField: textFields) {
            if (textField.getText() == null || textField.getText().trim().equalsIgnoreCase("")) {
                checkEntrance("empty fields");
                return true;
            }
        }
        return false;
    }

    public void checkEntrance(String error) {
        Alert a = new Alert(Alert.AlertType.NONE);

        // set alert type
        a.setAlertType(Alert.AlertType.WARNING);

        a.setContentText("Enter "+ error+ " first");
        // show the dialog
        a.show();
    }

    private void getEditAbleTextFields() {
        textFields.add(nameField);
        textFields.add(categoryField);
        textFields.add(companyFiled);
        textFields.add(costField);
        textFields.add(amountField);
        textFields.add(tagsField);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getEditAbleTextFields();
        ArrayList<String> productsNames = new ArrayList(((Seller)Controller.getOurController().getLoggedInAccount()).getProducts());
        ArrayList<Product> products = new ArrayList<>();
        for (String name: productsNames) {
            products.add(Product.getProductWithBarcode(name));
        }
        ObservableList<Product> observableList = FXCollections.observableArrayList(products);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("AmountOfExist"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("Cost"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
        tagsColumn.setCellValueFactory(new PropertyValueFactory<>("Tags"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("Company"));
        byersColumn.setCellValueFactory(new PropertyValueFactory<>("Byers"));


        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setItems(observableList);
    }

    public void switchToAccountPage(ActionEvent actionEvent) throws IOException {
        if (Controller.getOurController().getLoggedInAccount().equals(App.defaultCustomer)) {
            LoginCreate.setBeforeRoot("main");
            App.setRoot("login-create");
        } else {
            switch (Controller.getOurController().getLoggedInAccount().getClass().toString()) {
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

    public void updateFields(MouseEvent mouseEvent) {
        Product product = table.getSelectionModel().getSelectedItems().get(0);
        String name  = product.getProductBarcode();

        nameField.setText(Product.getProductWithBarcode(name).getName());
        categoryField.setText(Product.getProductWithBarcode(name).getCategory());
        companyFiled.setText(Product.getProductWithBarcode(name).getCompany());
        costField.setText(String.valueOf(Product.getProductWithBarcode(name).getCost()));
        amountField.setText(String.valueOf(Product.getProductWithBarcode(name).getAmountOfExist()));
        tagsField.setText(String.valueOf(Product.getProductWithBarcode(name).getTags()));
        descriptionField.setText(String.valueOf(Product.getProductWithBarcode(name).getDescription()));

    }
}
