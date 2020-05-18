package Model;
import java.io.File;

public abstract class Account extends SaveAble {

    protected String userName;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String passWord ;

    protected double credit;


    public Account(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public static Account getAccountWithName(String name){
        Account account;
        account = (Account) SaveAndLoad.getSaveAndLoad().readJSON(name, Manager.class);
        if(account != null){
            return account;
        }
        account = (Account) SaveAndLoad.getSaveAndLoad().readJSON(name, Customer.class);
        if(account != null){
            return account;
        }
        account = (Account) SaveAndLoad.getSaveAndLoad().readJSON(name, Seller.class);
        return account;
    }

    public static void deleteAccount(Account account) {
        File file = new File(account.getClass() + "\\" + account.getUserName());
        file.delete();
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

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    @Override
    protected String getName() {
        return userName;
    }
}