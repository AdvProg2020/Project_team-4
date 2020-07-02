package org.example;

import Control.Controller;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Manager {
    @FXML
    public Button request;
    @FXML
    public Button codedOff;
    @FXML
    public Button usersManaging;
    @FXML
    public Button categoriesManaging;
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
    public Button saveButton;

    public void initialize() {
        userName.setText(Controller.getOurController().getLoggedInAccount().getUserName());
        firstName.setText(Controller.getOurController().getLoggedInAccount().getFirstName());
        lastName.setText(Controller.getOurController().getLoggedInAccount().getLastName());
        passWord.setText(Controller.getOurController().getLoggedInAccount().getPassWord());
        phoneNumber.setText(Controller.getOurController().getLoggedInAccount().getPhoneNumber());
        mail.setText(Controller.getOurController().getLoggedInAccount().getEmail());
        role.setText(Controller.getOurController().getLoggedInAccount().getClass().toString());
        credit.setText(String.valueOf(Controller.getOurController().getLoggedInAccount().getCredit()));
        getEditAbleTextFields();
        saveButton.setOnAction(saveButtonHandler);
        Image image;
        File file = new File("Image\\" + Controller.getOurController().getLoggedInAccount().getUserName() + ".png");
        if(file.exists()){
            image = new Image("file:////..\\Image\\" + Controller.getOurController().getLoggedInAccount().getUserName() + ".png");
        }else{
            image = new Image("file:////..\\Image\\noProfile.png");
        }
        imageView.setImage(image);
    }

    EventHandler saveButtonHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            if (checkInfoEntrance()) return;
            Controller.getOurController().changeFields(firstName.getText().trim(), lastName.getText().trim(), phoneNumber.getText().trim(), mail.getText().trim(), passWord.getText().trim());
            SaveAndLoad.getSaveAndLoad().writeJSON(Controller.getOurController().getLoggedInAccount(), Model.Manager.class.toString(), Controller.getOurController().getLoggedInAccount().getUserName());
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

    @FXML
    public void goToRequestPage(ActionEvent actionEvent) {
        try {
            App.setRoot("request");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToCodedOffPage(ActionEvent actionEvent) {
        try {
            App.setRoot("off-code");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToManageUsersPage(ActionEvent actionEvent) {
        try {
            App.setRoot("users-managing");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToCategoriesPage(ActionEvent actionEvent) {
        try {
            App.setRoot("category");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToAccountPage(ActionEvent actionEvent) throws IOException {
        if (Controller.getOurController().getLoggedInAccount().equals(App.defaultCustomer)) {
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


    public void gotToMainPage(ActionEvent actionEvent) throws IOException {
        App.setRoot("main");
    }

    public void gotToProductsPage(ActionEvent actionEvent) throws IOException {
        App.setRoot("manager-products");
    }


    public void chooseImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("img files (*.png)", "*.png"));
        File file = fileChooser.showOpenDialog(App.getStage());

        if(file == null){
            return;
        }

        copyImage(file);

        Image image = new Image("file:///..\\Image\\" + Controller.getOurController().getLoggedInAccount().getUserName() + ".png");
        imageView.setImage(image);
    }

    private void copyImage(File file) {
        try {
            FileInputStream in = new FileInputStream(file);
            FileOutputStream out = new FileOutputStream("Image\\" + Controller.getOurController().getLoggedInAccount().getUserName() + ".png");
            SellersProductPage.CopyFile(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

