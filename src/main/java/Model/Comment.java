package Model;



public class Comment {
    private Account commentingAccount;
    private Product product;
    private String commentText;
    private enum commentStatus {
        inProcessOfApproval, approved, notApproved
    }
    private boolean boughtTheProductOrNot;

    public boolean hasBoughtTheProductOrNot (Customer customer) {
        return false;
    }

}
