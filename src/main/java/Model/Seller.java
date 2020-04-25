package Model;

import java.util.ArrayList;

public class Seller extends Account {

    //private static ArrayList<Seller> allSellers = new ArrayList<Seller>();
    protected ArrayList<History> history;
    private ArrayList<Product> products;

    public Seller(String userName, String passWord) {
        super(userName, passWord);
        this.sellHistory = new ArrayList<BuyLog>();
        this.sellingProducts = new ArrayList<Product>();
        this.offs = new ArrayList<Off>();
        this.history = new ArrayList<>();
        this.products = new ArrayList<>();
        //allSellers.add(this);
    }

    protected String companyName;

    private ArrayList<BuyLog> sellHistory;
    private ArrayList<Product> sellingProducts;
    private ArrayList<Off> offs;

    public static boolean addANewSeller(String requestId, String userName, String passWord) {
        Manager.addANewSellerRequest(requestId, userName, passWord);
        return true;
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

    public static Seller getSellerByName(String name) {
        for (Seller seller : allSellers) {
            if (seller.getName().equalsIgnoreCase(name)) {
                return seller;
            }
        }
        return null;
    }








}
