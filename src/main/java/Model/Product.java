package Model;



import java.util.ArrayList;
import java.util.HashMap;

public class Product {
    private String productBarcode;
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
