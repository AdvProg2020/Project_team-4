package org.example;

import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManagerProducts implements Initializable {

    Model.Account account;


    public TextField descriptionField;
    ArrayList<TextField> textFields = new ArrayList<>();

    public TableView<Model.Product> table;
    public TableColumn<Model.Product, String> nameColumn;
    public TableColumn<Model.Product, String> categoryColumn;
    public TableColumn<Model.Product, String> companyColumn;
    public TableColumn<Model.Product, Double> costColumn;
    public TableColumn<Model.Product, Integer> amountColumn;
    public TableColumn<Model.Product, ArrayList<String>> tagsColumn;
    public Button removeButton;

    public void remove(ActionEvent actionEvent) {
        ObservableList<Model.Product> selectedItem = table.getSelectionModel().getSelectedItems();
//        Controller.getOurController().controllerRemoveProduct(selectedItem.get(0).getProductBarcode());
        App.sendMessageToServer("controllerRemoveProduct", selectedItem.get(0).getProductBarcode());
        ObservableList<Model.Product> allProducts;
        ObservableList<Model.Product> singleProduct;
        allProducts = table.getItems();
        singleProduct = table.getSelectionModel().getSelectedItems();
        singleProduct.forEach(allProducts::remove);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        App.sendMessageToServer("getCurrentAccount", "");
        account = null;
        try {
            account = ((Model.Account)App.inObject.readObject());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        ArrayList<Model.Product> products = null;
        App.sendMessageToServer("getAllProducts", "");
        try {
            products = (ArrayList<Product>) App.inObject.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        Product.getAllProducts();
        ObservableList<Model.Product> observableList = FXCollections.observableArrayList(products);

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
        if (account.equals(App.defaultCustomer)) {
            LoginCreate.setBeforeRoot("main");
            App.setRoot("login-create");
        } else {
            switch (account.getUserName().substring(0, 2)) {
                case "man":
                    App.setRoot("manager");
                    break;
                case "cus":
                    App.setRoot("customer");
                    break;
                case "sel":
                    App.setRoot("seller");
                    break;
            }
        }
    }
}
