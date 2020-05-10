package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Customer extends Account {
    private static ArrayList<Customer> allCustomers = new ArrayList<Customer>();
    private HashMap<Product, Integer> cart;
    private ArrayList<Seller> sellersOfProductsOfTheCart;
    //private ArrayList<BuyLog> buyingHistory;
    //private ArrayList<CodedOff> offCodes;
    protected ArrayList<History> history;
    protected ArrayList<CodedOff> offCodes;
    private String address = "";

    public Customer() {
    }

    public Customer(String userName, String passWord) {
        super(userName, passWord);
        this.offCodes = new ArrayList<>();
        this.history = new ArrayList<>();
        this.cart = new HashMap<>();
        this.sellersOfProductsOfTheCart = new ArrayList<>();
        allCustomers.add(this);
        SaveAndLoad.getSaveAndLoad().writeJSON(this, Customer.class, userName);
    }

    public static void newCustomer(String username, String password) {
        new Customer(username, password);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HashMap<Product, Integer> getCart() {
        return cart;
    }

    public ArrayList<Product> getProducts() {
        return null;
    }

//    public ArrayList<BuyLog> getBoughtProducts() {
//        return buyingHistory;
//    }


    public ArrayList<CodedOff> getOffCodes() {
        return offCodes;
    }

    public void filterOrSearchInProducts() {
        return;
    }

    public String productsComparing() {
        return null;
    }

    public void buy(Product product) {

    }

    public int addProductToCart(Product product) {
        if(product.isExistsOrNot()){
            product.setAmountOfExist(product.getAmountOfExist() - 1);
            cart.put(product, product.getCost()); // maybe need edition
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
        if (CodedOff.getOffCodeWithName(offCode) != null) {
            if ((getCartMoney() * CodedOff.getOffCodeWithName(offCode).getPercent() / 100) > CodedOff.getOffCodeWithName(offCode).getOffAmount()) {
                finalCost -= CodedOff.getOffCodeWithName(offCode).getOffAmount();
            } else {
                finalCost -= (getCartMoney() * CodedOff.getOffCodeWithName(offCode).getPercent() / 100);
            }
        }
        if (this.getCredit() < finalCost) {
            return false;
        } else {
            this.credit -= finalCost;
            ArrayList<Product> products = (ArrayList<Product>) cart.keySet();
            int i = 0;
            for (Seller seller: sellersOfProductsOfTheCart) {
                seller.setCredit(products.get(i).getCost() * cart.get(products.get(i)));
                i++;
            }
            return true;
        }
    }

    public int getCartMoney() {
        int cartCost = 0;
        if (this.cart.size() != 0) {
            for (Product product : cart.keySet()) {
                cartCost += product.getCost() * cart.get(product);
            }
        }
        return cartCost;
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

    public static Customer getCustomerByName(String name) {
        for (Customer customer : allCustomers) {
            if(name.equals(customer.getName())){
                return customer;
            }
        }
        return null;
    }

    public void setNumberOfProductInCart(Product productInCart, int n) {
        if (cart.keySet().contains(productInCart)) {
            cart.replace(productInCart, cart.get(productInCart) +n);
            if (cart.get(productInCart) == 0) {
                cart.remove(productInCart);
            }
        } else {
            System.out.println("This barcode is none of your selected products in cart");
        }

    }

}
