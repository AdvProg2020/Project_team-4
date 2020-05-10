package Control;

import Model.*;
import View.Menu.Menu;
import View.Outputs;
import com.google.gson.reflect.TypeToken;


import java.io.File;
import java.lang.reflect.Type;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;

import static Model.Product.getProductWithName;
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

    public int controllerDeleteAnUser(String username) {
        Account account = (Account) Account.getAccountWithName(username);
        if(account == null){
            return 2;
        }
        Account.deleteAccount(account);
        return 1;
    }

    public boolean controllerRemoveProduct(String productName) {
        return Product.removeProduct(getProductWithName(productName));
    }

    public int controllerCreateOffCode(String barcode, Matcher startDate, Matcher expireDate, String maximumOffAmount, String percentOfOff, String usageTimes, ArrayList<Customer> containingCustomers) {
        try {
            LocalDateTime start = LocalDateTime.of(Integer.parseInt(startDate.group(1)), Integer.parseInt(startDate.group(2)), Integer.parseInt(startDate.group(3)), Integer.parseInt(startDate.group(4)), Integer.parseInt(startDate.group(5)));
            LocalDateTime end = LocalDateTime.of(Integer.parseInt(expireDate.group(1)), Integer.parseInt(expireDate.group(2)), Integer.parseInt(expireDate.group(3)), Integer.parseInt(expireDate.group(4)), Integer.parseInt(expireDate.group(5)));
            if (start.compareTo(end) > 0) {
                return 2;
            }
            new CodedOff(barcode, start, end, Integer.parseInt(maximumOffAmount), Integer.parseInt(percentOfOff), Integer.parseInt(usageTimes), new ArrayList<Customer>(containingCustomers));
            return 1;
        }catch (Exception e){
            return 3;
        }
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

    public int newComment(String comment){
        return 0;
    }

    public String showCart() {
        return (((Customer)loggedInAccount).getCart()).toString();
    }

    public void increaseOrDecreaseProductNo(String productId, int n) {
        if (getProductWithName(productId).isExistsOrNot()) {
            getProductWithName(productId).setAmountOfExist(-n);
        } else {
            System.out.println("this product in not availAble any more");
            return;
        }
        ((Customer)loggedInAccount).setNumberOfProductInCart(getProductWithName(productId), n);
    }

    public ArrayList<Product> showProducts() {
        return Product.showAllProducts();
    }

    public Product showProduct(String productName) {
        return getProductWithName(productName);
    }

    public void showOrder(String s) {

    }

    public void rateProduct() {

    }

    public void purchase(String address, String phoneNumber) {

    }

    public void checkOffCodeAndSet(String offCode) {

    }

    public boolean pay(String offCode) {
        if (((Customer)loggedInAccount).pay(offCode)) {
            return true;
        }
        return false;
    }

    public void showCustomerBalance() {

    }

    public void showCustomerDiscountCodes() {

    }

    public int controllerCreateNewManagerAccountFromManager(String username, String password) {
        if(Account.getAccountWithName(username) != null){
            return 4;
        }
        Manager.addANewManager(username, password, true);
        return 1;
    }

    public void createCategory(String name, ArrayList<String> subCategories, ArrayList<String> tags, ArrayList<String> productsList) {
        ArrayList<Product> products = new ArrayList<>();
        for (String product: productsList) {
            if (getProductWithName(product) != null) {
                products.add(getProductWithName(product));
            }
        }
        ArrayList<Category> subCategory = new ArrayList<>();
        for (String category: subCategories) {
            if (Category.getCategoryByName(category) != null) {
                subCategory.add(Category.getCategoryByName(category));
            }
        }
        new Category(name, tags, products, subCategory);
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

    public static void readOffCodesFromFile() {
        Type offCodesListType = new TypeToken<ArrayList<CodedOff>>(){}.getType();
        try {
            CodedOff.getAllDiscounts().addAll((Collection<? extends CodedOff>) SaveAndLoad.getSaveAndLoad().readJSONByType("offCodes", offCodesListType));
        } catch (Exception e) {
            Outputs.printReadFileResult("Didn't read the array of all offCodes");
        }
        //readArrayFromFile(CodedOff.getAllDiscounts(), "offCodes");
    }

    public static void readRequestsFromFile() {
        Type sellerAccountRequestListType = new TypeToken<ArrayList<RequestANewSellerAccount>>(){}.getType();
        try {
            Manager.getRegisterSellerAccountRequests().addAll((Collection<? extends RequestANewSellerAccount>) SaveAndLoad.getSaveAndLoad().readJSONByType("registerSellerAccountRequests", sellerAccountRequestListType));
        } catch (Exception e) {
            Outputs.printReadFileResult("Didn't read the array of all RequestANewSellerAccount");
        }
        Type offRequestListType = new TypeToken<ArrayList<RequestOff>>(){}.getType();
        try {
            Manager.getEditOffRequests().addAll((Collection<? extends RequestOff>) SaveAndLoad.getSaveAndLoad().readJSONByType("editOffRequests", offRequestListType));
        } catch (Exception e) {
            Outputs.printReadFileResult("Didn't read the array of all RequestOff");
        }
        Type productRequestListType = new TypeToken<ArrayList<RequestProduct>>(){}.getType();
        try {
            Manager.getEditProductsRequests().addAll((Collection<? extends RequestProduct>) SaveAndLoad.getSaveAndLoad().readJSONByType("editProductsRequests", productRequestListType));
        } catch (Exception e) {
            Outputs.printReadFileResult("Didn't read the array of all RequestProduct");
        }

    }

    public static void readOffsFromFile() {
        Type offListType = new TypeToken<ArrayList<Off>>(){}.getType();
        try {
            Off.getAllOffs().addAll((Collection<? extends Off>) SaveAndLoad.getSaveAndLoad().readJSONByType("allOffs", offListType));
        } catch (Exception e) {
            System.out.println("Didn't read the array of all offs");
        }
    }

    public static void readProductsFromFile() {
        Type productListType = new TypeToken<ArrayList<Product>>(){}.getType();
        try {
            Product.getAllProducts().addAll((Collection<? extends Product>) SaveAndLoad.getSaveAndLoad().readJSONByType("allProducts", productListType));
        } catch (Exception e) {
            System.out.println("Didn't read the array of all products");
        }
    }

    public static void readCategoriesFromFile() {
        Type categoryListType = new TypeToken<ArrayList<Category>>(){}.getType();
        try {
            Category.getAllCategories().addAll((Collection<? extends Category>) SaveAndLoad.getSaveAndLoad().readJSONByType("allCategories", categoryListType));
        } catch (Exception e) {
            System.out.println("Didn't read the array of all categories");
        }
    }

//    public static void readArrayFromFile(Collection<?> array, String name) {
//        Type type = new TypeToken<ArrayList<SaveAble>>(){}.getType();
//        try {
//            array.addAll((Collection<?>) SaveAndLoad.getSaveAndLoad().readJSONByType(name, type));
//        } catch (Exception e) {
//            System.out.println("Didn't read the array of all offCodes");
//        }
//    }
    

    public CodedOff getDiscount(String s) {
        for (CodedOff discountCode: CodedOff.getAllDiscounts()) {
            if (discountCode.getOffBarcode().equalsIgnoreCase(s)) {
                return discountCode;
            }
        }
        return null;
    }

    public void removeDiscount(String offName) {
        Manager.removeDiscount(offName);
    }

    public int requestAddProductToCart(String productId) {
        Product product;
        if((product = Product.getProductWithName(productId)) == null)
            return 0;

        Account account = getLoggedInAccount();
        if(!account.getClass().equals(Customer.class)){
            return 2;
        }
        Customer customer = (Customer) account;
        return customer.addProductToCart(product);

    }

    public int calculateCartCost() {
        return ((Customer)loggedInAccount).getCartMoney();
    }

    public Collection<? extends Seller> requestProductSeller(String productId) {
        return null;
    }

    public Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public void editField(String field) {
        System.out.println("Enter your new amount for the field you choose");
        Matcher newAmount = getField("Please enter a valid string", "(\\S+)");
        switch (field.toLowerCase()) {
            case "firstname":
                loggedInAccount.setFirstName(newAmount.group(1));
                return;
            case "lastname":
                loggedInAccount.setLastName(newAmount.group(1));
                return;
            case "credit" :
                loggedInAccount.setCredit(Double.parseDouble(newAmount.group(1)));
                return;
            case "phonenumber" :
                loggedInAccount.setPhoneNumber(newAmount.group(1));
                return;
            case "email" :
                loggedInAccount.setEmail(newAmount.group(1));
                return;
            case "password" :
                loggedInAccount.setPassWord(newAmount.group(1));
        }
    }

    public int requestAddProduct(String name, String company, double cost, String category, String description) {
        return 0;
    }

    public void removeCategory(String name) {
        Category.deleteCategoryAndProducts(name);
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

    public String[] getusers(Class className) {
        File f = new File(String.valueOf(className));
        return f.list();
    }

    public static void createProductRequest(String name, String company, int cost, String categoryName, String description, int amountOfExist, ArrayList<String> tags, ArrayList<String> sellersNames) {
        Category category = Category.getCategoryByName(categoryName);
        ArrayList<Seller> sellers = new ArrayList<>();
        for (String sellerName: sellersNames) {
            if (Seller.getAccountWithName(sellerName) != null) {
                sellers.add((Seller) Seller.getAccountWithName(sellerName));
            }
        }
        Request requestProduct = new RequestProduct(RequestType.PRODUCT, new Product(name, company, cost, category, description, amountOfExist, tags, sellers));
    }



    public ArrayList<Off> getAllOffsOfSeller() {
        ArrayList<Off> sellersOff = new ArrayList<>();
        for (Off off: Off.getAllOffs()) {
            if (((Seller)loggedInAccount).getOffs().contains(off)) {
                sellersOff.add(off);
            }
        }
        return sellersOff;
    }

    public Off getOffByName(String name) {
        ArrayList<Off> sellersOff = getAllOffsOfSeller();
        for (Off off: sellersOff) {
            if (off.getOffBarcode().equalsIgnoreCase(name)) {
                return off;
            }
        }
        return null;
    }

    public void createOrEditOffRequest(ArrayList<String> products, Matcher startDate, Matcher endDate, int offAmount) {
        ArrayList<Product> productsToAddTO = new ArrayList<>();
        for (String productBarcode: products) {
            if (Product.getAllProducts().contains(getProductWithName(productBarcode)) && !getProductWithName(productBarcode).isInOffOrNot()) {
                productsToAddTO.add(getProductWithName(productBarcode));
                getProductWithName(productBarcode).setInOffOrNot(true);
            }
        }
        LocalDateTime start = LocalDateTime.of(Integer.parseInt(startDate.group(1)), Integer.parseInt(startDate.group(2)), Integer.parseInt(startDate.group(3)), Integer.parseInt(startDate.group(4)), Integer.parseInt(startDate.group(5)));
        LocalDateTime end = LocalDateTime.of(Integer.parseInt(endDate.group(1)), Integer.parseInt(endDate.group(2)), Integer.parseInt(endDate.group(3)), Integer.parseInt(endDate.group(4)), Integer.parseInt(endDate.group(5)));
        Off off = new Off(start, productsToAddTO, end, offAmount);
        Off.getAllOffs().remove(off);
        SaveAndLoad.getSaveAndLoad().writeJSON(Off.getAllOffs(), ArrayList.class, "allOffs");
        new RequestOff(RequestType.OFF, off);
    }

    public void removeOff(String name) {
        if (Off.getAllOffs().contains(Off.getOffByBarcode(name))) {
            Off.getAllOffs().remove(Off.getOffByBarcode(name));
            for (Product product: Off.getOffByBarcode(name).getProducts()) {
                product.setInOffOrNot(false);
                product.offTheCost((-product.getCost() * 100 /(100 - Off.getOffByBarcode(name).getOffAmount())) * Off.getOffByBarcode(name).getOffAmount());
            }
        }
    }

    public void setCustomersField(String firstName, String lastName, String phoneNumber, String email) {
        Customer loggedInCustomer = (Customer) loggedInAccount;
        loggedInCustomer.setFirstName(firstName);
        loggedInCustomer.setLastName(lastName);
        loggedInCustomer.setPhoneNumber(phoneNumber);
        loggedInCustomer.setEmail(email);
    }

    public void setCustomerAddress(String address) {
        ((Customer) loggedInAccount).setAddress(address);
    }

}

