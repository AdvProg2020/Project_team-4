package Model;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class RequestProduct extends Request {
    private Product product;

    public RequestProduct(RequestType requestType, Product product) {
        super(requestType);
        this.product = product;
        Manager.getEditProductsRequests().add(this);
        //Product.getAllProducts().remove(product);
        SaveAndLoad.getSaveAndLoad().writeJSON(Manager.getEditProductsRequests(), ArrayList.class, "editProductsRequests");
    }

    public Product getProduct() {
        return product;
    }

    public String getProductName() {
        return product.getName();
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
}
