package Control;

import Model.*;

import java.util.ArrayList;

public class Controller {

    private final static Controller ourController = new Controller();

    public static Controller getOurController() {
        return ourController;
    }

    public int controllerNewAccount(String type, String username, String password){
        if (Account.getAccountWithName(username) != null){
            return 2;
        }
        switch (type) {
            case "customer":
                Customer.newCustomer(username, password);
                return 1;
            case "seller":
                Seller.addANewSeller(username, password);
                return 1;
            case "manager":
                if (!Manager.addANewManager(false, username, password)) {
                    return 4;
                }
                //first manager created
                return 1;
            default:
                return 3;
        }
    }

    public int controllerLogin(String username, String password){
        if(Account.getAccountWithName(username) == null){
            //Account not found
            return 2;
        }if(Account.getAccountWithName(username).getPassWord().equals(password)){
            Account.login(Account.getAccountWithName(username));
            return 1;
        }
        return 3;
    }

    public Account controllerShowUser(String username){
       return Account.getAccountWithName(username);
    }

    public void controllerDeleteAnUser(String username){
        Account.deleteAccount(Account.getAccountWithName(username));
    }

    public void controllerCreateNewManagerAccountFromManager(String username, String firstName, String lastName, String email, String phoneNumber, String password){
        // voroodi ha kheili ezafie!
        Manager.addANewManager(true, username, password);
    }

    public void controllerRemoveProduct(String productName){
        Manager.remove(Product.getProductWithName(productName));
    }

    public void controllerCreateOffCode(String barcode, String startingTime, String endingTime, double offAmount, int usageTimes){
        new Off(barcode, startingTime, endingTime, offAmount);
    }

    public ArrayList<CodedOff> getAllDiscounts(){
        return CodedOff.getAllDiscounts();
    }


}

