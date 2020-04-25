package Model;

import java.util.ArrayList;

public class RequestANewSellerAccount extends Request {
    private static ArrayList<RequestANewSellerAccount> allRequestANewSellerAccounts;
    private String userName;
    private String passWord;
    public RequestANewSellerAccount(String requestId, String requestType, String userName, String passWord) {
        super(requestType, requestId);
        this.userName = userName;
        this.passWord = passWord;
        allRequestANewSellerAccounts.add(this);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }
}
