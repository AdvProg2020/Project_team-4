package org.example;


import Control.Controller;
import Model.Comment;
import Model.Product;
import View.Menu.Menu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class ProductPage {

    public Label productName;
    public Label cost;
    public Label company;
    public Label averageScore;
    public Label description;
    public Label exist;
    public Label category;
    public ArrayList<Label> comments;
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

    public static void setProduct(Product product) {
        ProductPage.product = product;
    }

    public void addToCart() {
        Controller.getOurController().requestAddProductToCart(product.getProductBarcode());
    }

    public void addComment() {
        Controller.getOurController().newComment(commentField.getText(), product, nameField.getText());
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Successfully added.");
        alert.show();
    }

    public void addScore() {
        product.setAverageScore(Integer.parseInt(score.getText()));
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Successfully scored.");
        alert.show();
    }

    public void initialize() {
        product.setSeen(1);
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
                menuItem.setOnAction(EventHandler);
            }

        }
        productName.setText(product.getName());
        cost.setText(String.valueOf(product.getCost()));
        company.setText(product.getCompany());
        averageScore.setText(String.valueOf(product.getAverageScore()));
        description.setText(product.getDescription());
        exist.setText(String.valueOf(product.getAmountOfExist()));
        category.setText(product.getCategory());
        for (Comment productComment : product.getComments()) {
            Label l = new Label();
            l.setText(String.valueOf(productComment));
            comments.add(l);
        }
        if (comments != null){
            for (Label comment : comments) {
                comment.setVisible(true);
                commentsVBox.getChildren().add(comment);
            }
        }
    }

    public void back(ActionEvent actionEvent) throws IOException {
        App.setRoot("ProductsPage");
    }
}
