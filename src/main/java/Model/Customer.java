package Model;

import Control.Controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Customer extends Account {
    //private static ArrayList<Customer> allCustomers = new ArrayList<Customer>();
    private HashMap<String, Integer> cart;
    private ArrayList<String> sellersOfProductsOfTheCart;
    //private ArrayList<BuyLog> buyingHistory;
    //private ArrayList<CodedOff> offCodes;
//    protected ArrayList<History> history;
    protected ArrayList<String> offCodes;
    protected ArrayList<Integer> usageOfOffCodes = new ArrayList<>();
    private String address = "";

    public Customer(String userName, String passWord) {
        super(userName, passWord);
        this.offCodes = new ArrayList<>();
//        this.history = new ArrayList<>();
        this.cart = new HashMap<>();
        this.sellersOfProductsOfTheCart = new ArrayList<>();
//        allCustomers.add(this);
        System.out.println(this.getClass());
        SaveAndLoad.getSaveAndLoad().writeJSON(this, this.getClass().toString(), userName);
        for (String offCode: offCodes) {
            usageOfOffCodes.add(0);
        }
    }

    public static void newCustomer(String username, String password) {
        new Customer(username, password);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HashMap<String, Integer> getCart() {
        return cart;
    }

    public ArrayList<String> getProducts() {
        return null;
    }

//    public ArrayList<BuyLog> getBoughtProducts() {
//        return buyingHistory;
//    }


    public ArrayList<String> getOffCodes() {
        return offCodes;
    }

    public void filterOrSearchInProducts() {
        return;
    }

    public String productsComparing() {
        return null;
    }

    public void buy(String product) {

    }

    public int addProductToCart(String product) {
        if(Product.getProductWithBarcode(product).isExistsOrNot()){
            Product.getProductWithBarcode(product).setAmountOfExist(Product.getProductWithBarcode(product).getAmountOfExist() - 1);
            cart.put(product, cart.get(product) + 1); // maybe need edition
            SaveAndLoad.getSaveAndLoad().saveGenerally();
            return 1;
        }
        else{
            return 0;
        }
    }

    public boolean pay(String offCode) {
        //age chizi be Off ezafe shod bayad costesh hamoon ja kam beshe haaaaaa in ja off mohasebe nemishe va faghat codedOff ha tasir daran
        //too saef mahsool bayad darj beshe ke kodoom seller dare ino mofrooshe va too customer ye arrayList hast ke be tartbie product haye hashMap product haye cart seller haye har product rpo ham zakhire mikone
        int finalCost = getCartMoney();
        if (CodedOff.getOffCodeWithName(offCode) != null && this.usageOfOffCodes.get(getOffCodeIndexForUsageTimeAddingByName(offCode))  < CodedOff.getOffCodeWithName(offCode).getUsageTime()) {
            if ((getCartMoney() * CodedOff.getOffCodeWithName(offCode).getPercent() / 100) > CodedOff.getOffCodeWithName(offCode).getOffAmount()) {
                finalCost -= CodedOff.getOffCodeWithName(offCode).getOffAmount();
            } else {
                finalCost -= (getCartMoney() * CodedOff.getOffCodeWithName(offCode).getPercent() / 100);
            }
            this.usageOfOffCodes.set(getOffCodeIndexForUsageTimeAddingByName(offCode), this.usageOfOffCodes.get(getOffCodeIndexForUsageTimeAddingByName(offCode)) + 1);
        }
        if (this.getCredit() < finalCost) {
            return false;
        } else {
            addHistory(finalCost);
            this.credit -= finalCost;
            ArrayList<String> products = (ArrayList<String>) cart.keySet();
            int i = 0;
            for (String seller: sellersOfProductsOfTheCart) {
                Seller sellerFromFile = (Seller) Seller.getAccountWithName(seller);
                sellerFromFile.setCredit(Product.getProductWithBarcode(products.get(i)).getCost() * cart.get(products.get(i)));
                SaveAndLoad.getSaveAndLoad().writeJSON(sellerFromFile, sellerFromFile.getClass().toString(), sellerFromFile.getUserName());
                i++;
            }
            cart = new HashMap<>();
            SaveAndLoad.getSaveAndLoad().saveGenerally();
            return true;
        }
    }

    public int getCartMoney() {
        int cartCost = 0;
        if (this.cart.size() != 0) {
            for (String product : cart.keySet()) {
                cartCost += Product.getProductWithBarcode(product).getCost() * cart.get(product);
            }
        }
        return cartCost;
    }

    public int getOffCodeIndexForUsageTimeAddingByName(String name) {
        for (int i=0; i<offCodes.size(); i++) {
            if (Off.getOffByBarcode(offCodes.get(i)).getOffBarcode().equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Customer{" +
//                ", history=" + history +
                ", offCodes=" + offCodes +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passWord='" + passWord + '\'' +
                ", credit=" + credit +
                '}';
    }


    public void setNumberOfProductInCart(String productInCart, int n) {
        if (cart.keySet().contains(productInCart)) {
            cart.replace(productInCart, cart.get(productInCart) +n);
            if (cart.get(productInCart) == 0) {
                cart.remove(productInCart);
            }
        } else {
            System.out.println("This barcode is none of your selected products in cart");
        }

    }

//    public History getHistoryById(String name) {
//        for (History history: history) {
//            if (history.getLogBarcode().equalsIgnoreCase(name)) {
//                return history;
//            }
//        }
//        return null;
//    }

    public void addHistory(int finalCost) {
        LocalDateTime dateTime = LocalDateTime.now();
        int offCost = getCartMoney() - finalCost;
        ArrayList<String> products = new ArrayList<>(cart.keySet());
        History historyOfPurchase = new History(dateTime, getCartMoney(), offCost, sellersOfProductsOfTheCart, products);
//        history.add(historyOfPurchase);
    }

    public void setSellerName(String name) {
        sellersOfProductsOfTheCart.add(name);
    }

    public void addOffCode(String offCode) {
        this.offCodes.add(offCode);
    }
}
