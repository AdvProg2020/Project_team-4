package Model;


import java.util.ArrayList;

public class Comment extends SaveAble {
    private String commentBarcode;
    private static ArrayList<Comment> allComments = new ArrayList<>();
    private String commentingAccount;
    private String product;
    private String commentText;

    public static ArrayList<Comment> getAllComments() {
        return allComments;
    }

    public String getCommentText() {
        return commentText;
    }

    public String getCommentingAccount() {
        return commentingAccount;
    }

    private enum commentStatus {
        PROCESS, APPROVED, REJECTED
    }
    private boolean boughtTheProductOrNot;

    public Comment(String commentingAccount, String product, String commentText, boolean boughtTheProductOrNot) {
        this.commentingAccount = commentingAccount;
        this.product = product;
        this.commentText = commentText;
        this.boughtTheProductOrNot = boughtTheProductOrNot;
        allComments.add(this);
    }

    public String getCommentBarcode() {
        return commentBarcode;
    }

    public boolean hasBoughtTheProductOrNot (String customer) {
        return false;
    }

    @Override
    protected String getName() {
        return commentBarcode;
    }
}
