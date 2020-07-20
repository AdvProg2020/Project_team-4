package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class History implements Serializable {
    private String logBarcode;
    private String date;
    private double cost;
    private double offSCost;
    private ArrayList<String> products;
    private ArrayList<String> name;
    private boolean deliveredOrNot;

    public History(String date, double paidCost, double offSCost, ArrayList<String> sellerName, ArrayList<String> products) {
        this.logBarcode = Product.givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect();
        this.date = date;
        this.cost = paidCost;
        this.offSCost = offSCost;
        this.products = products;
        this.name = sellerName;
    }

    public String getDate() {
        return date;
    }

    public double getCost() {
        return cost;
    }

    public double getOffSCost() {
        return offSCost;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public boolean isDeliveredOrNot() {
        return deliveredOrNot;
    }

    public String getLogBarcode() {
        return logBarcode;
    }

    @Override
    public String toString() {
        return "History{" +
                "logBarcode='" + logBarcode + '\'' +
                ", date=" + date +
                ", cost=" + cost +
                ", offSCost=" + offSCost +
                ", products=" + products +
                ", name=" + name +
                ", deliveredOrNot=" + deliveredOrNot +
                '}';
    }
}

