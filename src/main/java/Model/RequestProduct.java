package Model;

import java.util.ArrayList;
import java.util.Objects;

public class RequestProduct extends Request {
    private Product product;

    public RequestProduct(RequestType requestType, Product product) {
        super(requestType);
        this.product = product;
//        Manager.getEditProductsRequests().add(this);
//        for dupblicate creating
        //Product.getAllProducts().remove(product);
        SaveAndLoad.getSaveAndLoad().writeJSON(Manager.getEditProductsRequests(), ArrayList.class.toString(), "editProductsRequests");
    }

    public void setProduct(Product product) {
        this.product = product;
    }


//    public String getProductBarcode() {
//        return productBarcode;
//    }

    public Product getProduct() {
        return product;
    }

    public String getProductName() {
        return product.getProductBarcode();
    }


    public ArrayList<String> getProductCategoryTags() {
        return product.getCategoryTags();
    }


    public String getProductCompany() {
        return product.getCompany();
    }

    public boolean getProductOffStatus() {
        return product.isInOffOrNot();
    }

    public int getProductCost() {
        return product.getCost();
    }

    public String getProductCategory() {
        return product.getCategory();
    }

    public String getProductDescription() {
        return product.getDescription();
    }

    public int getProductAmountOfExist() {
        return product.getAmountOfExist();
    }

    public ArrayList<String> getProductTags() {
        return product.getTags();
    }

    @Override
    protected String getName() {
        return product.getName();
    }

    @Override
    public String toString() {
        return "RequestProduct{" +
                "product=" + product +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestProduct that = (RequestProduct) o;
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

    public ArrayList<String> getSellers() {
        return new ArrayList<>(product.getSellers());
    }
}
