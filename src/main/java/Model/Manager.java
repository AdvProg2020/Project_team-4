package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager extends Account {

    private static HashMap<String, Manager> managers;

    private static ArrayList<Request> registerSellerAccountRequests;
    private static ArrayList<Request>  editProductsRequests;
    private static ArrayList<Request> editOffRequests;
    private static ArrayList<Account> allAccounts;
    private static ArrayList<Category> categories;

    private static ArrayList<CodedOff> offCodes;

    public Manager(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord, double credit) {
        super(userName, firstName, lastName, email, phoneNumber, passWord, credit);
    }

    public void editOffCode(CodedOff offCode) {
        //offCodes.get(offCode)
    }

    public static ArrayList<Request> getRegisterSellerAccountRequests() {
        return registerSellerAccountRequests;
    }

    public static ArrayList<Request> getEditProductsRequest() {
        return editProductsRequests;
    }

    public static ArrayList<Request> getEditOffRequests() {
        return editOffRequests;
    }

    public void addOffCode() {

    }


    public ArrayList<Account> getAllAccounts() {
        return allAccounts;
    }

    public void deleteAnAccount() {

    }

    public void addMangerAccount() {

    }

    public void editACategory() {

    }

    public void addACategory() {

    }
}
