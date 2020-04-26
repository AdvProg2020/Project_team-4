package Model;

import java.io.File;
import java.util.ArrayList;

public class Manager extends Account {

    private static boolean isFirstManagerCreatedOrNot;

    private static ArrayList<Manager> allManagers;
    private static ArrayList<Manager> managers;

    private static ArrayList<RequestANewSellerAccount> registerSellerAccountRequests;
    private static ArrayList<RequestProduct>  editProductsRequests;
    private static ArrayList<RequestOff> editOffRequests;
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

    public static ArrayList<RequestANewSellerAccount> getRegisterSellerAccountRequests() {
        return registerSellerAccountRequests;
    }

    public static ArrayList<RequestProduct> getEditProductsRequest() {
        return editProductsRequests;
    }

    public static ArrayList<RequestOff> getEditOffRequests() {
        return editOffRequests;
    }

    public static ArrayList<Request> getAllRequests() {
        ArrayList<Request> allRequests = new ArrayList<>();
        allRequests.addAll(registerSellerAccountRequests);
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
            accountRequestAccept((RequestANewSellerAccount) request);
            return true;
        } else if (request.getRequestType() == RequestType.OFF) {
            editOff((RequestOff) request);
            return true;
        } else if (request.getRequestType() == RequestType.PRODUCT){
            editProduct((RequestProduct) request);
            return true;
        }
        return false;
    }

    private static boolean accountRequestAccept(RequestANewSellerAccount request) {
        if (registerSellerAccountRequests.contains(request)) {
            SaveAndLoad.getSaveAndLoad().writeJSONAccount(new Customer(request.getUserName(), request.getPassWord()));
            return true;
        }
        return false;
    }

    private static boolean editProduct(RequestProduct request) {
        if (editProductsRequests.contains(request)) {
            new Product(request.getProduct());
            editProductsRequests.remove(request);
            return true;
        }
        return false;
    }

    private static boolean editOff(RequestOff request) {
        if (editOffRequests.contains(request)) {
            new Off(request.getOff().getOffBarcode(), request.getOff().getStartDate(), request.getOff().getEndDate(), request.getOff().getOffAmount());
            editOffRequests.remove(request);
            return true;
        }
        return false;
    }

    public static boolean declineRequest(Request request) {
        if (editOffRequests.contains(request)) {
            editOffRequests.remove(request);
            return true;
        } else if (editProductsRequests.contains(request)) {
            editProductsRequests.remove(request);
            return true;
        } else if (registerSellerAccountRequests.contains(request)) {
            registerSellerAccountRequests.remove(request);
            return true;
        }
        return false;
    }

    public static void setRegisterSellerAccountRequest(Request registerSellerAccountRequests) {
        Manager.registerSellerAccountRequests.add((RequestANewSellerAccount) registerSellerAccountRequests);
    }

    public static void setEditProductsRequest(Request editProductsRequests) {
        Manager.editProductsRequests.add((RequestProduct) editProductsRequests);
    }

    public static void setEditOffRequests(Request editOffRequests) {
        Manager.editOffRequests.add((RequestOff) editOffRequests);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passWord='" + passWord + '\'' +
                ", credit=" + credit +
                '}';
    }
}
