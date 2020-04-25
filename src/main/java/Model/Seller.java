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
        //allSellers.add(this);
    }

    public static boolean addANewSeller(String requestId, String userName, String passWord) {
        Manager.addANewSellerRequest(requestId, userName, passWord);
        return true;
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

}
