package org.example;


import Control.Controller;
import Model.Product;
import javafx.scene.control.*;

public class ProductPage {
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

    }
}
