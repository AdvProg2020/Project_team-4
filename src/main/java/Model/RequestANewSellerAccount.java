package Model;

import java.util.ArrayList;

public class RequestANewSellerAccount extends Request {
    private static ArrayList<RequestANewSellerAccount> allRequestANewSellerAccounts;
    private String userName;
    private String passWord;
    public RequestANewSellerAccount(String requestType, String userName, String passWord) {
        super(requestType);
        this.userName = userName;
        this.passWord = passWord;
        allRequestANewSellerAccounts.add(this);
    }
}
