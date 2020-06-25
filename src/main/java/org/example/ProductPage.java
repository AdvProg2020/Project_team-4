package org.example;


import Control.Controller;
import Model.Product;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class ProductPage {
    public TextField comment;
    public TextField score;
    public Button addButton;
    public MenuButton sellers;
    public Product product;
    public Alert alert;
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
}
