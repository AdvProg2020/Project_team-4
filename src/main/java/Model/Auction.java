package Model;

import java.util.ArrayList;
import java.util.Date;

public class Auction {
    private static ArrayList<Auction> auctions = new ArrayList<>();
    private final Date expireDate;
    private final Account seller;
    private final ArrayList<String> chatMassages;
    private final Product product;
    private static double maximumOfferedPrice;

    public Date getExpireDate() {
        return expireDate;
    }

    public Account getSeller() {
        return seller;
    }

    public ArrayList<String> getChatMassages() {
        return chatMassages;
    }

    public Auction(Date expireDate, Account seller, Product product) {
        this.expireDate = expireDate;
        this.seller = seller;
        this.chatMassages = new ArrayList<>();
        this.product = product;
    }

    public static void offerPrice(double offer){
        if(offer > maximumOfferedPrice){
            maximumOfferedPrice = offer;
        }
    }
}
