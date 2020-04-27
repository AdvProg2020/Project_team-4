package Model;

import java.util.ArrayList;
import java.util.Date;

public class History {
    private String logBarcode;
    private Date date;
    private double cost;
    private double offSCost;
    private ArrayList<Product> products;
    private String name;
    private boolean deliveredOrNot;

    public History(String logBarcode, Date date, double paidCost, double offSCost, String sellerName, boolean deliveredOrNot) {
        this.logBarcode = logBarcode;
        this.date = date;
        this.cost = paidCost;
        this.offSCost = offSCost;
        this.products = new ArrayList<Product>();
        this.name = sellerName;
        this.deliveredOrNot = deliveredOrNot;
    }
}

