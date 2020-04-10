package Model;

public class RequestANewSellerAccount extends Request {
    private String userName;
    private String passWord;
    public RequestANewSellerAccount(String requestType, String userName, String passWord) {
        super(requestType);
        this.userName = userName;
        this.passWord = passWord;
    }
}
