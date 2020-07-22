package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SellersOffPage implements Initializable {

    ArrayList<TextField> textFields = new ArrayList<>();

    public TableView<Model.Off> table;
    public TableColumn<Model.Off, String> barcodeColumn;
    public TableColumn<Model.Off, String> productsColumn;
    public TableColumn<Model.Off, String> startDate;
    public TableColumn<Model.Off, String> endDate;
    public TableColumn<Model.Off, String> offAmountColumn;
    public TextField barcodeField;
    public TextField productsField;
    public TextField startDateField;
    public TextField endDateField;
    public TextField offAmountField;
    public Button editButton;

    @FXML
    public void edit(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        if (checkInfoEntrance())return;
        ArrayList<String> productsNames = new ArrayList<>();
        for (String name: productsField.getText().trim().split(" ")) {
            App.sendMessageToServer("getProductWithBarcode", name);
            Object object = App.inObject.readObject();
            Model.Product product =null;
            if (object!=null) {
                 product = (Model.Product) object;
            }
            if (product != null) {
                productsNames.add(name);
            }
        }
        System.out.println("hhh");
        App.sendMessageToServer("createOrEditOffRequest", startDateField.getText().trim() + " "  + endDateField.getText().trim() + " " + Integer.parseInt(offAmountField.getText().trim()) + " " + barcodeField.getText().trim());
        App.sendObjectToServer(productsNames);
//        Controller.getOurController().createOrEditOffRequest(productsNames, startDateField.getText().trim(), endDateField.getText().trim(), Integer.parseInt(offAmountField.getText().trim()), barcodeField.getText().trim());
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
        App.sendMessageToServer("getAllOffs", "");
        ArrayList<Model.Off> offs = null;
        try {
            offs = new ArrayList((ArrayList<Model.Off>) App.inObject.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<Model.Off> observableList = FXCollections.observableArrayList(offs);

        barcodeColumn.setCellValueFactory(new PropertyValueFactory<>("OffBarcode"));
        productsColumn.setCellValueFactory(new PropertyValueFactory<>("Products"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
        offAmountColumn.setCellValueFactory(new PropertyValueFactory<>("OffAmount"));

        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setItems(observableList);
    }

    public void switchToAccount(ActionEvent actionEvent) throws IOException {
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
