package Model;

import java.util.ArrayList;

public class Customer extends Account {
    private static ArrayList<Customer> allCustomers = new ArrayList<Customer>();
    private ArrayList<Product> cart;
    private ArrayList<BuyLog> buyingHistory;
    private ArrayList<CodedOff> offCodes;

    public Customer(String userName, String passWord) {
        super(userName, passWord);
        this.offCodes = new ArrayList<>();
        allCustomers.add(this);
    }


    public ArrayList<Product> getProducts() {
        return null;
    }

    public ArrayList<BuyLog> getBoughtProducts() {
        return buyingHistory;
    }


    @Override
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


}
