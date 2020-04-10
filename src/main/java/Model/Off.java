package Model;



import java.util.ArrayList;
import java.util.HashMap;

public class Off {
    private static HashMap<String, Off> offs;
    private String offBarcode;
    private ArrayList<Product> products;
    private enum offStatus {
        inProcessForMaking, inProcessForEdit, approved
    }

    private String startDate;
    private String endDate;
    private double offAmount;

}
