package org.example;


import Model.Product;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class ProductPage {
    public TextField comment;
    public TextField score;
    public Button addButton;
    public MenuButton sellers;
    public Product product;
    public ProductPage(Product product) {
        this.product = product;
    }
}
