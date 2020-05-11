package Model;

import java.util.ArrayList;

public class Seller extends Account {

    //private static ArrayList<Seller> allSellers = new ArrayList<Seller>();
    protected ArrayList<History> history;
    private ArrayList<Product> products;
    protected String companyName;

    private ArrayList<History> sellHistory;
    private ArrayList<Product> sellingProducts;
    private ArrayList<Off> offs;

    public Seller(String userName, String passWord) {
        super(userName, passWord);
        this.sellHistory = new ArrayList<History>();
        this.sellingProducts = new ArrayList<Product>();
        this.offs = new ArrayList<Off>();
        this.history = new ArrayList<>();
        this.products = new ArrayList<>();
        SaveAndLoad.getSaveAndLoad().writeJSON(this, Seller.class, userName);
        //allSellers.add(this);
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setSoldProducts(History soldProduct) {
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

    public ArrayList<Off> getOffs() {
        return offs;
    }

    public ArrayList<History> getHistory() {
        return history;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "history=" + history +
                ", products=" + products +
                ", companyName='" + companyName + '\'' +
                ", sellHistory=" + sellHistory +
                ", sellingProducts=" + sellingProducts +
                ", offs=" + offs +
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
