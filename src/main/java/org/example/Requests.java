package org.example;

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
    public TableView<Model.RequestANewSellerAccount> sellerReq;
    @FXML
    public TableView<Model.RequestProduct> productReq;
    @FXML
    public TableView<Model.RequestOff> offReq;
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
    public TableColumn<Model.RequestANewSellerAccount, String> sellerUserName;
    @FXML
    public TableColumn<Model.RequestProduct, String> productBarcode;
    @FXML
    public TableColumn<Model.RequestOff, String> offBarcode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sellerUserName.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        productBarcode.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        offBarcode.setCellValueFactory(new PropertyValueFactory<>("OffName"));
        ObservableList<Model.RequestANewSellerAccount> sellerRequests = null;
        try {
            sellerRequests = getSellerReqs();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        sellerReq.setItems(sellerRequests);
        ObservableList<Model.RequestProduct> productsRequests = null;
        try {
            productsRequests = getProductReqs();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        productReq.setItems(productsRequests);
        ObservableList<Model.RequestOff> offsRequests = null;
        try {
            offsRequests = getOffReqs();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        offReq.setItems(offsRequests);
        sellerReq.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        productReq.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        offReq.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private ObservableList<Model.RequestOff> getOffReqs() throws IOException, ClassNotFoundException {
        App.sendMessageToServer("getEditOffRequests", "");
        List list = new ArrayList((ArrayList<Model.Request>) App.inObject.readObject());
        ObservableList observableList = FXCollections.observableArrayList(list);
        return observableList;
    }

    private ObservableList<Model.RequestProduct> getProductReqs() throws IOException, ClassNotFoundException {
        App.sendMessageToServer("getEditProductRequests", "");
        List list = new ArrayList((ArrayList<Model.Request>) App.inObject.readObject());
        System.out.println(list);
        ObservableList observableList = FXCollections.observableArrayList(list);
        return observableList;
    }

    private ObservableList<Model.RequestANewSellerAccount> getSellerReqs() throws IOException, ClassNotFoundException {
        App.sendMessageToServer("getRegisterSellerAccountRequests", "");
        List list = new ArrayList((ArrayList<Model.Request>) App.inObject.readObject());
        list.add(new Model.RequestANewSellerAccount(Model.RequestType.ACCOUNT, "ali", "pass123"));
        ObservableList observableList = FXCollections.observableArrayList(list);
        return observableList;
    }


    public void acceptSeller(ActionEvent actionEvent) {
        App.sendMessageToServer("RequestANewSellerAccountAccept", "");
        App.sendObjectToServer(sellerReq.getSelectionModel().getSelectedItem());
//        Controller.getOurController().acceptRequest((RequestANewSellerAccount) sellerReq.getSelectionModel().getSelectedItem());
        removeFromTableView();

    }

    private void removeFromTableView() {
        ObservableList<Model.RequestANewSellerAccount> allProducts, singleProduct;
        allProducts = sellerReq.getItems();
        singleProduct = sellerReq.getSelectionModel().getSelectedItems();
        singleProduct.forEach(allProducts::remove);
    }

    public void acceptProduct(ActionEvent actionEvent) {
        App.sendMessageToServer("RequestProductAccountAccept", "");
        App.sendObjectToServer(productReq.getSelectionModel().getSelectedItem());

//        Controller.getOurController().acceptRequest((RequestProduct) productReq.getSelectionModel().getSelectedItem());
        removePro();
    }

    private void removePro() {
        ObservableList<Model.RequestProduct> allProducts;
        ObservableList<Model.RequestProduct> singleProduct;
        allProducts = productReq.getItems();
        singleProduct = productReq.getSelectionModel().getSelectedItems();
        singleProduct.forEach(allProducts::remove);
    }

    private void removeOff() {
        ObservableList<Model.RequestOff> allProducts;
        ObservableList<Model.RequestOff> singleProduct;
        allProducts = offReq.getItems();
        singleProduct = offReq.getSelectionModel().getSelectedItems();
        singleProduct.forEach(allProducts::remove);
    }

    public void acceptOff(ActionEvent actionEvent) {
        App.sendMessageToServer("RequestOffAccountAccept", "");
        App.sendObjectToServer(offReq.getSelectionModel().getSelectedItem());
//        Controller.getOurController().acceptRequest((RequestOff) offReq.getSelectionModel().getSelectedItem());
        ObservableList<Model.RequestOff> allProducts, singleProduct;
        allProducts = offReq.getItems();
        singleProduct = offReq.getSelectionModel().getSelectedItems();
        singleProduct.forEach(allProducts::remove);
    }

    public void declineSeller(ActionEvent actionEvent) {
        App.sendMessageToServer("RequestANewSellerAccountDecline", "");
        App.sendObjectToServer(sellerReq.getSelectionModel().getSelectedItem());
//        Controller.getOurController().declineRequest((RequestANewSellerAccount) sellerReq.getSelectionModel().getSelectedItem());
        removeFromTableView();
    }

    public void declineProduct(ActionEvent actionEvent) {
        App.sendMessageToServer("RequestProductAccountDecline", "");
        App.sendObjectToServer(productReq.getSelectionModel().getSelectedItem());

//        Controller.getOurController().declineRequest((RequestProduct) productReq.getSelectionModel().getSelectedItem());
        removePro();
    }

    public void declineOff(ActionEvent actionEvent) {
        App.sendMessageToServer("RequestOffAccountDecline", "");
        App.sendObjectToServer(offReq.getSelectionModel().getSelectedItem());
//        Controller.getOurController().declineRequest((RequestOff) offReq.getSelectionModel().getSelectedItem());
        removeOff();
    }

    public void switchToAccountPage(ActionEvent actionEvent) throws IOException {
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
