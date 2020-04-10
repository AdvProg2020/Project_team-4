package Model;

import java.util.ArrayList;

public class Customer extends Account {
    private static ArrayList<Customer> allCustomers = new ArrayList<Customer>();
    private ArrayList<Product> cart;
    private ArrayList<BuyLog> buyingHistory;

    public Customer(String userName, String passWord) {
        super(userName, passWord);
        allCustomers.add(this);
    }


    public ArrayList<Product> getProducts() {
        return null;
    }

    public ArrayList<BuyLog> getBoughtProducts() {
        return buyingHistory;
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
