package Controll;

import Model.*;

public class Controller {

    public static Controller controller = new Controller();
    private Account loggedInAccount;

    private Controller(){

    }

    public static Controller getController(){
        return controller;
    }

    public boolean validNewName(String username){
        return Account.getAccountWithName(username) == null;
    }

    public void createAccount(String username, String type, String password){
        if(type.equals("customer")){
            //call method for new customer
        }else if(type.equals("seller")){
            //call method for new seller
        }else{
            //call method for new manager
        }
    }

    public int login(String username, String password){
        Account account;
        if((account = Account.getAccountWithName(username)) == null){
            // means that "this name isn't exist"
            return 2;
        }
        if(account.getPassWord().equals(password)){
            // call login methos in model if needed
            // means successfully logged in
            loggedInAccount = account;
            return 1;
        }
        //means wrong password
        return 3;
    }


}
