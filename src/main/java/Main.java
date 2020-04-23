import Model.Account;
import Model.CodedOff;
import Model.Off;
import Model.Product;
import View.Menu.MainMenu;
import View.Menu.Menu;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File file = new File(Account.class + "");
        File file1 = new File(Product.class + "");
        File file2 = new File(Off.class + "");
        File file3 = new File(CodedOff.class + "");
        MainMenu mainMenu = new MainMenu();
        mainMenu.execute();
    }

}