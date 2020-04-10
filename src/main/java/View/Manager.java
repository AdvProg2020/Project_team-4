package View;

public class Manager {
    public int CreateAccount(String type, String username, String password){
        return requestCreateAccount(type, username, password);
    }
    public int Login(String username, String password){
        return requestLogin(username, password);
    }


}
