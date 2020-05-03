package Model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CodedOff extends SaveAble {
    private static ArrayList<CodedOff> allOfCodes = new ArrayList<>();
    private String offBarcode;
    private SimpleDateFormat startTime;
    private SimpleDateFormat endTime;
    private double offAmount;
    private int usageTime;
    private HashMap<Account, Integer> numberOfUsageForEachAccount;
    private ArrayList<Account> discountIsForTheseAccounts;
    private ArrayList<Customer> containingCustomers;
    private static ArrayList<String> offBarcodes = new ArrayList<>();

    public CodedOff(String offBarcode, SimpleDateFormat startTime, SimpleDateFormat endTime, double offAmount, int usageTime, ArrayList<Customer> containingCustomers) {
        this.offBarcode = offBarcode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.offAmount = offAmount;
        this.usageTime = usageTime;
        this.containingCustomers = containingCustomers;
        this.numberOfUsageForEachAccount = new HashMap<Account, Integer>();
        this.discountIsForTheseAccounts = new ArrayList<Account>();
        allOfCodes.add(this);
    }

    public static void removeOffCode(CodedOff offCode) {
        File file = new File(offCode.getName());

        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
        allOfCodes.remove(offCode);
    }

    public static ArrayList<String> getOffBarcodes() {
        return offBarcodes;
    }

    public String getOffBarcode() {
        return offBarcode;
    }

    @Override
    public String toString() {
        return "CodedOff{" +
                "offBarcode='" + offBarcode + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", offAmount=" + offAmount +
                ", numberOfUsageForEachAccount=" + numberOfUsageForEachAccount +
                ", discountIsForTheseAccounts=" + discountIsForTheseAccounts +
                '}';
    }

    @Override
    protected String getName() {
        return offBarcode;
    }


    public static ArrayList<CodedOff> getAllDiscounts() {
        return allOfCodes;
    }

    public static CodedOff getOffCodeWithName(String name) {
        for (CodedOff offCode : allOfCodes) {
            return offCode;
        }
        return null;
    }
}
