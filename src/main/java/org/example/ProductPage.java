package org.example;


import Control.Controller;
import Model.Comment;
import Model.Product;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ProductPage {

    public Label productName;
    public Label cost;
    public Label company;
    public Label averageScore;
    public Label description;
    public Label exist;
    public Label category;
    public Label[] comments;
    public TextField score;
    public TextField commentField;
    public TextField nameField;
    public VBox commentsVBox;
    public Button addButton;
    public MenuButton sellers;
    public MenuItem[] menuItems;
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
        Controller.getOurController().newComment(commentField.getText());
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Successfully added.");
        alert.show();
    }

    public void addScore() {
        product.setAverageScore(Integer.parseInt(score.getText()));
    }

    public void initialize() {
        try {
            if (product.getSellers() != null) {
                menuItems = new MenuItem[product.getSellers().size()];
                for (int i = 0; i < product.getSellers().size(); i++) {
                    for (String seller : product.getSellers()) {
                        menuItems[i].setText(seller);
                    }
                }
                for (MenuItem menuItem : menuItems) {
                    sellers.getItems().add(menuItem);
                }

            }
        } catch (NullPointerException e) {
            System.out.println("ey baba");
        }
        productName.setText(product.getName());
        cost.setText(String.valueOf(product.getCost()));
        company.setText(product.getCompany());
        averageScore.setText(String.valueOf(product.getAverageScore()));
        description.setText(product.getDescription());
        exist.setText(String.valueOf(product.getAmountOfExist()));
        category.setText(product.getCategory());
        for (Comment productComment : product.getComments()) {
            for (int i = 0; i < product.getComments().size(); i++) {
                comments[i].setText(String.valueOf(productComment));
            }
        }
        if (comments != null)
            commentsVBox = new VBox(comments);
    }

    public void back(ActionEvent actionEvent) throws IOException {
        App.setRoot("ProductsPage");
    }
}
