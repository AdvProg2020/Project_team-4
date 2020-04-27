package Model;



import java.util.ArrayList;

public class Category extends SaveAble {
    private static ArrayList<Category> allCategories;
    private String name;
    private ArrayList<String> tags;
    private ArrayList<Category> subCategories;
    private ArrayList<Product> products;

    public Category(String name) {
        this.name = name;
        this.tags = new ArrayList<String>();
        this.subCategories = new ArrayList<Category>();
        this.products = new ArrayList<Product>();
        allCategories.add(this);
    }

    public static ArrayList<Product> searchInCategories() {
        return null;
    }

    public String getName() {
        return name;
    }

    public static ArrayList<Product> getProductsOfACategory(String categoryName, ArrayList<String> filters) {
        ArrayList<Product> productsToShow = new ArrayList<Product>();
        if (getCategoryByName(categoryName).products.size() != 0) {
            for (Product product : getCategoryByName(categoryName).products) {
                if (filters.size() == 0 || product.getCategoryTags().contains(filters)) {
                    productsToShow.add(product);
                }
            }
        }
        for (Category subCategory : getCategoryByName(categoryName).subCategories) {
            if (subCategory.products != null) {
                subCategory.getProductsOfACategory(subCategory.getName(), filters);
            }
        }
        return productsToShow;
    }

    public static Category getCategoryByName(String name) {
        for (Category category : allCategories) {
            if (category.getName().equalsIgnoreCase(name)) {
                return category;
            }
        }
        return null;
    }

    public static ArrayList<Category> getAllCategories() {
        return allCategories;
    }

    public static String compareBetweenTwoProduct(Category category, Product product1, Product product2) {
        return null;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", tags=" + tags +
                ", subCategories=" + subCategories +
                ", products=" + products +
                '}';
    }




}
