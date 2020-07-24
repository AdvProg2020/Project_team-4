package Model;

import java.util.ArrayList;
import java.util.Date;

public class Auction {
    private static ArrayList<Auction> auctions = new ArrayList<>();
    private final Date expireDate;
    private final Account seller;
    private final ArrayList<String> chatMassages;

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
        this.chatMassages = new ArrayList<>();
    }
}
