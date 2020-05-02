package Control;

import Model.*;
import View.Menu.Menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;

import static View.Menu.Menu.getField;

public class Controller {

    private final static Controller ourController = new Controller();

    private final Scanner scanner = Menu.getScanner();

    private Account loggedInAccount = null;

    public static Controller getOurController() {
        return ourController;
    }

    public int controllerNewAccount(String type, String username, String password) {
        if (Account.getAccountWithName(username) != null) {
            return 4;
        }
        switch (type) {
            case "customer":
                Customer.newCustomer(username, password);
                return 1;
            case "seller":
                Manager.addANewSellerRequest(username, password);
                return 2;
            case "manager":
                if (!Manager.addANewManager(username, password, false)) {
                    return 3;
                }
                return 1;
            default:
                return 5;
        }
    }

    public int controllerLogin(String username, String password) {
        if (Account.getAccountWithName(username) == null) {
            return 2;
        }
        if (Account.getAccountWithName(username).getPassWord().equals(password)) {
            loggedInAccount = Account.getAccountWithName(username);
//            Account.login(Account.getAccountWithName(username));
            return 1;
        }
        return 3;
    }

    public int logout() {
        if (loggedInAccount == null) {
            return 1;
        }
        loggedInAccount = null;
        return 2;
    }

    public void controllerShowUser(String username) {
        System.out.println(Account.getAccountWithName(username));
    }

    public void controllerDeleteAnUser(String username) {
        Account.deleteAccount((Account) Account.getAccountWithName(username));
    }

    public boolean controllerRemoveProduct(String productName) {
        return Product.removeProduct(Product.getProductWithName(productName));
    }

    public void controllerCreateOffCode(String barcode, String startDate, String expireDate, String maximumOffAmount, String percentOfOff, String usageTimes, ArrayList containingCustomers) {
        new Off(barcode, startDate, expireDate, maximumOffAmount);
    }

    public ArrayList<CodedOff> getAllCodedOff() {
        return CodedOff.getAllDiscounts();
    }

    public CodedOff controllerGetCodedOff(String offCodeName) {
        return CodedOff.getOffCodeWithName(offCodeName);
    }

    public void controllerRemoveCodedOff(String offCodeName) {
        if (CodedOff.getOffCodeWithName(offCodeName) == null) {
            return;
        }
        CodedOff.removeOffCode(CodedOff.getOffCodeWithName(offCodeName));
    }

    public ArrayList<CodedOff> showAllDiscountCodes() {
        return CodedOff.getAllDiscounts();
    }


    public ArrayList<Product> showCart() {
        return (((Customer)loggedInAccount).getCart());
    }

    public ArrayList<Product> showProducts() {
        return Product.showAllProducts();
    }

    public Product showProduct(String productName) {
        return Product.getProductWithName(productName);
    }

    public void showOrder(String s) {

    }

    public void rateProduct() {

    }

    public void purchase(String address, String phoneNumber) {

    }

    public void checkOffCodeAndSet(String offCode) {

    }

    public void pay() {
        ((Customer)loggedInAccount).pay();
    }

    public void showCustomerBalance() {

    }

    public void showCustomerDiscountCodes() {

    }

    public int controllerCreateNewManagerAccountFromManager(String username, String password) {
        if(Account.getAccountWithName(username) != null){
            return 2;
        }
        Manager.addANewManager(username, password, true);
        return 1;
    }

    public void createCategory(String name, String subCategories, String tags, String productsList) {

    }

    public ArrayList<SaveAble> showAllRequests() {
        return Manager.getAllRequests();
    }

    public void acceptRequest(Request request) {
        Manager.acceptRequest(request);
    }

    public void declineRequest(Request request) {
        Manager.declineRequest(request);
    }

    public boolean getDiscount(String s) {
        return false;
    }

    public void removeDiscount(String offName) {
        Manager.removeDiscount(offName);
    }

    public int requestAddProductToCart(String productId) {
        ((Customer)loggedInAccount).addProductToCart(Product.getProductWithName(productId));
        return 1;
    }

    public Collection<? extends Seller> requestProductSeller(String productId) {
        return null;
    }

    public Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public int editField(String field) {
        System.out.println("Enter your new amount for the field you choose");
        Matcher newAmount = getField("Please enter a valid string", "(\\S+)");
        switch (field.toLowerCase()) {
            case "firstname":
                loggedInAccount.setFirstName(newAmount.group(1));
                return 1;
            case "lastname":
                loggedInAccount.setLastName(newAmount.group(1));
                return 1;
            case "credit" :
                loggedInAccount.setCredit(Double.parseDouble(newAmount.group(1)));
                return 1;
            case "phonenumber" :
                loggedInAccount.setPhoneNumber(newAmount.group(1));
                return 1;
            case "email" :
                loggedInAccount.setEmail(newAmount.group(1));
                return 1;
            case "password" :
                loggedInAccount.setPassWord(newAmount.group(1));
                return 1;
        }
        return 2;
    }

    public int requestAddProduct(String name, String company, double cost, String category, String description) {
        return 0;
    }

    public ArrayList requestCompanyInfo() {
        return null;
    }

    public ArrayList requestSalesHistoryInfo() {
        return null;
    }

    public ArrayList requestListOfProducts() {
        return null;
    }

    public boolean requestProductInfo() {
        return false;
    }

    public ArrayList requestProductBuyers() {
        return null;
    }

    public Customer requestLoggedInUser() {
        return null;
    }
}

