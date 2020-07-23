package Model;

import java.util.ArrayList;
import java.util.Date;

public class Auction {
    private Date expireDate;
    private Account seller;
    private ArrayList<String> chatMassages = new ArrayList<>();

    public Date getExpireDate() {
        return expireDate;
    }

    public Account getSeller() {
        return seller;
    }

    public ArrayList<String> getChatMassages() {
        return chatMassages;
    }

    public Auction(Date expireDate, Account seller) {
        this.expireDate = expireDate;
        this.seller = seller;
    }
}
