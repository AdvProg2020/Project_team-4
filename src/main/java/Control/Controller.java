package Control;

import Model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Controller {

    private final static Controller ourController = new Controller();

    private static Account loggedInAccount = null;

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
                Seller.addANewSeller("kljsadfk", username, password);
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

    public Account controllerShowUser(String username) {
        return Account.getAccountWithName(username);
    }

    public void controllerDeleteAnUser(String username) {
        Account.deleteAccount((Account) Account.getAccountWithName(username));
    }

    public void controllerRemoveProduct(String productName) {
        Manager.remove(Product.getProductWithName(productName));
    }

    public void controllerCreateOffCode(String barcode, Date startingTime, Date endingTime, double offAmount, int usageTimes, String containingCustomers) {
        new Off(barcode, startingTime, endingTime, offAmount);
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

    public ArrayList<Request> showAllRequests() {
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

    /////////////////////////////////////////////////////////////////chi kar mikone
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

    public static Account getLoggedInAccount() {
        return loggedInAccount;
    }


    public static boolean editField(String field) {
        return true;
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

