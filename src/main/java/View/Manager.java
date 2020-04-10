package View;

import Controll.Controller;

public class Manager {
    public int CreateAccount(String type, String username, String password) {
        return Controller.getController().requestCreateAccount(type, username, password);
    }

    public int Login(String username, String password) {
        return Controller.getController().requestLogin(username, password);
    }


}
