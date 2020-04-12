package Model;


import java.util.ArrayList;

public class BuyLog {
    private static ArrayList<BuyLog> allBuyLogs;
    private String logBarcode;
    private String date;
    private double paidCost;
    private double offSCost;
    private ArrayList<Product> products;
    //private String customerName;
    private String sellerName;
    private boolean deliveredOrNot;

    public BuyLog(String logBarcode, String date, double paidCost, double offSCost, String sellerName, boolean deliveredOrNot) {
        this.logBarcode = logBarcode;
        this.date = date;
        this.paidCost = paidCost;
        this.offSCost = offSCost;
        this.products = new ArrayList<Product>();
        this.sellerName = sellerName;
        this.deliveredOrNot = deliveredOrNot;
        allBuyLogs.add(this);
    }
}
