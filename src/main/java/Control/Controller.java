package Control;

import Model.*;
import com.sun.tools.javac.jvm.Code;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

public class Controller {
    private final static Controller ourController = new Controller();

    public static Controller getOurController() {
        return ourController;
    }

    public int controllerNewAccount(String type, String username, String password){
        if (Account.getAccountWithName(username) != null){
            return 4;
        }
        switch (type) {
            case "customer":
                Customer.newCustomer(username, password);
                return 1;
            case "seller":
                // id!
                Seller.addANewSeller("uhyghuj59", username, password);
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

    public int controllerLogin(String username, String password){
        if(Account.getAccountWithName(username) == null){
            return 2;
        }if(Account.getAccountWithName(username).getPassWord().equals(password)){
            Account.login((Account) (Account.getAccountWithName(username)));
            return 1;
        }
        return 3;
    }

    public int requestLogout(){
        if(Account.logout()){
            return 1;
        }
        return 2;
    }

    public Account controllerShowUser(String username) {
        return (Account) Account.getAccountWithName(username);
    }

    public void controllerDeleteAnUser(String username) {
        Account.deleteAccount((Account) Account.getAccountWithName(username));
    }

    public void controllerCreateNewManagerAccountFromManager(String username, String firstName, String lastName, String email, String phoneNumber, String password){
        // voroodi ha kheili ezafie!
        Manager.addANewManager(password, username, true);
    }

    public void controllerRemoveProduct(String productName){
        Manager.remove(Product.getProductWithName(productName));
    }

    public void controllerCreateOffCode(String barcode, String startingTime, String endingTime, double offAmount, int usageTimes, String containingCustomers){
        new Off(barcode, startingTime, endingTime, offAmount);
    }

    public ArrayList<CodedOff> getAllDiscounts(){
        return CodedOff.getAllDiscounts();
    }

    public CodedOff controllerGetDiscount(String offCodeName) {
        return CodedOff.getOffCodeWithName(offCodeName);
    }

    public void controllerRemoveDiscount(String offCodeName) {
        CodedOff.removeOffCode(CodedOff.getOffCodeWithName(offCodeName));
    }

    public ArrayList<CodedOff> showAllDiscountCodes(){
        return CodedOff.getAllDiscounts();
    }



    public void showCart() {
    }

    public void showProducts() {
        
    }

    public void showProduct() {
        
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
    }

    public void showCustomerBalance() {
    }

    public void showCustomerDiscountCodes() {
    }

    public int controllerCreateNewManagerAccountFromManager(String s, String s1) {
        return 0;
    }

    public void createCategory(String name, String subCategories, String tags, String productsList) {
    }

    public boolean showAllRequests() {
        return false;
    }

    public void acceptRequest(Request request) {
    }

    public void declineRequest(Request request) {
    }

    public boolean getDiscount(String s) {
        return false;
    }

    public void removeDiscount(String s) {
    }

    public int requestAddProductToCart(String productId) {
        return 0;
    }

    public Collection<? extends Seller> requestProductSeller(String productId) {
        return null;
    }
}

