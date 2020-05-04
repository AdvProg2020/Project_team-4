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

    private Date startDate;
    private Date endDate;
    private double offAmount;

    public Off(String offBarcode, Date startDate, Date endDate, double offAmount) {
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

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getOffAmount() {
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

    public static Off getOffByName(String name) {
        for (Off off: allOffs) {
            if (off.getName().equalsIgnoreCase(name)) {
                return off;
            }
        }
        return null;
    }

    public static ArrayList<Off> getAllOffs() {
        return allOffs;
    }
}
