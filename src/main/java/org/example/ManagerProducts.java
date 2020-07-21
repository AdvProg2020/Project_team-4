package org.example;

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

    Model.Account account = null;
    String type;


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
            type = App.dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            account = ((Model.Account)App.inObject.readObject());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        ArrayList<Model.Product> products = null;
        App.sendMessageToServer("getAllProducts", "");
        try {
            products = (ArrayList<Model.Product>) App.inObject.readObject();
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
