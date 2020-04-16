package Model;

import java.util.ArrayList;

public class SellLog extends SaveAble{
    private static ArrayList<SellLog> allSelLogs;
    private String logBarcode;
    private String date;
    private double receivedCost;
    private double offSCost;
    private ArrayList<Product> products;
    //private String customerName;
    private String customerName;
    private boolean deliveredOrNot;

    public SellLog(String logBarcode, String date, double receivedCost, double offSCost, ArrayList<Product> products, String customerName, boolean deliveredOrNot) {
        this.logBarcode = logBarcode;
        this.date = date;
        this.receivedCost = receivedCost;
        this.offSCost = offSCost;
        this.products = products;
        this.customerName = customerName;
        this.deliveredOrNot = deliveredOrNot;
        allSelLogs.add(this);
    }

    @Override
    protected String getName() {
        return logBarcode;
    }
}
