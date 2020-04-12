package Model;



import java.util.ArrayList;
import java.util.HashMap;

public class Off {
    private static ArrayList<Off> allOffs;
    private static HashMap<String, Off> offs;
    private String offBarcode;
    private ArrayList<Product> products;
    private enum offStatus {
        inProcessForMaking, inProcessForEdit, approved
    }

    private String startDate;
    private String endDate;
    private double offAmount;

    public Off(String offBarcode, String startDate, String endDate, double offAmount) {
        this.offBarcode = offBarcode;
        this.products = new ArrayList<Product>();
        this.startDate = startDate;
        this.endDate = endDate;
        this.offAmount = offAmount;
        allOffs.add(this);
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

    public ArrayList<Product> getProducts() {
        return products;
    }
}
