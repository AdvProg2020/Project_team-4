import Control.Controller;
import Model.*;
import View.Menu.MainMenu;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        File file = new File(String.valueOf(Customer.class));
        file.mkdir();
        File file6 = new File(String.valueOf(Seller.class));
        file6.mkdir();
        File file7 = new File(String.valueOf(Manager.class));
        file7.mkdir();
        File file1 = new File(Product.class + "");
        file1.mkdir();
        File file2 = new File(Off.class + "");
        file2.mkdir();
        File file3 = new File(CodedOff.class + "");
        file3.mkdir();
        File file4 = new File(Seller.class + "");
        file4.mkdir();
        File file5 = new File(String.valueOf(RequestANewSellerAccount.class));
        file5.mkdirs();
        File file8 = new File(String.valueOf(CodedOff.class));
        file8.mkdirs();
        File file9 = new File(String.valueOf(String.class));
        file9.mkdirs();
        File file10 = new File(String.valueOf(ArrayList.class));
        file10.mkdirs();
        Controller.readOffCodesFromFile();
        MainMenu mainMenu = new MainMenu();
        mainMenu.execute();
    }

}