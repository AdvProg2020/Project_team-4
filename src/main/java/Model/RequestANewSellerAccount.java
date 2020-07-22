package Model;

import java.util.ArrayList;
import java.util.Objects;

public class RequestANewSellerAccount extends Request {
    private static ArrayList<RequestANewSellerAccount> allRequestANewSellerAccounts = new ArrayList<>();
    private String userName;
    private String passWord;
    public RequestANewSellerAccount(RequestType requestType, String userName, String passWord) {
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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestANewSellerAccount that = (RequestANewSellerAccount) o;
        return userName.equals(that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    @Override
    protected String getName() {
        return userName;
    }
}
