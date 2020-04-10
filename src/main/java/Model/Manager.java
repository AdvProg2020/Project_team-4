package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager extends Account {

    private static boolean isTheFirstManagerCreatedOrNot;

    private static ArrayList<Manager> allManagers;

    public Manager(String userName, String passWord) {
        super(userName, passWord);
        allManagers.add(this);
        isTheFirstManagerCreatedOrNot = true;
    }

    private static ArrayList<Manager> managers;

    private static ArrayList<Request> registerSellerAccountRequests;
    private static ArrayList<Request>  editProductsRequests;
    private static ArrayList<Request> editOffRequests;
    private static ArrayList<Account> allAccounts;
    private static ArrayList<Category> categories;

    private static ArrayList<CodedOff> offCodes;

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

    public static boolean addANewManager(String userName, String passWord) {
        if (isTheFirstManagerCreatedOrNot){
            return false;
        }
        allManagers.add(new Manager(userName, passWord));
        return true;
    }

    public static boolean addANewSellerRequest(String userName, String passWord) {
        registerSellerAccountRequests.add(new RequestANewSellerAccount("Create a seller account", userName, passWord));
        return true;
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
