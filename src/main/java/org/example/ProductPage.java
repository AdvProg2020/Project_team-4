package org.example;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class ProductPage {

    Model.Account account;

    public static HBox comparisonPageHBox = new HBox();
    public Label productName;
    public Label cost;
    public Label company;
    public Label averageScore;
    public Label description;
    public Label exist;
    public Label category;
    public ArrayList<Model.Comment> comments = new ArrayList<>();
    public TextField score;
    public TextField commentField;
    public TextField nameField;
    public VBox commentsVBox = new VBox();
    public Button addButton;
    public MenuButton sellers;
    public ArrayList<MenuItem> menuItems;
    public MenuItem example;
    private static Model.Product product;
    public Alert alert;
    public Button backButton;
    public TableColumn<Model.Comment, String> commentsColumn;
    public TableColumn<Model.Comment, String> nameColumn;
    public TableView<Model.Comment> commentsTable;
    public ImageView productImage;
    public Button scoreButton;
    public Button replayButton;
    public Button commentButton;
    public TableView<Model.Product> similarProduct;
    public Label newCommentLabel;
    public Button compareButton;
    public TableColumn<Model.Comment, String> replayColumn;
    public TableColumn<Model.Product, String> rateColumn;
    public Button rate;
    public Button showComparisonButton;
    public javafx.scene.layout.HBox HBox = new HBox();

    public static void setProduct(Model.Product product) {
        ProductPage.product = product;
    }

    public void addToCart() throws IOException {
        if (account != null) {
            App.sendMessageToServer("requestAddProductToCart", product.getProductBarcode());
//            Controller.getOurController().requestAddProductToCart(product.getProductBarcode());
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successfully added.");
            alert.show();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You must login first!");
            alert.show();
        }
    }

    public void addComment() throws IOException, ClassNotFoundException {
        if (account != null) {
            App.sendMessageToServer("newComment", commentField.getText() + " " + product + " " + nameField.getText());
            App.sendObjectToServer(product);
//            Controller.getOurController().newComment(commentField.getText(), product, nameField.getText());
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successfully added.");
            alert.show();
//            SaveAndLoad.getSaveAndLoad().saveGenerally();
            initializeCommentTable();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You must login first");
            alert.show();
        }
    }

    public void addScore() {
        App.sendMessageToServer("setAverageScore", score.getText());
        App.sendObjectToServer(product);
        product.setAverageScore(Integer.parseInt(score.getText()));
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Successfully scored.");
        alert.show();
//        SaveAndLoad.getSaveAndLoad().saveGenerally();
        averageScore.setText(String.valueOf(product.getAverageScore()));
    }

    public void addToCompare(){
        VBox vBox = new VBox();
        Label[] labels = new Label[7];
        for (int i = 0; i < 7; i++) {
            labels[i] = new Label();
        }
        labels[0].setText(product.getNameOfProductNotBarcode());
        labels[1].setText(String.valueOf(product.getCost()));
        labels[2].setText(product.getCompany());
        labels[3].setText(String.valueOf(product.getAverageScore()));
        labels[4].setText(String.valueOf(product.getAmountOfExist()));
        labels[5].setText(product.getCategory());
        labels[6].setText(product.getDescription());
        vBox.getChildren().addAll(labels);
        ComparisonPage.setVBox(vBox);
//        SaveAndLoad.getSaveAndLoad().saveGenerally();
    }

    public void showComparison() throws IOException {
        App.setRoot("comparison-page");
    }

    public void initialize() throws IOException, ClassNotFoundException {
        App.sendMessageToServer("getCurrentAccount", "");
        account = null;
        String type = App.dataInputStream.readUTF();
        try {
            account = ((Model.Account)App.inObject.readObject());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        product.setSeen(1);
        App.sendMessageToServer("setSeen", String.valueOf(1));
        App.sendObjectToServer(product);
//        SaveAndLoad.getSaveAndLoad().saveGenerally();
        comments = new ArrayList<>();
        commentsVBox.setLayoutX(400);
        commentsVBox.setLayoutY(500);
        if (product.getSellers() != null) {
            menuItems = new ArrayList<>();
            for (String seller : product.getSellers()) {
                MenuItem mi = new MenuItem();
                mi.setText(seller);
                menuItems.add(mi);
            }
            for (MenuItem menuItem : menuItems) {
                sellers.getItems().add(menuItem);
//                Controller.getOurController().setNameOfSellerOfProductAddedToCart(menuItem.getText());
                menuItem.setOnAction(event -> App.sendMessageToServer("setNameOfSellerOfProductAddedToCart", menuItem.getText()));
            }

        }
        productName.setText(product.getNameOfProductNotBarcode());
        cost.setText(String.valueOf(product.getCost()));
        company.setText(product.getCompany());
        averageScore.setText(String.valueOf(product.getAverageScore()));
        description.setText(product.getDescription());
        exist.setText(String.valueOf(product.getAmountOfExist()));
        category.setText(product.getCategory());
        initializeCommentTable();
        productImage.setImage(product.getImageFile());
    }

    public void back(ActionEvent actionEvent) throws IOException {
        App.setRoot("ProductsPage");
    }

    public void initializeCommentTable() throws IOException, ClassNotFoundException {
        commentsColumn.setCellValueFactory(new PropertyValueFactory<>("CommentText"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("CommentingAccount"));
        replayColumn.setCellValueFactory(new PropertyValueFactory<>("Replay"));
        App.sendMessageToServer("getAllProducts", "");
        ArrayList<Model.Product> products = (ArrayList<Model.Product>) App.inObject.readObject();
//                Product.getAllProducts();
        comments.clear();
        for (Model.Product product1 : products) {
            comments.addAll(product1.getComments());
        }
        ObservableList<Model.Comment> observeListComment = FXCollections.observableArrayList(comments);
        commentsTable.getItems().clear();
        commentsTable.setItems(observeListComment);
    }


    //////////////////////NAFAHMIDAM CHI KAR MIKONE JODA NATOONESTAM BEKONAM
    public void replay(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        for (Model.Comment comment : product.getComments()) {
            if(comment.getCommentText().equals(commentsTable.getSelectionModel().getSelectedItem().getCommentText()))
                comment.setReplay(commentField.getText());
        }
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("successfully replayed.");
        alert.show();
        App.sendMessageToServer("saveProduct", product.getProductBarcode());
        App.sendObjectToServer(product);
//        SaveAndLoad.getSaveAndLoad().saveGenerally();
        initializeCommentTable();
    }

}
