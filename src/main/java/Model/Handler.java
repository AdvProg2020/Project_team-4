package Model;

public class Handler {

    private static final Handler handler = new Handler();


    private Handler() {
    }

    public static Handler getHandler() {
        return handler;
    }

    public static void login(Account account) {
        loggedInAccount = account;
    }

    public static boolean logout(){
        if(loggedInAccount == null){
            return false;
        }
        loggedInAccount = null;
        return true;
    }

    public static Account getLoggedInAccount() {
        return loggedInAccount;
    }


}
