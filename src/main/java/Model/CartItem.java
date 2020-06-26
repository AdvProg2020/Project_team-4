package Model;

import Control.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class CartItem {

    private int itemNo;
    private String name;
    private double price;
    private int howMany;
    private double totalPrice;
    private Button addButton;
    private Button decreaseButton;

    public CartItem(Product product) {
        this.name = product.getName();
        this.price = product.getCost();
        addButton = new Button();
        addButton.setText("add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Controller.getOurController().increaseOrDecreaseProductNo(product.getProductBarcode(), +1);
            }
        });
        decreaseButton = new Button();
        decreaseButton.setText("delete");
        decreaseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Controller.getOurController().increaseOrDecreaseProductNo(product.getProductBarcode(), -1);
            }
        });
    }


    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public double getCost() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getHowMany() {
        return howMany;
    }

    public void setHowMany(int howMany) {
        this.howMany = howMany;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Button getAddButton() {
        return addButton;
    }

    public void setAddButton(Button addButton) {
        this.addButton = addButton;
    }

    public Button getDecreaseButton() {
        return decreaseButton;
    }

    public void setDecreaseButton(Button decreaseButton) {
        this.decreaseButton = decreaseButton;
    }
}
