package Model;



import javafx.scene.image.Image;

import java.nio.charset.Charset;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.time.format.DateTimeFormatter;

public class Product extends SaveAble {
    private static ArrayList<Product> allProducts = new ArrayList<>();
    private String productBarcode;
    private  ArrayList<String> byers = new ArrayList<>();

    public static Product getProductWithName(String product) {
        for (Product product1: allProducts) {
            if (product1.getNameOfProductNotBarcode().equalsIgnoreCase(product)) {
                return product1;
            }
        }
        return null;
    }

    public void setByers(String userName) {
        this.byers.add(userName);
    }

    //private static HashMap<String, Product> products;
    private enum  productStatus {
        MAKING, EDITING, APPROVED
    }
    private String name;
    private int seen;
    private String localDateTime;
    private ArrayList<String> categoryTags;
    private String company;
    private int cost;
    private ArrayList<String> sellers;
    private boolean existsOrNot;
    private String category;
    private String description;
    private ArrayList<Comment> comments;
    private int scoreNo;
    private int averageScore;
    private int amountOfExist;
    private boolean isInOffOrNot;
    private ArrayList<String> tags;
    private static int giveId;
    private String endTime;
//    private Image image;


    public void addComment(Comment comment){
        comments.add(comment);
    }

    public String getRemainTime() {
        LocalDate localDate = LocalDate.now();
        String str = endTime;
        if(str == null){
            return "no limit";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(str, formatter);
        return String.valueOf(Period.between(dateTime, localDate));
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getScoreNo() {
        return scoreNo;
    }

    public static int getGiveId() {
        return giveId;
    }

    public void setSeen(int seen) {
        this.seen += seen;
    }

    public int getSeen() {
        return seen;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public Product(String name, String company, int cost, String category, String description, int amountOfExist, ArrayList<String> tags, String firstSellerName) {
        this.productBarcode = givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect();
        this.name = name;
        this.categoryTags = new ArrayList<>();
        this.company = company;
        this.cost = cost;
        this.sellers = new ArrayList<>();
        System.out.println(firstSellerName);
        sellers.add(firstSellerName);
        System.out.println(sellers);
        this.category = category;
        this.description = description;
        this.comments = new ArrayList<>();
        this.tags = tags;
        this.amountOfExist = amountOfExist;
        this.localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        giveId++;
    }

    public void setSellers(String sellers) {
        this.sellers.add(sellers);
        SaveAndLoad.getSaveAndLoad().saveGenerally();
    }

    //    public Image getImage() {
//        return image;
//    }

//    public Product(String name, String company, int cost, String category, String description, int amountOfExist, ArrayList<String> tags, Image image1) {
//        this.productBarcode = givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect();
//        this.name = name;
//        this.categoryTags = new ArrayList<>();
//        this.company = company;
//        this.cost = cost;
//        this.sellers = new ArrayList<>();
//        this.category = category;
//        this.description = description;
//        this.comments = new ArrayList<>();
//        this.tags = tags;
//        this.sellers = new ArrayList<>();
//        this.amountOfExist = amountOfExist;
//        this.localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
//        this.image = image1;
//        giveId++;
//    }

    public Product(Product product) {
        this.productBarcode = product.getProductBarcode();
        this.name = product.getNameOfProductNotBarcode();
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
        this.localDateTime = product.getLocalDateTime();
        Seller seller= ((Seller) Account.getAccountWithName(product.getSellers().get(0)));
        System.out.println(seller);
        seller.getProducts().add(product.getProductBarcode());
        System.out.println(seller);
        SaveAndLoad.getSaveAndLoad().writeJSON(seller, Seller.class.toString(), seller.getName());
        System.out.println(Account.getAccountWithName(seller.getName()));
        allProducts.add(this);
        SaveAndLoad.getSaveAndLoad().writeJSON(allProducts, ArrayList.class.toString(), "allProducts");
    }

    public String getProductBarcode() {
        return productBarcode;
    }

    public String getByers() {
        return byers.toString();
    }

    public String getCompany() {
        return company;
    }

    public ArrayList<String> getSellers() {
        return sellers;
    }

    public boolean isExistsOrNot() {
        if (amountOfExist > 0) {
            return true;
        }
        return false;
    }

    public String getCategory() {
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



    public static ArrayList<Product> getProductsWithTags(String name, ArrayList<String> tags) {
        ArrayList<Product> products = new ArrayList<>();
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
    public String getName() {
        return productBarcode;
    }

    public String getNameOfProductNotBarcode() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public static boolean
    removeProduct(Product product) {  //need to debug after implementation add product in sellerMenu.
        if (allProducts.contains(product)) {
            allProducts.remove(product);
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


    public static void createProduct(String name, String company, int cost, String category, String description, int amountOfExist, ArrayList<String> tags, String firstSellerName) {
        allProducts.add(new Product(name, company, cost, category, description, amountOfExist, tags, firstSellerName));
        SaveAndLoad.getSaveAndLoad().writeJSON(allProducts, ArrayList.class.toString(), "allProducts");
    }

    public void setAmountOfExist(int amountOfExist) {
        this.amountOfExist += amountOfExist;
    }

    public void offTheCost(double off) {
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
        this.averageScore = (lastAverage * scoreNo + newScore)/(scoreNo + 1);
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


    public void setCategory(String category) {
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

}
