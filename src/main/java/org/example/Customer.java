package org.example;

import Control.Controller;
import Model.CodedOff;
import Model.SaveAndLoad;
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
        userName.setText(Controller.getOurController().getCurrentAccount().getUserName());
        firstName.setText(Controller.getOurController().getCurrentAccount().getFirstName());
        lastName.setText(Controller.getOurController().getCurrentAccount().getLastName());
        passWord.setText(Controller.getOurController().getCurrentAccount().getPassWord());
        phoneNumber.setText(Controller.getOurController().getCurrentAccount().getPhoneNumber());
        mail.setText(Controller.getOurController().getCurrentAccount().getEmail());
        role.setText(Controller.getOurController().getCurrentAccount().getClass().toString());
        credit.setText(String.valueOf(Controller.getOurController().getCurrentAccount().getCredit()));
        getEditAbleTextFields();
        saveButton.setOnAction(saveButtonHandler);
        cartButton.setOnAction(cartButtonHandler);
        ArrayList<String> offCodesNames = new ArrayList<>(((Model.Customer)Controller.getOurController().getCurrentAccount()).getOffCodes());
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
        File file = new File("Image\\" + Controller.getOurController().getCurrentAccount().getUserName() + ".png");
        if(file.exists()){
            image = new Image("file:////..\\Image\\" + Controller.getOurController().getCurrentAccount().getUserName() + ".png");
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
            Controller.getOurController().setCustomersField(firstName.getText().trim(), lastName.getText().trim(), phoneNumber.getText().trim(), mail.getText().trim());
            Controller.getOurController().setCustomerPassWordAndAddress(passWord.getText().trim(), address.getText().trim());
            SaveAndLoad.getSaveAndLoad().writeJSON(Controller.getOurController().getCurrentAccount(), Model.Customer.class.toString(), Controller.getOurController().getCurrentAccount().getUserName());
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
        if (Controller.getOurController().getCurrentAccount().equals(App.defaultCustomer)) {
            LoginCreate.setBeforeRoot("main");
            App.setRoot("login-create");
        } else {
            switch (Controller.getOurController().getCurrentAccount().getClass().toString()) {
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

    public void logout(ActionEvent actionEvent) {
        int result = Controller.getOurController().logout();
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

    public void showOffCode(ActionEvent actionEvent) {
        String name = ((Button)actionEvent.getSource()).getText();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(CodedOff.getOffCodeWithName(name).getStartTime() + "/" + CodedOff.getOffCodeWithName(name).getEndTime());
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

        Image image = new Image("file:////..\\Image\\" + Controller.getOurController().getCurrentAccount().getUserName() + ".png");
        imageView.setImage(image);
    }

    private void copyImage(File file) {
        try {
            FileInputStream in = new FileInputStream(file);
            FileOutputStream out = new FileOutputStream("Image\\" + Controller.getOurController().getCurrentAccount().getUserName() + ".png");
            SellersProductPage.CopyFile(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
