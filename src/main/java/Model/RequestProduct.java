package Model;

public class RequestProduct extends Request {
    private Product product;

    public RequestProduct(String requestType, Product product) {
        super(requestType);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public String getProductName() {
        return product.getName();
    }
}
