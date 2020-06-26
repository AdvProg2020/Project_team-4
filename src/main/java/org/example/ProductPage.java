package org.example;


import Control.Controller;
import Model.Comment;
import Model.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class ProductPage {
    public TableColumn userComment;
    public TableColumn name;
    public Label productName;
    public Label cost;
    public Label company;
    public Label averageScore;
    public Label description;
    public Label exist;
    public Label category;
    public TextField commentField;
    public TextField nameField;
    public Label[] comments;
    public VBox commentsVBox;

    static class Comments{
        String name;
        String comment;
        public Comments() {
            this("", "");
        }
        public Comments(String name, String comment) {
            setName(name);
            setComment(comment);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }
    public TextField comment;
    public TextField score;
    public Button addButton;
    public MenuButton sellers;
    public Product product;
    public Alert alert;
    public MenuItem[] menuItems;



    public ProductPage(Product product) {
        this.product = product;
    }
    public void addToCart() {
        Controller.getOurController().requestAddProductToCart(product.getProductBarcode());
    }

    public void addComment() {
        Controller.getOurController().newComment(comment.getText());
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Successfully added.");
    }

    public void addScore() {
        product.setAverageScore(Integer.parseInt(score.getText()));
    }

    public void initialize(){
        menuItems = new MenuItem[product.getSellers().size()];
        for (int i = 0; i < product.getSellers().size(); i++) {
            for (String seller : product.getSellers()) {
                menuItems[i].setText(seller);
            }
        }
        sellers = new MenuButton("sellers", null, menuItems);
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
        commentsVBox = new VBox(comments);

    }
}
