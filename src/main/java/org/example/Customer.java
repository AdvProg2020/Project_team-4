package org.example;


import Model.CodedOff;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;

public class Customer {
    Model.Account account;
    public Button codedOff1Button;
    public Button codedOff2Button;
    public Button codedOff3Button;
    public ImageView imageView;
    ArrayList<TextField> textFields = new ArrayList<>();
    @FXML
    public TextField address;
    @FXML
    public TextField userName;
    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    public TextField passWord;
    @FXML
    public TextField phoneNumber;
    @FXML
    public TextField mail;
    @FXML
    public TextField role;
    @FXML
    public TextField credit;
    @FXML
    public TextField offCodes;
    @FXML
    public Button cartButton;
    @FXML
    public Button historyButton;
    @FXML
    public Button saveButton;

    public void initialize() {
        App.sendMessageToServer("getCurrentAccount", "");
        account = null;
        try {
            account = ((Model.Account)App.inObject.readObject());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        userName.setText(account.getUserName());
        firstName.setText(account.getFirstName());
        lastName.setText(account.getLastName());
        passWord.setText(account.getPassWord());
        phoneNumber.setText(account.getPhoneNumber());
        mail.setText(account.getEmail());
        role.setText(account.getClass().toString());
        credit.setText(String.valueOf(account.getCredit()));
        getEditAbleTextFields();
        saveButton.setOnAction(saveButtonHandler);
        cartButton.setOnAction(cartButtonHandler);
        ArrayList<String> offCodesNames = new ArrayList<>(((Model.Customer)account).getOffCodes());
        int i=0;
        if (offCodesNames.size() > 0) {
            codedOff1Button.setText(offCodesNames.get(0));
            codedOff1Button.setVisible(true);
        }
        if (offCodesNames.size() > 1) {
            codedOff2Button.setText(offCodesNames.get(1));
            codedOff2Button.setVisible(true);
        }
        if (offCodesNames.size() > 2) {
            codedOff3Button.setText(offCodesNames.get(2));
            codedOff3Button.setVisible(true);
        }
        Image image;
        File file = new File("Image\\" + account.getUserName() + ".png");
        if(file.exists()){
            image = new Image("file:////..\\Image\\" + account.getUserName() + ".png");
        }else{
            image = new Image("file:////..\\Image\\noProfile.png");
        }
        imageView.setImage(image);
    }

    EventHandler cartButtonHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            try {
                App.setRoot("cart");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    EventHandler saveButtonHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            if (checkInfoEntrance()) return;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(firstName.getText().trim() + " " + lastName.getText().trim() + " " + phoneNumber.getText().trim() + " " + mail.getText().trim() + " ");
            App.sendMessageToServer("setCustomersField", stringBuilder.toString());
//            Controller.getOurController().setCustomersField(firstName.getText().trim(), lastName.getText().trim(), phoneNumber.getText().trim(), mail.getText().trim());
            stringBuilder = new StringBuilder();
            stringBuilder.append(passWord.getText().trim() + " " + address.getText().trim());
            App.sendMessageToServer("setCustomerPassWordAndAddress", stringBuilder.toString());
//            Controller.getOurController().setCustomerPassWordAndAddress(passWord.getText().trim(), address.getText().trim());
//            SaveAndLoad.getSaveAndLoad().writeJSON(Controller.getOurController().getCurrentAccount(), Model.Customer.class.toString(), Controller.getOurController().getCurrentAccount().getUserName());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("saved SuccessFully");
            alert.show();
        }
    };
    private void getEditAbleTextFields() {
        textFields.add(firstName);
        textFields.add(lastName);
        textFields.add(passWord);
        textFields.add(phoneNumber);
        textFields.add(mail);
        textFields.add(address);
    }

    private boolean checkInfoEntrance() {
        for (TextField textField: textFields) {
            if (textField.getText() == null || textField.getText().trim().equalsIgnoreCase("")) {
                checkEntrance("empty fields");
                saveButton.setOnAction(saveButtonHandler);
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

    public void logout(ActionEvent actionEvent) throws IOException {
        App.sendMessageToServer("logout", "");
        int result = App.inObject.readInt();
        if (result == 2) {
            try {
                App.setRoot("main");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void goToMainPage(ActionEvent actionEvent) throws IOException {
        App.setRoot("main");
    }

    public void showOffCode(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        String name = ((Button)actionEvent.getSource()).getText();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        App.sendMessageToServer("getOffCodeWithName", name);
        Model.CodedOff codedOff = (CodedOff) App.inObject.readObject();
        alert.setContentText(codedOff.getStartTime() + "/" + codedOff.getEndTime());
        alert.show();
    }

    public void goToHistoryPage(ActionEvent actionEvent) throws IOException {
        App.setRoot("sell-history");
    }

    public void chooseImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("img files (*.png)", "*.png"));
        File file = fileChooser.showOpenDialog(App.getStage());

        if(file == null){
            return;
        }

        copyImage(file);

        Image image = new Image("file:////..\\Image\\" + account.getUserName() + ".png");
        imageView.setImage(image);
    }

    private void copyImage(File file) {
        try {
            FileInputStream in = new FileInputStream(file);
            FileOutputStream out = new FileOutputStream("Image\\" + account.getUserName() + ".png");
            SellersProductPage.CopyFile(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
