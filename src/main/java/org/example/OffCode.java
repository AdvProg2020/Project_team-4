package org.example;




import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;




public class OffCode implements Initializable {

    @FXML
    public TableColumn<Model.CodedOff, String> barcode;
    @FXML
    public TableColumn<Model.CodedOff, String> start;
    @FXML
    public TableColumn<Model.CodedOff, String> end;
    @FXML
    public TableColumn<Model.CodedOff, String> amount;
    @FXML
    public TableColumn<Model.CodedOff, String> usageTimes;
    @FXML
    public TableColumn<Model.CodedOff, String> percent;
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
    public TableView<Model.CodedOff> table;

    @FXML
    public void add(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        if (checkInfoEntrance(startField, endField))return;
        if (checkInfoEntrance(amountField, usageTimeField))return;
        if (checkInfoEntrance(percentField, containingCustomers))return;
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(containingCustomers.getText().trim().split(" ")));
        ArrayList<String> containingCustomers = new ArrayList<>();
        for (String name: arrayList) {
            App.sendMessageToServer("getAccountWithName", name);

            Model.Account account = null;
            try {
                account = (Model.Account) App.inObject.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(account == null){
                System.out.println("this username doesn't exist!");
                continue;
            }
            if(!account.getClass().equals(Model.Customer.class)){
                System.out.println("please enter customer for using codedoff");
                continue;
            }
            Model.Customer customer = (Model.Customer) account;
            if(containingCustomers.contains(account)){
                System.out.println("this name was added one time");
                continue;
            }
            containingCustomers.add(account.getUserName());
        }
        StringBuilder stringBuilder = new StringBuilder(startField.getText().trim() + " " + endField.getText().trim() + " " + amountField.getText().trim() + " " + percentField.getText().trim() + " " + usageTimeField.getText().trim());
        App.sendMessageToServer("controllerCreateOffCode", stringBuilder.toString());
        App.sendObjectToServer(containingCustomers);
//        int result = Controller.getOurController().controllerCreateOffCode(startField.getText().trim(), endField.getText().trim(), amountField.getText().trim(), percentField.getText().trim(), usageTimeField.getText().trim(), containingCustomers);
        int result = (int) App.inObject.readObject();

        App.sendMessageToServer("getAllDiscounts", "");

        ArrayList<Model.CodedOff> codedOffArrayList = (ArrayList<Model.CodedOff>) App.inObject.readObject();
        if (result == 1) {
            table.getItems().add(codedOffArrayList.get(codedOffArrayList.size() - 1));
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
        App.sendMessageToServer("showAllDiscountCodes", "");
        ArrayList<Model.CodedOff> codedOff = null;
        try {
            codedOff = (ArrayList<Model.CodedOff>) App.inObject.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<Model.CodedOff> codedOffs = FXCollections.observableArrayList(new ArrayList<>(
                codedOff
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

    public void changeEnd(TableColumn.CellEditEvent<Model.CodedOff, String> codedOffStringCellEditEvent) throws IOException, ClassNotFoundException {
        String codedOffName = table.getSelectionModel().getSelectedItem().getOffBarcode();
        App.sendMessageToServer("setEndTimeForOffCode", codedOffName + " " + codedOffStringCellEditEvent.getNewValue());
//        CodedOff codedOff = (CodedOff) App.inObject.readObject();
//        codedOff.setEndTime(codedOffStringCellEditEvent.getNewValue());
//        SaveAndLoad.getSaveAndLoad().saveGenerally();
    }

    public void changeAmount(TableColumn.CellEditEvent<Model.CodedOff, String> codedOffStringCellEditEvent) {
        String codedOffName = table.getSelectionModel().getSelectedItem().getOffBarcode();
        App.sendMessageToServer("setAmountForOffCode", codedOffName + " " + codedOffStringCellEditEvent.getNewValue());
//        SaveAndLoad.getSaveAndLoad().saveGenerally();
    }

    public void changeUsageTime(TableColumn.CellEditEvent<Model.CodedOff, String> codedOffStringCellEditEvent) {
        String codedOffName = table.getSelectionModel().getSelectedItem().getOffBarcode();
        App.sendMessageToServer("setUsageTimeForOffCode", codedOffName + " " + codedOffStringCellEditEvent.getNewValue());
    }

    public void changePercent(TableColumn.CellEditEvent<Model.CodedOff, String> codedOffStringCellEditEvent) {
        String codedOffName = table.getSelectionModel().getSelectedItem().getOffBarcode();
        App.sendMessageToServer("setPercentForOffCode", codedOffName + " " + codedOffStringCellEditEvent.getNewValue());
//        SaveAndLoad.getSaveAndLoad().saveGenerally();
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
