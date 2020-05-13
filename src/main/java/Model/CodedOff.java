package Model;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class CodedOff extends SaveAble {
    private static ArrayList<CodedOff> allOfCodes = new ArrayList<>();
    private String offBarcode;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double offAmount;
    private int usageTime;
    private int percent;
    private HashMap<String, Integer> numberOfUsageForEachUserName;
    private ArrayList<String> discountIsForTheseUserNames;
    private ArrayList<String> containingUserNames;

    public CodedOff(String offBarcode, LocalDateTime startTime, LocalDateTime endTime, double offAmount, int percent, int usageTime, ArrayList<String> containingCustomers) {
        this.offBarcode = offBarcode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.offAmount = offAmount;
        this.percent = percent;
        this.usageTime = usageTime;
        this.containingUserNames = new ArrayList<>(containingCustomers);
        this.numberOfUsageForEachUserName = new HashMap<String, Integer>();
        this.discountIsForTheseUserNames = new ArrayList<String>();
        allOfCodes.add(this);
        SaveAndLoad.getSaveAndLoad().writeJSON(allOfCodes, ArrayList.class, "offCodes");
    }

    public static void removeOffCode(String offCode) {
        File file = new File(CodedOff.getOffCodeWithName(offCode).getName());

        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
        allOfCodes.remove(CodedOff.getOffCodeWithName(offCode));
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
                ", containing users" + containingUserNames +
                ", numberOfUsageForEachAccount=" + numberOfUsageForEachUserName +
                ", discountIsForTheseAccounts=" + discountIsForTheseUserNames +
                '}';
    }

    @Override
    protected String getName() {
        return offBarcode;
    }

    public int getPercent() {
        return percent;
    }

    public double getOffAmount() {
        return offAmount;
    }

    public int getUsageTime() {
        return usageTime;
    }

    public static ArrayList<CodedOff> getAllDiscounts() {
        return allOfCodes;
    }

    public static CodedOff getOffCodeWithName(String name) {
        for (CodedOff offCode : allOfCodes) {
            if (offCode.getOffBarcode().equalsIgnoreCase(name)) {
                return offCode;
            }
        }
        return null;
    }
}
