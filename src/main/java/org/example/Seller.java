package org.example;

import Model.Account;
import Model.History;
import Model.SaveAndLoad;

import java.util.ArrayList;

public class Seller extends Account {

    //private static ArrayList<Seller> allSellers = new ArrayList<Seller>();
    protected ArrayList<Model.History> history;
    private ArrayList<String> products;
    protected String companyName;

    private ArrayList<Model.History> sellHistory;
//    private ArrayList<String> sellingProducts;
    private ArrayList<String> offs;

    public Seller(String userName, String passWord) {
        super(userName, passWord);
        this.sellHistory = new ArrayList<Model.History>();
//        this.sellingProducts = new ArrayList<>();
        this.offs = new ArrayList<>();
        this.history = new ArrayList<>();
        this.products = new ArrayList<>();
        SaveAndLoad.getSaveAndLoad().writeJSON(this, Seller.class.toString(), userName);
        //allSellers.add(this);
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setSoldProducts(Model.History soldProduct) {
        this.sellHistory.add(soldProduct);
    }

    public void setProducts(String product) {
        this.products.add(product);
    }

    //    public void setSellingProducts(ArrayList<String> sellingProducts) {
//        this.sellingProducts = sellingProducts;
//    }

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

    public ArrayList<String> getOffs() {
        return offs;
    }

    public ArrayList<History> getHistory() {
        return history;
    }

    public ArrayList<String> getProducts() {
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
//                ", sellingProducts=" + sellingProducts +
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
