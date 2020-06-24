package org.example;

import Model.Off;
import Model.Product;
import Control.Controller;
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
import java.util.ResourceBundle;

public class SellersOffPage implements Initializable {

    ArrayList<TextField> textFields = new ArrayList<>();

    public TableView<Off> table;
    public TableColumn<Off, String> barcodeColumn;
    public TableColumn<Off, String> productsColumn;
    public TableColumn<Off, String> startDate;
    public TableColumn<Off, String> endDate;
    public TableColumn<Off, String> offAmountColumn;
    public TextField barcodeField;
    public TextField productsField;
    public TextField startDateField;
    public TextField endDateField;
    public TextField offAmountField;
    public Button editButton;

    @FXML
    public void edit(ActionEvent actionEvent) {
        if (checkInfoEntrance())return;
        ArrayList<String> productsNames = new ArrayList<>();
        for (String name: productsField.getText().trim().split(" ")) {
            if (Product.getProductWithBarcode(name) != null) {
                productsNames.add(name);
            }
        }
        Controller.getOurController().createOrEditOffRequest(productsNames, startDateField.getText().trim(), endDateField.getText().trim(), Integer.parseInt(offAmountField.getText().trim()));
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
        textFields.add(barcodeField);
        textFields.add(startDateField);
        textFields.add(endDateField);
        textFields.add(offAmountField);
        textFields.add(productsField);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getEditAbleTextFields();
        ArrayList<String> offsNames = new ArrayList(((Seller)Controller.getOurController().getLoggedInAccount()).getOffs());
        ArrayList<Off> offs = new ArrayList<>();
        for (String name: offsNames) {
            offs.add(Off.getOffByBarcode(name));
        }
        ObservableList<Off> observableList = FXCollections.observableArrayList(offs);

        barcodeColumn.setCellValueFactory(new PropertyValueFactory<>("OffBarcode"));
        productsColumn.setCellValueFactory(new PropertyValueFactory<>("Products"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
        offAmountColumn.setCellValueFactory(new PropertyValueFactory<>("OffAmount"));

        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setItems(observableList);
    }
}
