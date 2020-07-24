package org.example;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.Date;

public class Auction {

    public TextField price;
    public Label remainingTime;
    private Model.Auction auction;
    private Date expireDate;

    public void offerNewPrice(ActionEvent actionEvent) {
        Model.Auction.offerPrice(Double.parseDouble(price.getText()));
    }
    public void initialize(){
        long timestamp = System.currentTimeMillis();
        int expire = (int) ((expireDate.getSeconds() * 1000) - timestamp);
        remainingTime.setText(String.valueOf(expire));

    }
}
