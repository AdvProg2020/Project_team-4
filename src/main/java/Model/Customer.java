package Model;

import java.util.ArrayList;

public class Customer extends Account {
    //private static ArrayList<Customer> allCustomers = new ArrayList<Customer>();
    private ArrayList<Product> cart;
    //private ArrayList<BuyLog> buyingHistory;
    //private ArrayList<CodedOff> offCodes;
    protected ArrayList<History> history;
    protected ArrayList<CodedOff> offCodes;

    public Customer(String userName, String passWord) {
        super(userName, passWord);
        this.offCodes = new ArrayList<>();
        //allCustomers.add(this);
        this.history = new ArrayList<>();
    }

    public static void newCustomer(String username, String password) {
        Customer customer = new Customer(username, password);
        SaveAndLoad.getSaveAndLoad().writeJSONAccount(customer);
    }

    public ArrayList<Product> getCart() {
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

    public static void addProductToCart(Product product) {
        //check if exiits or not and kam kon azash
    }

    public boolean pay() {
        //check if money is enough
        if (this.getCredit() < getCartMoney()) {
            return false;
        } else {
            this.credit -= getCartMoney();
            return true;
        }
    }

    public int getCartMoney() {
        int cartCost = 0;
        if (this.cart.size() != 0) {
            for (Product product : cart) {
                cartCost += product.getCost();
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
}
