package Model;

import java.util.ArrayList;

public class Seller extends Account {
    protected String companyName;

    private ArrayList<Product> soldProducts;
    private ArrayList<Product> sellingProducts;
    private ArrayList<Off> offs;

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setSoldProducts(ArrayList<Product> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public void setSellingProducts(ArrayList<Product> sellingProducts) {
        this.sellingProducts = sellingProducts;
    }

    public void sendEditProductRequest() {
        Manager.receiveEditProductRequest();
    }

    public void sendRemoveProductRequest() {
        Manager.receiveRemoveProductREquest();
    }

    public void sendAddProductRequest() {
        Manager.receiveAddProductREquest();
    }

    public void sendAddOffRequest() {
        Manager.receiveAddOffRequest();
    }

    public void sendEditOffRequest() {
        Manager.receiveEditOffRequest();
    }




}
