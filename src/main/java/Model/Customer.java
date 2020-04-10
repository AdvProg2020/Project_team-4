package Model;

import java.util.ArrayList;

public class Customer extends Account {
    private ArrayList<Product> cart;
    private ArrayList<BuyLog> buyingHistory;


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
