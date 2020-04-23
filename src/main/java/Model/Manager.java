package Model;

import java.io.File;
import java.util.ArrayList;

public class Manager extends Account {

    private static boolean isFirstManagerCreatedOrNot;

    private static ArrayList<Manager> allManagers;
    private static ArrayList<Manager> managers;

    private static ArrayList<Request> registerSellerAccountRequests;
    private static ArrayList<Request>  editProductsRequests;
    private static ArrayList<Request> editOffRequests;
    private static ArrayList<Account> allAccounts;
    private static ArrayList<Category> categories;

    private static ArrayList<CodedOff> offCodes;


    public Manager(String userName, String passWord) {
        super(userName, passWord);
        allManagers.add(this);
        isFirstManagerCreatedOrNot = false;
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

    public static void remove(Product productWithName) {

    }

    public static boolean addANewManager(String userName, String passWord, Boolean isRequestFromManger) {
        if (isFirstManagerCreatedOrNot && isRequestFromManger){
            new Manager(userName, passWord);
            return true;
        }
        return false;
    }

    public static boolean addANewSellerRequest(String userName, String passWord, String requestId) {
        registerSellerAccountRequests.add(new RequestANewSellerAccount(requestId, "Create a seller account", userName, passWord));
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

    public static void removeProduct(Product product) {
        File file = new File(product.getName());

        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
        Product.getProducts() != null;
        Product.getProductsInProducts().remove(product);
    }

    public static void AnswerRequest(Request request, boolean acceptOrDecline) {

    }




}
