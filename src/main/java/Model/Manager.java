package Model;



import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Manager extends Account implements Serializable {

    private static boolean isFirstManagerCreatedOrNot = false;


    private static ArrayList<RequestANewSellerAccount> registerSellerAccountRequests = new ArrayList<>();
    private static ArrayList<RequestProduct> editProductsRequests = new ArrayList<>();
    private static ArrayList<RequestOff> editOffRequests = new ArrayList<>();
    private static ArrayList<SaveAble> allAccounts = new ArrayList<>();
    private static final ArrayList<SaveAble> categories = new ArrayList<>();




    public Manager(String userName, String passWord) {
        super(userName, passWord);
        SaveAndLoad.getSaveAndLoad().writeJSON(this, Manager.class.toString(), userName);
    }

    public static void removeDiscount(String offName) {
        if (CodedOff.getAllDiscounts().contains(CodedOff.getOffCodeWithName(offName))) {
            CodedOff.getAllDiscounts().remove(CodedOff.getOffCodeWithName(offName));
        }
        SaveAndLoad.getSaveAndLoad().writeJSON(CodedOff.getAllDiscounts(), ArrayList.class.toString(), "offCodes");
    }

    public void editOffCode(CodedOff offCode) {
        //offCodes.get(offCode)
    }

    public static ArrayList<RequestANewSellerAccount> getRegisterSellerAccountRequests() {
        return registerSellerAccountRequests;
    }

    public static ArrayList<RequestProduct> getEditProductsRequests() {
        return editProductsRequests;
    }


    public static ArrayList<RequestOff> getEditOffRequests() {
        return editOffRequests;
    }

    public static ArrayList<SaveAble> getAllRequests() {
        ArrayList<SaveAble> allRequests = new ArrayList<>();
        allRequests.addAll(registerSellerAccountRequests);
        allRequests.addAll(editOffRequests);
        allRequests.addAll(editProductsRequests);
        return allRequests;
    }

    public void addOffCode() {

    }

    public static boolean addANewManager(String userName, String passWord, Boolean isRequestFromManger) {
        File directory = new File(System.getProperty("user.dir") + "\\" + Manager.class);
        if (directory.isDirectory()) {
            String[] files = directory.list();
            if (files.length > 0) {
                System.out.println(System.getProperty("user.dir") + "\\" + Manager.class);
                isFirstManagerCreatedOrNot = true;
            }
            else {
                isFirstManagerCreatedOrNot = false;
            }
        }
        if ((!isFirstManagerCreatedOrNot) || isRequestFromManger){
            //System.out.println(System.getProperty("user.dir") + "\\" + Manager.class);
            new Manager(userName, passWord);
            isFirstManagerCreatedOrNot = false;
            return true;
        }
        return false;
    }

//    public static boolean addANewSellerRequest(String userName, String passWord, String requestId) {
//        registerSellerAccountRequests.add(new RequestANewSellerAccount(requestId, "Create a seller account", userName, passWord));
//        return true;
//    }


    public ArrayList<SaveAble> getAllAccounts() {
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


    public static void AnswerRequest(Request request, boolean acceptOrDecline) {

    }

    public static boolean acceptRequest(Request request) {
        if (request.getRequestType() == RequestType.ACCOUNT) {
            System.out.println("salam");
            accountRequestAccept((RequestANewSellerAccount) request);
            declineRequest(request);
            return true;
        } else if (request.getRequestType() == RequestType.OFF) {
            editOff((RequestOff) request);
            declineRequest(request);
            return true;
        } else if (request.getRequestType() == RequestType.PRODUCT){
            editProduct((RequestProduct) request);
            declineRequest(request);
            return true;
        }
        return false;
    }

    private static boolean accountRequestAccept(RequestANewSellerAccount request) {
        if (registerSellerAccountRequests.contains(request)) {
            new Seller(request.getUserName(), request.getPassWord());
            registerSellerAccountRequests.remove(request);
            SaveAndLoad.getSaveAndLoad().writeJSON(registerSellerAccountRequests, ArrayList.class.toString(), "registerSellerAccountRequests");
            return true;
        }
        return false;
    }

    public static boolean addANewSellerRequest(String userName, String passWord) {
        registerSellerAccountRequests.add(new RequestANewSellerAccount(RequestType.ACCOUNT, userName, passWord));
        SaveAndLoad.getSaveAndLoad().writeJSON(registerSellerAccountRequests, ArrayList.class.toString(), "registerSellerAccountRequests");
        return true;
    }

    private static boolean editProduct(RequestProduct request) {
        if (editProductsRequests.contains(request)) {
            if (request.getProductName().startsWith("productBarcode: ")) {
                Product product = Product.getProductWithBarcode(request.getProductName().substring(17));
                product.setAmountOfExist(request.getProductAmountOfExist());
                product.setCategoryTags(request.getProductCategoryTags());
                product.setCategory(request.getProductCategory());
                product.setCompany(request.getProductCompany());
                product.setCost(request.getProductCost());
                product.setDescription(request.getProductDescription());
                product.setTags(request.getProductTags());
            } else {
                Product product = Product.getProductWithBarcode(request.getProductName());
                if (Product.getAllProducts().contains(product)) {
                    Product.getAllProducts().remove(product);
                }
                new Product(request.getProduct());
                editProductsRequests.remove(request);
//                SaveAndLoad.getSaveAndLoad().writeJSON(editOffRequests, ArrayList.class, "editProductRequests");
                SaveAndLoad.getSaveAndLoad().saveGenerally();
                return true;
            }
        }
        return false;
    }

    private static boolean editOff(RequestOff request) {
        if (editOffRequests.contains(request)) {
            Off off = Off.getOffByBarcode(request.getOffName());
            if (Off.getAllOffs().contains(off)) {
                Off.getAllOffs().remove(off);
            }
            new Off(request.getOff().getStartDate(), request.getOff().getProducts(), request.getOff().getEndDate(), request.getOff().getOffAmount(), request.getOffName());
            editOffRequests.remove(request);
//            SaveAndLoad.getSaveAndLoad().writeJSON(editOffRequests, ArrayList.class, "editOffRequests");
            SaveAndLoad.getSaveAndLoad().saveGenerally();
            return true;
        }
        return false;
    }

    public static boolean declineRequest(Request request) {
        if (editOffRequests.contains(request)) {
            editOffRequests.remove(request);
            SaveAndLoad.getSaveAndLoad().writeJSON(editOffRequests, ArrayList.class.toString(), "editOffRequests");

            return true;
        } else if (editProductsRequests.contains(request)) {
            editProductsRequests.remove(request);
            SaveAndLoad.getSaveAndLoad().writeJSON(editProductsRequests, ArrayList.class.toString(), "editProductsRequests");

            return true;
        } else if (registerSellerAccountRequests.contains(request)) {
            registerSellerAccountRequests.remove(request);
            SaveAndLoad.getSaveAndLoad().writeJSON(registerSellerAccountRequests, ArrayList.class.toString(), "registerSellerAccountRequests");

            return true;
        }
        return false;
    }

    public static void setRegisterSellerAccountRequest(Request registerSellerAccountRequests) {
        //Manager.registerSellerAccountRequests.add((RequestANewSellerAccount) registerSellerAccountRequests);
    }

    public static void setEditProductsRequest(Request editProductsRequests) {
        Manager.editProductsRequests.add((RequestProduct) editProductsRequests);
    }

    public static void setEditOffRequests(Request editOffRequests) {
        Manager.editOffRequests.add((RequestOff) editOffRequests);
    }

    public static Request getRequestByName(String id) {
        for (Request request : registerSellerAccountRequests) {
            if (request.getName().equalsIgnoreCase(id)) {
                return request;
            }
        }
        for (Request request : editOffRequests) {
            if (request.getName().equalsIgnoreCase(id)) {
                return request;
            }
        }
        for (Request request : editProductsRequests) {
            if (request.getName().equalsIgnoreCase(id)) {
                return request;
            }
        }
        return null;
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
