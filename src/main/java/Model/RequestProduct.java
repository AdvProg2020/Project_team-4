package Model;

public class RequestProduct extends Request {
    private Product product;

    public RequestProduct(String requestType, String requestId, Product product) {
        super(requestType, requestId);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
