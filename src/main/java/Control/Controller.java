package Control;

import Model.Account;
import Model.Customer;
import Model.Manager;
import Model.Seller;

public class Controller {
    private final static Controller ourController = new Controller();

    public static Controller getOurController() {
        return ourController;
    }

    public int requestCreateAccount(String type, String username, String password){
        if (Account.getAccountWithName(username) != null){
            return 2;
        }
        if(type.equals("customer")){
            new Customer(username, password);
            return 1;
        }else if(type.equals("seller")){
            Seller.addANewSeller(username, password);
            return 1;
        }else if(type.equals("manager")){
            Manager.addANewManager(username, password);
            //request send
            return 1;
        }else return 3;
    }

    public int requestLogin(String username, String password){
        if(Account.getAccountWithName(username) == null){
            return 2;
        }if(Account.getAccountWithName(username).getPassWord().equals(password)){
            //login this

        }
        return 3;
    }


}

