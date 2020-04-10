package Model;

import java.util.ArrayList;

public abstract class Account {
    protected static ArrayList<Account> allAccounts = new ArrayList<Account>();
    protected String userName;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String passWord ;
    protected ArrayList<CodedOff> offCodes;
    protected double credit;
    protected ArrayList<BuyLog> sellOrBuyHistory;
    protected ArrayList<BuyLog> buyOrSellLogs;



    public Account(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord, double credit) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.offCodes = new ArrayList<CodedOff>();
        this.credit = credit;
        this.sellOrBuyHistory = new ArrayList<BuyLog>();
        this.buyOrSellLogs = new ArrayList<BuyLog>();
        allAccounts.add(this);
    }

    public static Account getAccountWithName(String name) {
        for (Account account : allAccounts) {
            if (account.getUserName().equalsIgnoreCase(name)) {
                return account;
            }
        }
        return null;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName   = lastName;
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

    public ArrayList<Account> getAllAccounts() {
        return allAccounts;
    }

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

    public ArrayList<CodedOff> getOffCodes() {
        return offCodes;
    }

    public double getCredit() {
        return credit;
    }

    public ArrayList<BuyLog> getSellOrBuyHistory() {
        return sellOrBuyHistory;
    }

    public ArrayList<BuyLog> getBuyOrSellLogs() {
        return buyOrSellLogs;
    }
}
