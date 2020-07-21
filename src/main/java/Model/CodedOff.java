package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class CodedOff extends SaveAble {
    private static ArrayList<CodedOff> allOfCodes = new ArrayList<>();
    private String offBarcode;
    private String startTime;
    private String endTime;
    private String offAmount;
    private String usageTime;
    private String percent;
    private HashMap<String, Integer> numberOfUsageForEachUserName;
    private ArrayList<String> discountIsForTheseUserNames;
    private ArrayList<String> containingUserNames;

    public CodedOff(String startTime, String endTime, String offAmount, String percent, String usageTime, ArrayList<String> containingCustomers) {
        this.offBarcode = Product.givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect();
        this.startTime = startTime;
        this.endTime = endTime;
        this.offAmount = offAmount;
        this.percent = percent;
        this.usageTime = usageTime;
        this.containingUserNames = new ArrayList<>(containingCustomers);
        this.numberOfUsageForEachUserName = new HashMap<String, Integer>();
        this.discountIsForTheseUserNames = new ArrayList<String>();
        allOfCodes.add(this);
        SaveAndLoad.getSaveAndLoad().writeJSON(allOfCodes, ArrayList.class.toString(), "allOffCodes");
    }

    public static void removeOffCode(String offCode) {
        allOfCodes.remove(CodedOff.getOffCodeWithName(offCode));
        SaveAndLoad.getSaveAndLoad().saveGenerally();
    }

    public static ArrayList<CodedOff> getAllOfCodes() {
        return allOfCodes;
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

    public String getPercent() {
        return percent;
    }

    public String getOffAmount() {
        return offAmount;
    }

    public String getUsageTime() {
        return usageTime;
    }

    public static ArrayList<CodedOff> getAllDiscounts() {
        return allOfCodes;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public HashMap<String, Integer> getNumberOfUsageForEachUserName() {
        return numberOfUsageForEachUserName;
    }

    public ArrayList<String> getDiscountIsForTheseUserNames() {
        return discountIsForTheseUserNames;
    }

    public ArrayList<String> getContainingUserNames() {
        return containingUserNames;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setOffAmount(String offAmount) {
        this.offAmount = offAmount;
    }

    public void setUsageTime(String usageTime) {
        this.usageTime = usageTime;
    }

    public void setPercent(String percent) {
        this.percent = percent;
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
