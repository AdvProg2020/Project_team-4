package org.example;


import Control.Controller;
import Model.Comment;
import Model.Product;
import Model.SaveAndLoad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

import static org.example.App.loadFXML;

public class ProductPage {

    private App.ClientImpl clientImpl;

    public void setClientImpl(App.ClientImpl clientImpl) {
        this.clientImpl = clientImpl;
    }

    public static HBox comparisonPageHBox = new HBox();
    public Label productName;
    public Label cost;
    public Label company;
    public Label averageScore;
    public Label description;
    public Label exist;
    public Label category;
    public ArrayList<Comment> comments = new ArrayList<>();
    public TextField score;
    public TextField commentField;
    public TextField nameField;
    public VBox commentsVBox = new VBox();
    public Button addButton;
    public MenuButton sellers;
    public ArrayList<MenuItem> menuItems;
    public MenuItem example;
    private static Product product;
    public Alert alert;
    public Button backButton;
    public TableColumn<Comment, String> commentsColumn;
    public TableColumn<Comment, String> nameColumn;
    public TableView<Comment> commentsTable;
    public ImageView productImage;
    public Button scoreButton;
    public Button replayButton;
    public Button commentButton;
    public TableView<Product> similarProduct;
    public Label newCommentLabel;
    public Button compareButton;
    public TableColumn<Comment, String> replayColumn;
    public TableColumn<Product, String> rateColumn;
    public Button rate;
    public Button showComparisonButton;
    public javafx.scene.layout.HBox HBox = new HBox();

    public static void setProduct(Product product) {
        ProductPage.product = product;
    }

    public void addToCart() {
        if (Controller.getOurController().getLoggedInAccount() != null) {
            Controller.getOurController().requestAddProductToCart(product.getProductBarcode());
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successfully added.");
            alert.show();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You must login first!");
            alert.show();
        }
    }

    public void addComment() {
        if (Controller.getOurController().getLoggedInAccount() != null) {
            Controller.getOurController().newComment(commentField.getText(), product, nameField.getText());
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successfully added.");
            alert.show();
            SaveAndLoad.getSaveAndLoad().saveGenerally();
            initializeCommentTable();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You must login first");
            alert.show();
        }
    }

    public void addScore() {
        product.setAverageScore(Integer.parseInt(score.getText()));
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Successfully scored.");
        alert.show();
        SaveAndLoad.getSaveAndLoad().saveGenerally();
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
        SaveAndLoad.getSaveAndLoad().saveGenerally();
    }

    public void showComparison() throws IOException {
        clientImpl.setRoot("comparison-page");
    }

    public void initialize() {
        product.setSeen(1);
        SaveAndLoad.getSaveAndLoad().saveGenerally();
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
                menuItem.setOnAction(event -> Controller.getOurController().setNameOfSellerOfProductAddedToCart(menuItem.getText()));
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
        clientImpl.setRoot("ProductsPage");
    }

    public void initializeCommentTable() {
        commentsColumn.setCellValueFactory(new PropertyValueFactory<>("CommentText"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("CommentingAccount"));
        replayColumn.setCellValueFactory(new PropertyValueFactory<>("Replay"));
        ArrayList<Product> products = Product.getAllProducts();
        comments.clear();
        for (Product product1 : products) {
            comments.addAll(product1.getComments());
        }
        ObservableList<Comment> observeListComment = FXCollections.observableArrayList(comments);
        commentsTable.getItems().clear();
        commentsTable.setItems(observeListComment);
    }

    public void replay(ActionEvent actionEvent) {
        for (Comment comment : product.getComments()) {
            if(comment.getCommentText().equals(commentsTable.getSelectionModel().getSelectedItem().getCommentText()))
                comment.setReplay(commentField.getText());
        }
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("successfully replayed.");
        alert.show();
        SaveAndLoad.getSaveAndLoad().saveGenerally();
        initializeCommentTable();
    }

}
