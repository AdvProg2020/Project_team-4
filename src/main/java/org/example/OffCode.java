package org.example;

import Control.Controller;
import Model.*;
import Model.Customer;
import Model.Manager;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import static Model.Account.getAccountWithName;

public class OffCode implements Initializable {

    @FXML
    public TableColumn<CodedOff, String> barcode;
    @FXML
    public TableColumn<CodedOff, String> start;
    @FXML
    public TableColumn<CodedOff, String> end;
    @FXML
    public TableColumn<CodedOff, String> amount;
    @FXML
    public TableColumn<CodedOff, String> usageTimes;
    @FXML
    public TableColumn<CodedOff, String> percent;
    @FXML
    public TextField startField;
    @FXML
    public TextField endField;
    @FXML
    public TextField amountField;
    @FXML
    public TextField usageTimeField;
    @FXML
    public TextField percentField;
    @FXML
    public Button addButton;
    @FXML
    public TextField containingCustomers;
    @FXML
    public TableView<CodedOff> table;

    @FXML
    public void add(ActionEvent actionEvent) {
        if (checkInfoEntrance(startField, endField))return;
        if (checkInfoEntrance(amountField, usageTimeField))return;
        if (checkInfoEntrance(percentField, containingCustomers))return;
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(containingCustomers.getText().trim().split(" ")));
        ArrayList<String> containingCustomers = new ArrayList<>();
        for (String name: arrayList) {
            Account account = getAccountWithName(name);
            if(account == null){
                System.out.println("this username doesn't exist!");
                continue;
            }
            if(!account.getClass().equals(Model.Customer.class)){
                System.out.println("please enter customer for using codedoff");
                continue;
            }
            Model.Customer customer = (Customer) account;
            if(containingCustomers.contains(account)){
                System.out.println("this name was added one time");
                continue;
            }
            containingCustomers.add(account.getUserName());
        }
        int result = Controller.getOurController().controllerCreateOffCode(startField.getText().trim(), endField.getText().trim(), amountField.getText().trim(), percentField.getText().trim(), usageTimeField.getText().trim(), containingCustomers);
        System.out.println(result);
        if (result == 1) {
            table.getItems().add(CodedOff.getAllDiscounts().get(CodedOff.getAllDiscounts().size() - 1));
        }
    }

    public void checkEntrance(String error) {
        Alert a = new Alert(Alert.AlertType.NONE);

        // set alert type
        a.setAlertType(Alert.AlertType.WARNING);

        a.setContentText("Enter "+ error+ " first");
        // show the dialog
        a.show();
    }

    private boolean checkInfoEntrance(TextField userSign, TextField passSign) {
        if (userSign.getText() == null || userSign.getText().trim().equalsIgnoreCase("")) {
            checkEntrance("full info");
            return true;
        }
        if (passSign.getText() == null || passSign.getText().trim().equalsIgnoreCase("")){
            checkEntrance("full info");
            return true;
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<CodedOff> codedOffs = FXCollections.observableArrayList(new ArrayList<>(
                Controller.getOurController().showAllDiscountCodes()
        ));
        barcode.setCellValueFactory(new PropertyValueFactory<>("OffBarcode"));
        start.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
        end.setCellValueFactory(new PropertyValueFactory<>("EndTime"));
        amount.setCellValueFactory(new PropertyValueFactory<>("OffAmount"));
        usageTimes.setCellValueFactory(new PropertyValueFactory<>("UsageTime"));
        percent.setCellValueFactory(new PropertyValueFactory<>("Percent"));
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setItems(codedOffs);
        table.setEditable(true);
        end.setCellFactory(TextFieldTableCell.forTableColumn());
        amount.setCellFactory(TextFieldTableCell.forTableColumn());
        usageTimes.setCellFactory(TextFieldTableCell.forTableColumn());
        percent.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void changeEnd(TableColumn.CellEditEvent<CodedOff, String> codedOffStringCellEditEvent) {
        CodedOff codedOff = table.getSelectionModel().getSelectedItem();
        codedOff.setEndTime(codedOffStringCellEditEvent.getNewValue());
        SaveAndLoad.getSaveAndLoad().saveGenerally();
    }

    public void changeAmount(TableColumn.CellEditEvent<CodedOff, String> codedOffStringCellEditEvent) {
        CodedOff codedOff = table.getSelectionModel().getSelectedItem();
        codedOff.setOffAmount(codedOffStringCellEditEvent.getNewValue());
        SaveAndLoad.getSaveAndLoad().saveGenerally();
    }

    public void changeUsageTime(TableColumn.CellEditEvent<CodedOff, String> codedOffStringCellEditEvent) {
        CodedOff codedOff = table.getSelectionModel().getSelectedItem();
        codedOff.setUsageTime(codedOffStringCellEditEvent.getNewValue());
        SaveAndLoad.getSaveAndLoad().saveGenerally();
    }

    public void changePercent(TableColumn.CellEditEvent<CodedOff, String> codedOffStringCellEditEvent) {
        CodedOff codedOff = table.getSelectionModel().getSelectedItem();
        codedOff.setPercent(codedOffStringCellEditEvent.getNewValue());
        SaveAndLoad.getSaveAndLoad().saveGenerally();
    }
}
