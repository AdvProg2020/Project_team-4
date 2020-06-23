package org.example;

import Model.Product;
import Model.Seller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import Control.Controller;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SellersProductPage implements Initializable {
    public TextField descriptionField;
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
        if (checkInfoEntrance())return;
        ArrayList<String> tagsArray = new ArrayList<>();
        for (String tag: tagsField.getText().trim().split(" ")) {
            tagsArray.add(tag);
        }
        Controller.createProductRequest(nameField.getText().trim(), companyFiled.getText().trim(), Integer.parseInt(costField.getText().trim()), categoryField.getText().trim(), descriptionField.getText().trim(), Integer.parseInt(amountField.getText().trim()), tagsArray);
    }

    public void remove(ActionEvent actionEvent) {
        ObservableList<Product> selectedItem = table.getSelectionModel().getSelectedItems();
        Controller.getOurController().removeProductFromSellerProducts(selectedItem.get(0).getProductBarcode());
        table.getItems().remove(selectedItem);
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


        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setItems(observableList);
    }
}
