package Model;

import java.util.ArrayList;

public class RequestANewSellerAccount extends Request {
    private static ArrayList<RequestANewSellerAccount> allRequestANewSellerAccounts = new ArrayList<>();
    private String userName;
    private String passWord;
    public RequestANewSellerAccount(String requestType, String userName, String passWord) {
        super(requestType);
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    @Override
    public String toString() {
        return "RequestANewSellerAccount{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", requestId=" + requestId +
                '}';
    }
}
