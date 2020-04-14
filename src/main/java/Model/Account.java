package Model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
public abstract class Account {
    private static Account loggedInAccount;
    //protected static ArrayList allAccounts = new ArrayList();
    protected String userName;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String passWord ;
    protected ArrayList offCodes;
    protected double credit;
    protected ArrayList sellOrBuyHistory;
    protected ArrayList buyOrSellLogs;
    public Account(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        this.offCodes = new ArrayList();
        this.sellOrBuyHistory = new ArrayList();
        this.buyOrSellLogs = new ArrayList();
//allAccounts.add(this);
    }
    public static Account getAccountWithName(String name) throws FileNotFoundException {
        return SaveDataAndGetSystemOnBack.readJSONAccount(name);
    }
    public static void login(Account account) {
        loggedInAccount = account;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    //public ArrayList getAllAccounts() {
// return allAccounts;
//}
    public String getUserName() {
        return userName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getPassWord() {
        return passWord;
    }
    public ArrayList getOffCodes() {
        return offCodes;
    }
    public double getCredit() {
        return credit;
    }
    public ArrayList getSellOrBuyHistory() {
        return sellOrBuyHistory;
    }
    public ArrayList getBuyOrSellLogs() {
        return buyOrSellLogs;
    }
    public void setCredit(double credit) {
        this.credit = credit;
    }
}