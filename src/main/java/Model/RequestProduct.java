package Model;

import java.util.ArrayList;

public class RequestProduct extends Request {
    private Product product;

    public RequestProduct(RequestType requestType, Product product) {
        super(requestType);
        this.product = product;
        Product.getAllProducts().remove(product);
        SaveAndLoad.getSaveAndLoad().writeJSON(Product.getAllProducts(), ArrayList.class, "allProducts");
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
}
