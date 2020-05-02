package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Off extends SaveAble {
    private static ArrayList<Off> allOffs;
    private static HashMap<String, Off> offs;
    private String offBarcode;
    private ArrayList<Product> products;
    private enum offStatus {
        MAKING, EDITING, APPROVED
    }

    private String startDate;
    private String endDate;
    private String offAmount;

    public Off(String offBarcode, String startDate, String endDate, String offAmount) {
        this.offBarcode = offBarcode;
        this.products = new ArrayList<Product>();
        this.startDate = startDate;
        this.endDate = endDate;
        this.offAmount = offAmount;
        allOffs.add(this);
    }

    public String getOffBarcode() {
        return offBarcode;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getOffAmount() {
        return offAmount;
    }

    @Override
    public String toString() {
        return "Off{" +
                "offBarcode='" + offBarcode + '\'' +
                ", products=" + products +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", offAmount=" + offAmount +
                '}';
    }

    @Override
    protected String getName() {
        return offBarcode;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
