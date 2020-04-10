package Model;

import java.util.ArrayList;

public class Seller extends Account {
    protected String companyName;

    private ArrayList<BuyLog> sellHistory;
    private ArrayList<Product> sellingProducts;
    private ArrayList<Off> offs;

    public Seller(String userName, String firstName, String lastName, String email, String phoneNumber, String passWord, double credit) {
        super(userName, firstName, lastName, email, phoneNumber, passWord, credit);
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setSoldProducts(BuyLog soldProduct) {
        this.sellHistory.add(soldProduct);
    }

    public void setSellingProducts(ArrayList<Product> sellingProducts) {
        this.sellingProducts = sellingProducts;
    }

    public void sendEditProductRequest() {
        /*Manager.receive();*/
    }

    public void sendRemoveProductRequest() {
        //Manager.receiveRemoveProductREquest();
    }

    public void sendAddProductRequest() {
        // Manager.receiveAddProductREquest();
    }

    public void sendAddOffRequest() {
        //Manager.receiveAddOffRequest();
    }

    public void sendEditOffRequest() {
        //Manager.receiveEditOffRequest();
    }





}
