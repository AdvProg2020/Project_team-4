import Model.*;
import View.Menu.MainMenu;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File file = new File(Manager.class + "");
        file.mkdir();
        File file0 = new File(Customer.class + "");
        file0.mkdir();
        File file4 = new File(Seller.class + "");
        file4.mkdir();
        File file1 = new File(Product.class + "");
        file1.mkdir();
        File file2 = new File(Off.class + "");
        file2.mkdir();
        File file3 = new File(CodedOff.class + "");
        file3.mkdir();
        MainMenu mainMenu = new MainMenu();
        mainMenu.execute();
    }

}