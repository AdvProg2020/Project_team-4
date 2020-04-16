package Model;



import java.util.ArrayList;
import java.util.HashMap;

public class Product {
    private static ArrayList<Product> allProducts;
    private String productBarcode;
    private static ArrayList<Customer> byers = new ArrayList<>();
    private static HashMap<String, Product> products;
    private enum  productStatus {
        inProcessOfMaking, inProcessOfEditing, approved
    }
    private String name;
    private ArrayList<String> categoryTags;
    private String company;
    private double cost;
    private ArrayList<Seller> sellers;
    private boolean existsOrNot;
    private Category category;
    private String description;
    private ArrayList<Comment> comments;
    private int averageScore;

    public Product(String productBarcode, String name, String company, double cost, boolean existsOrNot, Category category, String description, int averageScore) {
        this.productBarcode = productBarcode;
        this.name = name;
        this.categoryTags = new ArrayList<String>();
        this.company = company;
        this.cost = cost;
        this.sellers = new ArrayList<Seller>();
        this.existsOrNot = existsOrNot;
        this.category = category;
        this.description = description;
        this.comments = new ArrayList<Comment>();
        this.averageScore = averageScore;
        allProducts.add(this);
    }

    public static ArrayList<Product> getProductsInProducts() {
        return null;
    }

    public ArrayList<String> getCategoryTags() {
        return categoryTags;
    }

    public void addAProductToCart(Product product) {

    }

    public void buyTheCartItems() {

    }
}
