package Model;



import java.util.ArrayList;

public class Category extends SaveAble {
    private static ArrayList<Category> allCategories = new ArrayList<>();
    private String name;
    private ArrayList<String> tags;
    private ArrayList<String> subCategories;
    private ArrayList<String> products;

    public Category(String name, ArrayList<String> tags, ArrayList<String> products, ArrayList<String> subCategories) {
        this.name = name;
        if (allCategories.contains(getCategoryByName(name))) {
            deleteCategoryAndProducts(name);
        }
        this.tags = tags;
        this.subCategories = subCategories;
        this.products = products;
        for (String product: products) {
            Product.getProductWithBarcode(product).setCategoryTags(tags);
        }
        allCategories.add(this);
        SaveAndLoad.getSaveAndLoad().writeJSON(allCategories, ArrayList.class, "allCategories");
    }

    public static void deleteCategoryAndProducts(String name) {
        Category categoryToDelete = getCategoryByName(name);
        for (String product: categoryToDelete.products) {
            if (Product.getAllProducts().contains(Product.getProductWithBarcode(product))) {
                Product.getAllProducts().remove(Product.getProductWithBarcode(product));
            }
        }
        allCategories.remove(categoryToDelete);
        SaveAndLoad.getSaveAndLoad().saveGenerally();
    }

    public static ArrayList<String> searchInCategories() {
        return null;
    }

    public String getName() {
        return name;
    }

    public static ArrayList<Product> getProductsOfACategory(String categoryName, ArrayList<String> filters) {
        ArrayList<Product> productsToShow = new ArrayList<Product>();
        if (getCategoryByName(categoryName).products.size() != 0) {
            for (String product : getCategoryByName(categoryName).products) {
                if (filters.size() == 0 || Product.getProductWithBarcode(product).getCategoryTags().contains(filters)) {
                    productsToShow.add(Product.getProductWithBarcode(product));
                }
            }
        }
        for (String subCategory : getCategoryByName(categoryName).subCategories) {
            if (Category.getCategoryByName(subCategory).products != null) {
                Category.getCategoryByName(subCategory).getProductsOfACategory(Category.getCategoryByName(subCategory).getName(), filters);
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

    public static String compareBetweenTwoProduct(String category, String product1, String product2) {
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
