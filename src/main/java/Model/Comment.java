package Model;


import java.util.ArrayList;

public class Comment extends SaveAble {
    private String commentBarcode;
    private static ArrayList<Comment> allComments = new ArrayList<>();
    private String commentingAccount;
    private String product;
    private String commentText;
    private String replay;
    

    public void setReplay(String replay) {
        this.replay = replay;
    }

    public String getReplay() {
        return replay;
    }

    public static ArrayList<Comment> getAllComments() {
        return allComments;
    }

    public String getCommentText() {
        return commentText;
    }

    public String getCommentingAccount() {
        if (Account.getAccountWithName(commentingAccount).getFirstName() == null || Account.getAccountWithName(commentingAccount).getLastName() == null)
            return commentingAccount;
        else
            return Account.getAccountWithName(commentingAccount).getFullName();
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

    public boolean hasBoughtTheProductOrNot(String customer) {
        return false;
    }

    @Override
    protected String getName() {
        return commentBarcode;
    }
}
