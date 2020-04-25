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

    public static ArrayList<Request> getAllRequests() {
        ArrayList<Request> allRequests = new ArrayList<>(registerSellerAccountRequests);
        allRequests.addAll(editOffRequests);
        allRequests.addAll(editProductsRequests);
        return allRequests;
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
        Product.removeProduct(product);
    }

    public static void AnswerRequest(Request request, boolean acceptOrDecline) {

    }

    public static boolean acceptRequest(Request request) {
        if (request.getRequestType() == RequestType.ACCOUNT) {
            accountRequestAccept();
            return true;
        } else if (request.getRequestType() == RequestType.OFF) {
            editOff();
            return true;
        } else if (request.getRequestType() == RequestType.PRODUCT){
            editProduct();
            return true;
        }
        return false;
    }

    private static boolean accountRequestAccept(Request request) {
        if (registerSellerAccountRequests.contains(request)) {

        }
        return false;
    }

    public static void setRegisterSellerAccountRequest(Request registerSellerAccountRequests) {
        Manager.registerSellerAccountRequests.add(registerSellerAccountRequests);
    }

    public static void setEditProductsRequest(Request editProductsRequests) {
        Manager.editProductsRequests.add(editProductsRequests);
    }

    public static void setEditOffRequests(Request editOffRequests) {
        Manager.editOffRequests.add(editOffRequests);
    }
}
