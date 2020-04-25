package Model;


import java.util.ArrayList;

public class Comment extends SaveAble{
    private  String commentBarcode;
    private static ArrayList<Comment> allComments;
    private Account commentingAccount;
    private Product product;
    private String commentText;
    private enum commentStatus {
        PROCESS, APPROVED, REJECTED
    }
    private boolean boughtTheProductOrNot;

    public Comment(Account commentingAccount, Product product, String commentText, boolean boughtTheProductOrNot) {
        this.commentingAccount = commentingAccount;
        this.product = product;
        this.commentText = commentText;
        this.boughtTheProductOrNot = boughtTheProductOrNot;
        allComments.add(this);
    }

    public String getCommentBarcode() {
        return commentBarcode;
    }

    public boolean hasBoughtTheProductOrNot (Customer customer) {
        return false;
    }

    @Override
    protected String getName() {
        return commentBarcode;
    }
}
