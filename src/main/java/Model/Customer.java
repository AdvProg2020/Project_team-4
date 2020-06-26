package Model;

import Control.Controller;
import org.example.Cart;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Customer extends Account {
    //private static ArrayList<Customer> allCustomers = new ArrayList<Customer>();
    private HashMap<String, Integer> cart;
    private ArrayList<String> sellersOfProductsOfTheCart;
    //private ArrayList<BuyLog> buyingHistory;
    //private ArrayList<CodedOff> offCodes;
    protected ArrayList<History> history;
    protected ArrayList<String> offCodes;
    protected ArrayList<Integer> usageOfOffCodes = new ArrayList<>();
    private String address = "";

    public Customer(String userName, String passWord) {
        super(userName, passWord);
        this.offCodes = new ArrayList<>();
        this.history = new ArrayList<>();
        this.cart = new HashMap<>();
        this.sellersOfProductsOfTheCart = new ArrayList<>();
//        allCustomers.add(this);
        System.out.println(this.getClass());
        SaveAndLoad.getSaveAndLoad().writeJSON(this, this.getClass().toString(), userName);
        for (String offCode: offCodes) {
            usageOfOffCodes.add(0);
        }
    }

    public void setUsageOfOffCodes(int usageOfOffCodes) {
        this.usageOfOffCodes.add(usageOfOffCodes);
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

    public ArrayList<History> getHistory() {
        return history;
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
//        System.out.println(cart);
        if(Product.getProductWithBarcode(product).isExistsOrNot()){
//            Product.getProductWithBarcode("amount of exist of product:" + Product.getProductWithBarcode(product) + " " + Product.getProductWithBarcode(product).getAmountOfExist());
            Controller.getOurController().increaseOrDecreaseProductNo(product, +1);
//            Product.getProductWithBarcode(product).setAmountOfExist(Product.getProductWithBarcode(product).getAmountOfExist() - 1);
//            Product.getProductWithBarcode("amount of exist of product:" + Product.getProductWithBarcode(product) + " " + Product.getProductWithBarcode(product).getAmountOfExist());

            if(cart.get(product) != null) {
                cart.replace(product, cart.get(product) + 1);
            } else{
                cart.put(product, 1);
            }
            SaveAndLoad.getSaveAndLoad().saveGenerally();
            System.out.println(cart);
            return 1;
        }
        else{
            System.out.println("raft too");
            return 0;
        }
    }

    public boolean pay(String offCode) {
        //age chizi be Off ezafe shod bayad costesh hamoon ja kam beshe haaaaaa in ja off mohasebe nemishe va faghat codedOff ha tasir daran
        //too saef mahsool bayad darj beshe ke kodoom seller dare ino mofrooshe va too customer ye arrayList hast ke be tartbie product haye hashMap product haye cart seller haye har product rpo ham zakhire mikone
        int finalCost = getCartMoney();
        if (CodedOff.getOffCodeWithName(offCode) != null && this.usageOfOffCodes.get(getOffCodeIndexForUsageTimeAddingByName(offCode)) != 0) {
            if (getCartMoney() * (Integer.parseInt(CodedOff.getOffCodeWithName(offCode).getPercent()) / 100) > Double.parseDouble(CodedOff.getOffCodeWithName(offCode).getOffAmount())) {
                finalCost -= Double.parseDouble(CodedOff.getOffCodeWithName(offCode).getOffAmount());
            } else {
                finalCost -= (getCartMoney() * Integer.parseInt(CodedOff.getOffCodeWithName(offCode).getPercent()) / 100);
            }
            this.usageOfOffCodes.set(getOffCodeIndexForUsageTimeAddingByName(offCode), this.usageOfOffCodes.get(getOffCodeIndexForUsageTimeAddingByName(offCode)) - 1);
        }
        if (this.getCredit() < finalCost) {
            return false;
        } else {
            addHistory(finalCost);
            this.credit -= finalCost;
            Set<String> products = cart.keySet();
            Iterator iterator = products.iterator();
            int i = 0;
            for (String seller: sellersOfProductsOfTheCart) {
                String name = iterator.next().toString();
                Seller sellerFromFile = (Seller) Seller.getAccountWithName(seller);
                System.out.println(sellerFromFile.getCredit());
                sellerFromFile.setCredit(Product.getProductWithBarcode(name).getCost() * cart.get(name));
                System.out.println(sellerFromFile.getCredit());
                SaveAndLoad.getSaveAndLoad().writeJSON(sellerFromFile, sellerFromFile.getClass().toString(), sellerFromFile.getUserName());
            }
            cart = new HashMap<>();
            System.out.println("before saving");
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
            if (CodedOff.getOffCodeWithName(offCodes.get(i)).getOffBarcode().equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", history=" + history +
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

    public History getHistoryById(String name) {
        for (History history: history) {
            if (history.getLogBarcode().equalsIgnoreCase(name)) {
                return history;
            }
        }
        return null;
    }

    public void addHistory(int finalCost) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String time = dateTime.format(dateTimeFormatter);
        int offCost = getCartMoney() - finalCost;
        ArrayList<String> products = new ArrayList<>(cart.keySet());
        History historyOfPurchase = new History(time, getCartMoney(), offCost, sellersOfProductsOfTheCart, products);
        history.add(historyOfPurchase);
    }

    public void setSellerName(String name) {
        sellersOfProductsOfTheCart.add(name);
    }

    public void addOffCode(String offCode) {
        this.offCodes.add(offCode);
    }
}
