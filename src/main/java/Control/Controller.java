package Control;

import Model.*;
import View.Outputs;
import com.google.gson.reflect.TypeToken;
import org.example.App;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;

import static Model.Product.getProductWithBarcode;
import static View.Menu.Menu.getField;

public class Controller {

//    private static Controller ourController;
    private Account currentAccount = null;

    public static void main(String[] args) throws IOException {
        int port = 8888;
        //network
        ServerSocket serverSocket = new ServerSocket(port);
        handleData();
        while(true){
            Socket socket = serverSocket.accept();
            ControllerThread ct = new ControllerThread(socket);
            ct.run();
        }
    }



    private static void handleData() {
        File file1 = new File(String.valueOf("class Model.Customer"));
        file1.mkdir();
        File file2 = new File("class Model.Manager");
        file2.mkdir();
        File file3 = new File("class Model.Seller");
        file3.mkdir();
        File file4 = new File("class java.util.ArrayList");
        file4.mkdirs();
        File file5 = new File("Image");
        file5.mkdirs();
        Controller.readOffCodesFromFile();
        Controller.readRequestsFromFile();
        Controller.readOffsFromFile();
        Controller.readProductsFromFile();
        Controller.readCategoriesFromFile();
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
            if (Account.getAccountWithName(username).getClass().equals(Customer.class)) {
                Set<String> barcodes = ((Customer) currentAccount).getCart().keySet();
                ArrayList<String> sellers = ((Customer) currentAccount).getSellersOfProductsOfTheCart();
                currentAccount = Account.getAccountWithName(username);
                for (String barcode: barcodes) {
                    this.requestAddProductToCart(barcode);
                }
                for (String name: sellers) {
                    this.setNameOfSellerOfProductAddedToCart(name);
                }
            }else{
                currentAccount = Account.getAccountWithName(username);
            }
            SaveAndLoad.getSaveAndLoad().saveGenerally();
//            Account.login(Account.getAccountWithName(username));
            return 1;
        }
        return 3;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public int logout() {
        if (currentAccount == null) {
            return 1;
        }
        App.defaultCustomer = new Customer("default", "123");
        currentAccount = App.defaultCustomer;
        return 2;
    }

    public void controllerShowUser(String username) {
        System.out.println(Account.getAccountWithName(username));
    }

    public int controllerDeleteAnUser(String username) {
        Account account = Account.getAccountWithName(username);
        if(account == null){
            return 2;
        }
        Account.deleteAccount(account);
        return 1;
    }

    public boolean controllerRemoveProduct(String productName) {
        boolean result = Product.removeProduct(getProductWithBarcode(productName));
        SaveAndLoad.getSaveAndLoad().saveGenerally();
        return result;
    }

    public int controllerCreateOffCode(String start, String end, String maximumOffAmount, String percentOfOff, String usageTimes, ArrayList<String> containingCustomers) {
        try {
            if (start.compareTo(end) > 0) {
                return 2;
            }
            ////parametre akhar arrayList new bood fek kardam hamooni pas bedim behtare
            CodedOff codedOff = new CodedOff(start, end, maximumOffAmount, percentOfOff, usageTimes, containingCustomers);
            for (String customer: containingCustomers) {
                Customer customer1 = ((Customer)Customer.getAccountWithName(customer));
                customer1.addOffCode(codedOff.getOffBarcode());
                customer1.setUsageOfOffCodes(Integer.valueOf(usageTimes));
                SaveAndLoad.getSaveAndLoad().writeJSON(customer1, Customer.class.toString(), customer1.getUserName());
                SaveAndLoad.getSaveAndLoad().saveGenerally();
            }
            return 1;
        }catch (Exception e){
            e.printStackTrace();
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
        CodedOff.removeOffCode(offCodeName);
    }

    public ArrayList<CodedOff> showAllDiscountCodes() {
        return CodedOff.getAllDiscounts();
    }

    public void newComment(String comment, Product product, String name){
        Comment comment1 = new Comment(currentAccount.getUserName(), product.getProductBarcode(), comment, false);
        product.addComment(comment1);
    }


    public String showCart() {
        return (((Customer) currentAccount).getCart()).toString();
    }

    public void increaseOrDecreaseProductNo(String productId, int n) {
        if (getProductWithBarcode(productId)!=null && getProductWithBarcode(productId).isExistsOrNot()) {
            getProductWithBarcode(productId).setAmountOfExist(-n);
        } else {
            System.out.println("this product in not availAble any more");
            return;
        }
        ((Customer) currentAccount).setNumberOfProductInCart(productId, n);
        SaveAndLoad.getSaveAndLoad().saveGenerally();
    }

    public ArrayList<Product> showProducts() {
        return Product.showAllProducts();
    }

    public Product showProduct(String productName) {
        return getProductWithBarcode(productName);
    }

//    public History showOrderInCustomerMenu(String s) {
//        return ((Customer)loggedInAccount).getHistoryById(s);
//    }

    public void rateProduct(String productId, int rate) {
        Product product = getProductWithBarcode(productId);
        product.setAverageScore(rate);
    }

    public void purchase(String address, String phoneNumber) {

    }

    public void checkOffCodeAndSet(String offCode) {

    }

    public boolean pay(String offCode) {
        if (((Customer) currentAccount).pay(offCode)) {
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

    public Category createCategory(String name, ArrayList<String> subCategories, ArrayList<String> tags, ArrayList<String> productsList) {
        ArrayList<String> products = new ArrayList<>();
        for (String product: productsList) {
            if (getProductWithBarcode(product) != null) {
                products.add(product);
            }
        }
        ArrayList<String> subCategory = new ArrayList<>();
        for (String category: subCategories) {
            if (Category.getCategoryByName(category) != null) {
                subCategory.add(category);
            }
        }
        Category category = new Category(name, tags, products, subCategory);
        SaveAndLoad.getSaveAndLoad().saveGenerally();
        return category;
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
            CodedOff.getAllDiscounts().addAll((Collection<? extends CodedOff>) SaveAndLoad.getSaveAndLoad().readJSONByType("allOffCodes", offCodesListType));
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
        if((product = Product.getProductWithBarcode(productId)) == null)
            return 0;
        Account account = getCurrentAccount();
        if(!account.getClass().equals(Customer.class)){
            return 2;
        }
        Customer customer = (Customer) account;
        return customer.addProductToCart(productId);

    }

    public int calculateCartCost() {
        return ((Customer) currentAccount).getCartMoney();
    }

    public ArrayList<String> requestProductSeller(String productId) {
        ArrayList<String> sellersOfProduct = new ArrayList<>();
        String[] pathnames;

        // Creates a new File instance by converting the given pathname string
        // into an abstract pathname
        File f = new File(Seller.class.toString());

        // Populates the array with names of files and directories
        pathnames = f.list();

        // For each pathname in the pathnames array
        for (String pathname : pathnames) {
            // Print the names of files and directories
            Seller seller = (Seller) SaveAndLoad.getSaveAndLoad().readJSON(pathname, Seller.class);
            if (seller.getProducts().contains(Product.getProductWithBarcode(productId))) {
                sellersOfProduct.add(pathname);
            }
        }
        return sellersOfProduct;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void editField(String field) {
        System.out.println("Enter your new amount for the field you choose");
        Matcher newAmount = getField("Please enter a valid string", "(\\S+)");
        switch (field.toLowerCase()) {
            case "firstname":
                currentAccount.setFirstName(newAmount.group(1));
                return;
            case "lastname":
                currentAccount.setLastName(newAmount.group(1));
                return;
            case "credit" :
                currentAccount.setCredit(Double.parseDouble(newAmount.group(1)));
                return;
            case "phonenumber" :
                currentAccount.setPhoneNumber(newAmount.group(1));
                return;
            case "email" :
                currentAccount.setEmail(newAmount.group(1));
                return;
            case "password" :
                currentAccount.setPassWord(newAmount.group(1));
        }
        SaveAndLoad.getSaveAndLoad().writeJSON(this.getCurrentAccount(), this.getCurrentAccount().getClass().toString(), this.getCurrentAccount().getUserName());
    }

    public int requestAddProduct(String name, String company, double cost, String category, String description) {
        return 0;
    }

    public boolean removeCategory(String name) {
        Category.deleteCategoryAndProducts(name);
        return true;
    }

    public ArrayList requestCompanyInfo() {
        return null;
    }

    public ArrayList<History> requestSalesHistoryInfoInSeller() {
        if (currentAccount.getClass().equals(Seller.class)) {
            return ((Seller) currentAccount).getHistory();
        } else {
            return ((Customer) currentAccount).getHistory();
        }
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

    public static void createProductRequest(String name, String company, int cost, String categoryName, String description, int amountOfExist, ArrayList<String> tags, String firstSellerName) {
        Category category = Category.getCategoryByName(categoryName);
        ArrayList<Seller> sellers = new ArrayList<>();
        Request requestProduct = new RequestProduct(RequestType.PRODUCT, new Product(name, company, cost, categoryName, description, amountOfExist, tags, firstSellerName));
    }



    public ArrayList<Off> getAllOffsOfSeller() {
        ArrayList<Off> sellersOff = new ArrayList<>();
        for (Off off: Off.getAllOffs()) {
            if (((Seller) currentAccount).getOffs().contains(off.getOffBarcode())) {
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

    public void createOrEditOffRequest(ArrayList<String> products, String startDate, String endDate, int offAmount, String name) {
        ArrayList<Product> productsToAddTO = new ArrayList<>();
        for (String productBarcode: products) {
            if (Product.getAllProducts().contains(getProductWithBarcode(productBarcode)) && !getProductWithBarcode(productBarcode).isInOffOrNot()) {
                productsToAddTO.add(getProductWithBarcode(productBarcode));
                getProductWithBarcode(productBarcode).setInOffOrNot(true);
            }
        }
//        LocalDateTime start = LocalDateTime.of(Integer.parseInt(startDate.group(1)), Integer.parseInt(startDate.group(2)), Integer.parseInt(startDate.group(3)), Integer.parseInt(startDate.group(4)), Integer.parseInt(startDate.group(5)));
//        LocalDateTime end = LocalDateTime.of(Integer.parseInt(endDate.group(1)), Integer.parseInt(endDate.group(2)), Integer.parseInt(endDate.group(3)), Integer.parseInt(endDate.group(4)), Integer.parseInt(endDate.group(5)));
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        Off off = new Off(startDate, products, endDate, offAmount, name);
        Off.getAllOffs().remove(off);
        SaveAndLoad.getSaveAndLoad().writeJSON(Off.getAllOffs(), ArrayList.class.toString(), "allOffs");
        new RequestOff(RequestType.OFF, off);
    }

    public void removeOff(String name) {
        if (Off.getAllOffs().contains(Off.getOffByBarcode(name))) {
            Off.getAllOffs().remove(Off.getOffByBarcode(name));
            for (String product: Off.getOffByBarcode(name).getProducts()) {
                Product.getProductWithBarcode(product).setInOffOrNot(false);
                Product.getProductWithBarcode(product).offTheCost((-Product.getProductWithBarcode(product).getCost() * 100 /(100 - Off.getOffByBarcode(name).getOffAmount())) * Off.getOffByBarcode(name).getOffAmount());
            }
        }
    }

    public void setCustomersField(String firstName, String lastName, String phoneNumber, String email) {
        Customer loggedInCustomer = (Customer) currentAccount;
        loggedInCustomer.setFirstName(firstName);
        loggedInCustomer.setLastName(lastName);
        loggedInCustomer.setPhoneNumber(phoneNumber);
        loggedInCustomer.setEmail(email);
    }

    public void changeFields(String firstName, String lastName, String phoneNumber, String email, String passWord) {
        Account loggedInManager = currentAccount;
        loggedInManager.setFirstName(firstName);
        loggedInManager.setLastName(lastName);
        loggedInManager.setPhoneNumber(phoneNumber);
        loggedInManager.setEmail(email);
        loggedInManager.setPassWord(passWord);
    }

    public void setCustomerAddress(String address) {
        ((Customer) currentAccount).setAddress(address);
    }

    public void addOffCodeToCustomer() {

    }

    public double getCredit() {
        return (currentAccount.getCredit());
    }

    public ArrayList<String> getCustomerDiscountCodes() {
        return ((Customer) currentAccount).getOffCodes();
    }

    public String getCompanyNameInSeller() {
        return ((Seller) currentAccount).getCompanyName();
    }

//    public ArrayList<String> viewByers(String productId) {
//        Product product = getProductWithBarcode(productId);
//        return product.getByers();
//    }

    public void editProductRequest(String barcode, String companyName, int cost, String categoryName, String description, int amountOfExist, ArrayList<String> tags, String firstSellerName) {
        Category category = Category.getCategoryByName(categoryName);
        Request requestProduct = new RequestProduct(RequestType.PRODUCT, new Product("productBarcode: " + barcode,companyName, cost, categoryName, description, amountOfExist, tags, firstSellerName));
    }

    public void removeProductFromSellerProducts(String productId) {
        ArrayList<String> products = new ArrayList<>(((Seller) currentAccount).getProducts());
        for (String product: products) {
            if (product.equalsIgnoreCase(productId)) {
                ((Seller) currentAccount).getProducts().remove(product);
            }
        }
        SaveAndLoad.getSaveAndLoad().saveGenerally();
    }

    public ArrayList<Category> showCategories() {
        return Category.getAllCategories();
    }

    public ArrayList<String> requestOffsList() {
        ArrayList<Off> offs = Off.getAllOffs();
        ArrayList<String> offProducts = new ArrayList<>();
        for (Off off: offs) {
            offProducts.addAll(off.getProducts());
        }
        return offProducts;
    }

    public void setNameOfSellerOfProductAddedToCart(String sellerName) {
        Customer customer = (Customer) getCurrentAccount();
        customer.setSellerName(sellerName);
    }

    public void setCustomerPassWordAndAddress(String passWord, String address) {
        currentAccount.setPassWord(passWord);
        ((Customer) currentAccount).setAddress(address);
    }


    public void changeCompanyName(String trim) {
        ((Seller) this.getCurrentAccount()).setCompanyName(trim);
    }

    public HashMap getCart(){
        return ((Customer) currentAccount).getCart();
    }

    public Product getProductWithBarcodeController(String string){
        return Product.getProductWithName(string);
    }

}

