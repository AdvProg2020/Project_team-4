package org.example;

import Control.Controller;
import Model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class History implements Initializable {

    @FXML
    public TableView<Model.History> table;
    @FXML
    public TableColumn barcode;
    @FXML
    public TableColumn date;
    @FXML
    public TableColumn paidCost;
    @FXML
    public TableColumn codedOffDiscountAmount;
    @FXML
    public TableColumn boughtThings;
    @FXML
    public TableColumn sellerName;
    @FXML
    public TableColumn deliveredOrNot;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Model.History> histories = Controller.getOurController().requestSalesHistoryInfoInSeller();
        ArrayList<String> sellerNames = new ArrayList<>();
        sellerNames.add("ali");
        sellerNames.add("rpo");
        sellerNames.add("eth");
        histories.add(new Model.History("01-01-2020 10:10:11", 98.800, 50.30, sellerNames, new ArrayList<>()));
        ObservableList<Model.History> categoriesObserveAbleList = FXCollections.observableArrayList(histories);
        barcode.setCellValueFactory(new PropertyValueFactory<>("LogBarcode"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        paidCost.setCellValueFactory(new PropertyValueFactory<>("Cost"));
        codedOffDiscountAmount.setCellValueFactory(new PropertyValueFactory<>("OffSCost"));
        boughtThings.setCellValueFactory(new PropertyValueFactory<>("Products"));
        sellerName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        deliveredOrNot.setCellValueFactory(new PropertyValueFactory<>("DeliveredOrNot"));
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setItems(categoriesObserveAbleList);
    }

    public void switchToAacountPage(ActionEvent actionEvent) throws IOException {
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
