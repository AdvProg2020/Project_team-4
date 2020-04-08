package Model;

import java.util.ArrayList;

public class Customer extends Account{
    private ArrayList<Product> products;
    private ArrayList<Product> boughtProducts;


    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Product> getBoughtProducts() {
        return boughtProducts;
    }



    public Product getProductByFilter() {
        return null;
    }


    public Product productsComparing() {
        return null;
    }

    public void buy(Product product) {

    }
}
