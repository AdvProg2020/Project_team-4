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
            if(!Manager.addANewManager(username, password)){
                return 4;
            }
            //request send
            return 1;
        }else return 3;
    }

    public int requestLogin(String username, String password){
        if(Account.getAccountWithName(username) == null){
            return 2;
        }if(Account.getAccountWithName(username).getPassWord().equals(password)){
            Account.login(Account.getAccountWithName(username));
            return 1;
        }
        return 3;
    }

    public Account showAnUser(String username){
       return Account.getAccountWithName(username);
    }

    public Account deleteAnUser(String username){
        Account account = Account.getAccountWithName(username);
        Account.deleteAccount(username);
    }
}

