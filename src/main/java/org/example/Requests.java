package org.example;

import Control.Controller;
import Model.*;
import Model.Manager;
import Model.Seller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Requests implements Initializable {

    @FXML
    public TableView<RequestANewSellerAccount> sellerReq;
    @FXML
    public TableView<RequestProduct> productReq;
    @FXML
    public TableView<RequestOff> offReq;
    @FXML
    public Button acceptSeller;
    @FXML
    public Button acceptProduct;
    @FXML
    public Button acceptOff;
    @FXML
    public Button declineSeller;
    @FXML
    public Button declineProduct;
    @FXML
    public Button declineOff;
    @FXML
    public TableColumn<RequestANewSellerAccount, String> sellerUserName;
    @FXML
    public TableColumn<RequestProduct, String> productBarcode;
    @FXML
    public TableColumn<RequestOff, String> offBarcode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sellerUserName.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        productBarcode.setCellValueFactory(new PropertyValueFactory<>("ProductBarcode"));
        offBarcode.setCellValueFactory(new PropertyValueFactory<>("OffBarcode"));
        ObservableList<RequestANewSellerAccount> sellerRequests = getSellerReqs();
        sellerReq.setItems(sellerRequests);
        ObservableList<RequestProduct> productsRequests = getProductReqs();
        productReq.setItems(productsRequests);
        ObservableList<RequestOff> offsRequests = getOffReqs();
        offReq.setItems(offsRequests);
        sellerReq.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        productReq.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        offReq.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private ObservableList<RequestOff> getOffReqs() {
        List list = new ArrayList(Manager.getEditProductsRequests());
        list.add(new RequestOff(RequestType.OFF, new Off("sal, mah, rooz", new ArrayList<>(), "salm, mah,roox", 50)));
        ObservableList observableList = FXCollections.observableArrayList(list);
        return observableList;
    }

    private ObservableList<RequestProduct> getProductReqs() {
        List list = new ArrayList(Manager.getEditOffRequests());
        ArrayList<String> tags = new ArrayList<>();
        ArrayList<String> sellers = new ArrayList<>();
        Product product = new Product( "name", "company", 1000, "category", "description", 5, tags);
        list.add(new RequestProduct(RequestType.PRODUCT, product));
        ObservableList observableList = FXCollections.observableArrayList(list);
        return observableList;
    }

    private ObservableList<RequestANewSellerAccount> getSellerReqs() {
        List list = new ArrayList(Manager.getRegisterSellerAccountRequests());
        list.add(new RequestANewSellerAccount(RequestType.ACCOUNT, "ali", "pass123"));
        ObservableList observableList = FXCollections.observableArrayList(list);
        return observableList;
    }


    public void acceptSeller(ActionEvent actionEvent) {
        Controller.getOurController().acceptRequest((RequestANewSellerAccount) sellerReq.getSelectionModel().getSelectedItem());
        removeFromTableView();

    }

    private void removeFromTableView() {
        ObservableList<RequestANewSellerAccount> allProducts, singleProduct;
        allProducts = sellerReq.getItems();
        singleProduct = sellerReq.getSelectionModel().getSelectedItems();
        singleProduct.forEach(allProducts::remove);
    }

    public void acceptProduct(ActionEvent actionEvent) {
        Controller.getOurController().acceptRequest((RequestProduct) productReq.getSelectionModel().getSelectedItem());
        removePro();
    }

    private void removePro() {
        ObservableList<RequestProduct> allProducts;
        ObservableList<RequestProduct> singleProduct;
        allProducts = productReq.getItems();
        singleProduct = productReq.getSelectionModel().getSelectedItems();
        singleProduct.forEach(allProducts::remove);
    }

    private void removeOff() {
        ObservableList<RequestOff> allProducts;
        ObservableList<RequestOff> singleProduct;
        allProducts = offReq.getItems();
        singleProduct = offReq.getSelectionModel().getSelectedItems();
        singleProduct.forEach(allProducts::remove);
    }

    public void acceptOff(ActionEvent actionEvent) {
        Controller.getOurController().acceptRequest((RequestOff) offReq.getSelectionModel().getSelectedItem());
        ObservableList<RequestOff> allProducts, singleProduct;
        allProducts = offReq.getItems();
        singleProduct = offReq.getSelectionModel().getSelectedItems();
        singleProduct.forEach(allProducts::remove);
    }

    public void declineSeller(ActionEvent actionEvent) {
        Controller.getOurController().declineRequest((RequestANewSellerAccount) sellerReq.getSelectionModel().getSelectedItem());
        removeFromTableView();
    }

    public void declineProduct(ActionEvent actionEvent) {
        Controller.getOurController().declineRequest((RequestProduct) productReq.getSelectionModel().getSelectedItem());
        removePro();
    }

    public void declineOff(ActionEvent actionEvent) {
        Controller.getOurController().declineRequest((RequestOff) offReq.getSelectionModel().getSelectedItem());
        removeOff();
    }

    public void switchToAccountPage(ActionEvent actionEvent) throws IOException {
        if (Controller.getOurController().getLoggedInAccount() == null) {
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
