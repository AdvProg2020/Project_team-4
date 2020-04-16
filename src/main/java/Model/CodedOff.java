package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class CodedOff extends SaveAble{
    private static ArrayList<CodedOff> allOfCodes;
    private String offBarcode;
    private String startTime;
    private String endTime;
    private double offAmount;
    private int usageTime;
    private HashMap<Account, Integer> numberOfUsageForEachAccount;
    private ArrayList<Account> discountIsForTheseAccounts;
    private ArrayList<Customer> containingCustomers;

    public CodedOff(String offBarcode, String startTime, String endTime, double offAmount, int usageTime, ArrayList<Customer> containingCustomers) {
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
}
