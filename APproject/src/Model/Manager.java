package Model;

import java.util.ArrayList;

public class Manager extends Account{

    private ArrayList<Request> registerSellerAccountRequests;
    private ArrayList<Request>  editProductsRequest;
    private ArrayList<Request> editOffRequests;
    private ArrayList<Account> allAccounts;
    private ArrayList<Category> categories;

    private ArrayList<CodedOff> offCodes;

    public void editOffCode() {

    }

    public ArrayList<Request> getRegisterSellerAccountRequests() {
        return registerSellerAccountRequests;
    }

    public ArrayList<Request> getEditProductsRequest() {
        return editProductsRequest;
    }

    public ArrayList<Request> getEditOffRequests() {
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
