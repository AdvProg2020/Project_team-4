package Model;



import java.util.ArrayList;
import java.util.HashMap;

public class Product extends SaveAble{
    private static ArrayList<Product> allProducts;
    private String productBarcode;
    private static ArrayList<Customer> byers = new ArrayList<>();
    //private static HashMap<String, Product> products;
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
    private int amountOfExist;
    private ArrayList<String> tags;

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
        this.tags = new ArrayList<>();
        allProducts.add(this);
    }



    public static ArrayList<Product> getAllProducts() {
        return allProducts;
    }



    public ArrayList<String> getCategoryTags() {
        return categoryTags;
    }

    public void addAProductToCart(Product product) {

    }

    public void buyTheCartItems() {

    }

    public void addTag(ArrayList<String> tags) {
        this.tags.addAll(tags);
    }

    public static Product getProductWithName(String name) {
        for (Product product : allProducts) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public static ArrayList<Product> getProductsWithTags(String name, ArrayList<String> tags) {
        ArrayList<Product> products = new ArrayList();
        for (Product product : allProducts) {
            if (product.getName().equalsIgnoreCase(name)) {
                products.add(product);
            }
        }
        if (tags == null) {
            return products;
        }
        else {
            for (Product product : products) {
                for (String tag : tags) {
                    if (!product.getTags().contains(tag)) {
                        products.remove(product);
                        break;
                    }
                }
            }
            return products;
        }
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    @Override
    protected String getName() {
        return productBarcode;
    }
}
