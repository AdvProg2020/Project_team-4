package Model;



import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class Product extends SaveAble {
    private static ArrayList<Product> allProducts = new ArrayList<>();
    private String productBarcode;
    private  ArrayList<Customer> byers = new ArrayList<>();
    //private static HashMap<String, Product> products;
    private enum  productStatus {
        MAKING, EDITING, APPROVED
    }
    private int seen;
    private LocalDateTime localDateTime;
    private String name;
    private ArrayList<String> categoryTags;
    private String company;
    private int cost;
    private ArrayList<Seller> sellers;
    private boolean existsOrNot;
    private Category category;
    private String description;
    private ArrayList<Comment> comments;
    private int scoreNo;
    private int averageScore;
    private int amountOfExist;
    private boolean isInOffOrNot;
    private ArrayList<String> tags;
    private static int giveId;

    public int getScoreNo() {
        return scoreNo;
    }

    public int getSeen() {
        return seen;
    }

    public Product(String name, String company, int cost, Category category, String description, int amountOfExist, ArrayList<String> tags, ArrayList<Seller> sellers) {
        this.productBarcode = givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect();
        this.name = name;
        this.categoryTags = new ArrayList<>();
        this.company = company;
        this.cost = cost;
        this.sellers = new ArrayList<>();
        this.category = category;
        this.description = description;
        this.comments = new ArrayList<>();
        this.tags = tags;
        this.sellers = sellers;
        this.amountOfExist = amountOfExist;
        giveId++;
        this.seen = 0;
        localDateTime = LocalDateTime.now();
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

    public void plusSeenNumber(){
        seen++;
    }

    public String getProductBarcode() {
        return productBarcode;
    }

    public ArrayList<Customer> getByers() {
        return byers;
    }

    public String getCompany() {
        return company;
    }

    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public boolean isExistsOrNot() {
        if (amountOfExist > 0) {
            return true;
        }
        return false;
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

    public void buyTheCartItems() {

    }

    public void addTag(ArrayList<String> tags) {
        this.tags.addAll(tags);
    }

    public static Product getProductWithBarcode(String name) {
        for (Product product : allProducts) {
            if (product.getProductBarcode().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }



    public static ArrayList<Product> getProductsWithTagsAndName(String name, ArrayList<String> tags) {
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public static ArrayList<Product> getProductWithTag(ArrayList<String> tags){
        ArrayList<Product> products = new ArrayList<>();
        products.addAll(allProducts);
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

    public ArrayList<String> getTags() {
        return tags;
    }

    @Override
    public String getName() {
        return productBarcode;
    }

    public int getCost() {
        return cost;
    }

    public static boolean
    removeProduct(Product product) {  //need to debug after implementation add product in sellerMenu.
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

    public static String givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect() {
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


    public static void createProduct(String name, String company, int cost, Category category, String description, int amountOfExist, ArrayList<String> tags, ArrayList<Seller> sellers) {
        allProducts.add(new Product(name, company, cost, category, description, amountOfExist, tags, sellers));
        SaveAndLoad.getSaveAndLoad().writeJSON(allProducts, ArrayList.class, "allProducts");
    }

    public void setAmountOfExist(int amountOfExist) {
        this.amountOfExist += amountOfExist;
    }

    public void offTheCost(int off) {
        this.cost -= off;
    }

    public boolean isInOffOrNot() {
        return isInOffOrNot;
    }

    public void setInOffOrNot(boolean inOffOrNot) {
        isInOffOrNot = inOffOrNot;
    }

    public void setAverageScore(int newScore) {
        int lastAverage = this.averageScore;
        this.averageScore = (lastAverage*scoreNo + newScore)/(scoreNo + 1);
        this.scoreNo += 1;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }


    @Override
    public String toString() {
        return "Product{" +
                "productBarcode='" + productBarcode + '\'' +
                ", name='" + name + '\'' +
                ", categoryTags=" + categoryTags +
                ", company='" + company + '\'' +
                ", cost=" + cost +
                ", sellers=" + sellers +
                ", existsOrNot=" + existsOrNot +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", comments=" + comments +
                ", averageScore=" + averageScore +
                ", amountOfExist=" + amountOfExist +
                ", tags=" + tags +
                '}';
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
