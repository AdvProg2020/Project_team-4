package Controll;

import Model.*;

public class Controller {

    public boolean validNewName(String username){
        return Account.getAccountWithName(username) == null;
    }

    public void createAccount(String username, String type, String password){
        //call create new Account method in Model
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
            return 1;
        }
        //means wrong password
        return 3;
    }


}
