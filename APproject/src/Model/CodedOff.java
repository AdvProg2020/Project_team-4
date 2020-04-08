package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class CodedOff {
    private String offBarcode;
    private String startTime;
    private String endTime;
    private double offAmount;
    private HashMap<Account, Integer> numberOfUsageForEachAccount;
    private ArrayList<Account> discountIsForTheseAccounts;

}
