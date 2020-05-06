package Model;



import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Product extends SaveAble {
    private static ArrayList<Product> allProducts = new ArrayList<>();
    private String productBarcode;
    private static ArrayList<Customer> byers = new ArrayList<>();
    //private static HashMap<String, Product> products;
    private enum  productStatus {
        MAKING, EDITING, APPROVED
    }
    private String name;
    private ArrayList<String> categoryTags;
    private final String company;
    private final double cost;
    private final ArrayList<Seller> sellers;
    private final boolean existsOrNot;
    private final Category category;
    private final String description;
    private final ArrayList<Comment> comments;
    private final int averageScore;
    private final int amountOfExist;
    private final ArrayList<String> tags;
    private int productId;
    private static int giveId;

    public Product(String name, String company, double cost, boolean existsOrNot, Category category, String description, int averageScore, int amountOfExist, ArrayList<String> tags) {
        this.productBarcode = givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect();
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
        this.tags = tags;
        this.amountOfExist = amountOfExist;
        this.productId = giveId;
        giveId++;
        allProducts.add(this);
    }

    public Product(Product product) {
        this.productBarcode = product.getProductBarcode();
        this.name = product.getName();
        this.categoryTags = product.getCategoryTags();
        this.company = product.getCompany();
        this.cost = product.getCost();
        this.sellers = product.getSellers();
        this.existsOrNot = product.isExistsOrNot();
        this.category = product.getCategory();
        this.description = product.getDescription();
        this.comments = product.getComments();
        this.averageScore = product.getAverageScore();
        this.tags = product.getTags();
        this.amountOfExist = product.getAmountOfExist();
        allProducts.add(this);
        SaveAndLoad.getSaveAndLoad().writeJSON(allProducts, ArrayList.class, "allProducts");
    }

    public String getProductBarcode() {
        return productBarcode;
    }

    public static ArrayList<Customer> getByers() {
        return byers;
    }

    public String getCompany() {
        return company;
    }

    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public boolean isExistsOrNot() {
        return existsOrNot;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public int getAverageScore() {
        return averageScore;
    }

    public int getAmountOfExist() {
        return amountOfExist;
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

    public double getCost() {
        return cost;
    }

    public static boolean removeProduct(Product product) {  //need to debug after implementation add product in sellerMenu.
        if (allProducts.contains(product)) {
            allProducts.remove(product);
            //SaveAndLoad.getSaveAndLoad().writeJSON(allProducts, Product.class, pro);
            return true;
        }
        return false;
    }

    public static ArrayList<Product> showAllProducts() {
        return allProducts;
    }

    public String givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

    public void setCategoryTags(ArrayList<String> categoryTags) {
        this.categoryTags.addAll(categoryTags);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productBarcode.equalsIgnoreCase(product.productBarcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productBarcode)+8;
    }
}
