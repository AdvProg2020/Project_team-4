package Model;

import Control.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class History {
    private String logBarcode;
    private LocalDateTime date;
    private double cost;
    private double offSCost;
    private ArrayList<String> products;
    private ArrayList<String> name;
    private boolean deliveredOrNot;

    public History(LocalDateTime date, double paidCost, double offSCost, ArrayList<String> sellerName, ArrayList<String> products) {
        this.logBarcode = Product.givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect();
        this.date = date;
        this.cost = paidCost;
        this.offSCost = offSCost;
        this.products = products;
        this.name = sellerName;
    }

    public String getLogBarcode() {
        return logBarcode;
    }
}

