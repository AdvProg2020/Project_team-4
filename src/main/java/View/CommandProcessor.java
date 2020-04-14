package View;



<<<<<<< HEAD
import View.Menu.*;
=======
import View.Menu.Menu;
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb

import java.util.ArrayList;
import java.util.Scanner;
import static View.Manager.*;

public class CommandProcessor {
    public static Manager manager = new Manager();
    public static Scanner scanner;
    public static ArrayList<String> validCommands = new ArrayList<>();

<<<<<<< HEAD
=======
    //Menus.
    private static Menu createLoginMenu = new CreateLoginMenu();
    private static Menu managerMenu = new ManagerMenu();
    private static Menu sellerMenu = new SellerMenu();
    private static Menu customerMenu = new CustomerMenu();
    private static Menu productMenu = new ProductMenu();
    private static Menu offMenu = new OffMenu();
    private static Menu mainMenu = new MainMenu();

>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
    public CommandProcessor(Manager boss) {
        this.manager = manager;
        scanner = new Scanner(System.in);
    }

    public static void processCreateAccount(String[] splitInput) {
<<<<<<< HEAD
        Manager.createAccount(splitInput[2], splitInput[3], getPassword());
    }

    public static void processLogin(String[] splitInput) {
        Manager.login(splitInput[1], getPassword());
=======
        Manager.createAccount(splitInput[2], splitInput[3], requestPassword());
    }

    public static void processLogin(String[] splitInput) {
        Manager.login(splitInput[1], requestPassword());
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
    }

    public static void processOffsList(String[] splitInput) {
        Manager.offsList();
    }

<<<<<<< HEAD
    public static void processShowProduct(String[] splitInput, Menu previousMenu, String input){
        Manager.showProduct(splitInput[2], previousMenu, input);
    }

    public static void processDigest(String productId){
        Manager.digest(productId);
    }

    public static void processAddToCart() {
        addToCart();
    }

    public static void processSelectSeller(String[] splitInput){
        selectSeller(splitInput[2]);
    }




=======
    public static void processShowProduct(String[] splitInput){
        Manager.showProduct(splitInput[2]);
    }

>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
}
