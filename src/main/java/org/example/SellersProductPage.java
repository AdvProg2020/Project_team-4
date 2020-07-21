package org.example;

import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SellersProductPage implements Initializable {

    Model.Account account;
    String type;

    public TextField descriptionField;
    public TableColumn<Model.Product, String> byersColumn;
    public Label pictureStatus;
    ArrayList<TextField> textFields = new ArrayList<>();
    File imageFile = null;

    public TableView<Model.Product> table;
    public TableColumn<Model.Product, String> nameColumn;
    public TableColumn<Model.Product, String> categoryColumn;
    public TableColumn<Model.Product, String> companyColumn;
    public TableColumn<Model.Product, Double> costColumn;
    public TableColumn<Model.Product, Integer> amountColumn;
    public TableColumn<Model.Product, ArrayList<String>> tagsColumn;
    public TextField nameField;
    public TextField categoryField;
    public TextField companyFiled;
    public TextField costField;
    public TextField amountField;
    public TextField tagsField;
    public Button editButton;
    public Button removeButton;

    public void edit(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        pictureStatus.setText("no picture");
        App.sendMessageToServer("getProductWithBarcode", nameField.getText().trim());
        Model.Product product = (Model.Product) App.inObject.readObject();
        App.sendMessageToServer("getCurrentAccount", "");
        Model.Account account = null;
        String type = App.dataInputStream.readUTF();
        try {
            account = ((Model.Account)App.inObject.readObject());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (nameField.getText() != null && !nameField.getText().trim().equalsIgnoreCase("") && product != null) {
//            App.sendMessageToServer("getProductWithBarcode", ((Model.Seller)(account)).getUserName());
//            Product.getProductWithBarcode(nameField.getText().trim())
            App.sendMessageToServer("setSellersOfProduct", nameField.getText().trim() + " " + ((Model.Seller)(account)).getUserName());
//                    .setSellers(((Model.Seller)(account)).getUserName());
//            ((Model.Seller) account).setProducts(nameField.getText().trim());
            App.sendMessageToServer("setProductsOfASeller", account.getUserName() + " " + nameField.getText().trim());
            imageFile = null;
            return;
        }
        if (checkInfoEntrance())return;
        ArrayList<String> tagsArray = new ArrayList<>();
        for (String tag: tagsField.getText().trim().split(" ")) {
            tagsArray.add(tag);
        }
        App.sendObjectToServer(tagsArray);
        App.sendMessageToServer("createProductRequest", nameField.getText().trim() + " " + companyFiled.getText().trim() + " " + Integer.parseInt(costField.getText().trim()) + " " +  categoryField.getText().trim() + " " +  descriptionField.getText().trim() + " " +  Integer.parseInt(amountField.getText().trim()) + " " +   account.getUserName());
//        Controller.createProductRequest(nameField.getText().trim(), companyFiled.getText().trim(), Integer.parseInt(costField.getText().trim()), categoryField.getText().trim(), descriptionField.getText().trim(), Integer.parseInt(amountField.getText().trim()), tagsArray, Controller.getOurController().getCurrentAccount().getUserName());
        copyImage(nameField.getText());
    }

    public void remove(ActionEvent actionEvent) {
        ObservableList<Model.Product> selectedItem = table.getSelectionModel().getSelectedItems();
        App.sendMessageToServer("removeProductFromSellerProducts", selectedItem.get(0).getProductBarcode());
//        Controller.getOurController().removeProductFromSellerProducts(selectedItem.get(0).getProductBarcode());
        ObservableList<Model.Product> allProducts;
        ObservableList<Model.Product> singleProduct;
        allProducts = table.getItems();
        singleProduct = table.getSelectionModel().getSelectedItems();
        singleProduct.forEach(allProducts::remove);
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
        textFields.add(nameField);
        textFields.add(categoryField);
        textFields.add(companyFiled);
        textFields.add(costField);
        textFields.add(amountField);
        textFields.add(tagsField);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        App.sendMessageToServer("getCurrentAccount", "");
        account = null;
        try {
            type = App.dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            account = ((Model.Account)App.inObject.readObject());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        getEditAbleTextFields();
        ArrayList<String> productsNames = new ArrayList(((Model.Seller)account).getProducts());
        ArrayList<Model.Product> products = new ArrayList<>();
        for (String name: productsNames) {
            App.sendMessageToServer("getProductWithBarcode", name);

            try {
                products.add((Product) App.inObject.readObject());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        ObservableList<Product> observableList = FXCollections.observableArrayList(products);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("AmountOfExist"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("Cost"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
        tagsColumn.setCellValueFactory(new PropertyValueFactory<>("Tags"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("Company"));
        byersColumn.setCellValueFactory(new PropertyValueFactory<>("Byers"));


        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setItems(observableList);
    }

    public void switchToAccountPage(ActionEvent actionEvent) throws IOException {
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

    public void updateFields(MouseEvent mouseEvent) {
        Product product = table.getSelectionModel().getSelectedItems().get(0);
        String name  = product.getProductBarcode();

        nameField.setText(Product.getProductWithBarcode(name).getName());
        categoryField.setText(Product.getProductWithBarcode(name).getCategory());
        companyFiled.setText(Product.getProductWithBarcode(name).getCompany());
        costField.setText(String.valueOf(Product.getProductWithBarcode(name).getCost()));
        amountField.setText(String.valueOf(Product.getProductWithBarcode(name).getAmountOfExist()));
        tagsField.setText(String.valueOf(Product.getProductWithBarcode(name).getTags()));
        descriptionField.setText(String.valueOf(Product.getProductWithBarcode(name).getDescription()));

    }


    private void copyImage(String productname) {
        if(imageFile == null){
            return;
        }
        try {
            FileInputStream in = new FileInputStream(imageFile);
            FileOutputStream out = new FileOutputStream("Image\\" + productname + ".png");
            CopyFile(in, out);
            imageFile = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void CopyFile(FileInputStream in, FileOutputStream out) throws IOException {
        BufferedInputStream bin = new BufferedInputStream(in);
        BufferedOutputStream bout = new BufferedOutputStream(out);
        int b = 0;
        while (b != -1){
            b = bin.read();
            bout.write(b);
        }
        bin.close();
        bout.close();
    }

    public void addPicture(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("img files (*.png)", "*.png"));
        imageFile = fileChooser.showOpenDialog(App.getStage());
        if(imageFile != null){
            pictureStatus.setText("picture saved");
        }
    }
}
