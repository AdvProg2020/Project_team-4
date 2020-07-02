package org.example;

import Control.Controller;
import Model.Product;
import Model.Seller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManagerProducts implements Initializable {


    public TextField descriptionField;
    ArrayList<TextField> textFields = new ArrayList<>();

    public TableView<Product> table;
    public TableColumn<Product, String> nameColumn;
    public TableColumn<Product, String> categoryColumn;
    public TableColumn<Product, String> companyColumn;
    public TableColumn<Product, Double> costColumn;
    public TableColumn<Product, Integer> amountColumn;
    public TableColumn<Product, ArrayList<String>> tagsColumn;
    public Button removeButton;

    public void remove(ActionEvent actionEvent) {
        ObservableList<Product> selectedItem = table.getSelectionModel().getSelectedItems();
        Controller.getOurController().controllerRemoveProduct(selectedItem.get(0).getProductBarcode());
        ObservableList<Product> allProducts;
        ObservableList<Product> singleProduct;
        allProducts = table.getItems();
        singleProduct = table.getSelectionModel().getSelectedItems();
        singleProduct.forEach(allProducts::remove);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Product> products = Product.getAllProducts();
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
}
