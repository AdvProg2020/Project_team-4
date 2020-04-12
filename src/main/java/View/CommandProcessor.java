package View;



import java.util.ArrayList;
import java.util.Scanner;
import static View.Manager.*;
import static View.Outputs.printCreateAccountResult;
import static View.Outputs.printLoginResult;

public class CommandProcessor {
    public static Manager manager = new Manager();
    public static Scanner scanner;
    public static ArrayList<String> validCommands = new ArrayList<>();

    //Menus.
    private static Menu createLoginMenu = new CreateLoginMenu();
    private static Menu managerMenu = new ManagerMenu();
    private static Menu sellerMenu = new SellerMenu();
    private static Menu customerMenu = new CustomerMenu();
    private static Menu productMenu = new ProductMenu();
    private static Menu offMenu = new OffMenu();
    private static Menu mainMenu = new MainMenu();

    public CommandProcessor(Manager boss) {
        this.manager = manager;
        scanner = new Scanner(System.in);
    }

    public static void processCreateAccount(String[] splitInput) {
        Manager.createAccount(splitInput[2], splitInput[3], requestPassword());
    }

    public static void processLogin(String[] splitInput) {
        Manager.login(splitInput[1], requestPassword());
    }

    public static void processOffsList(String[] splitInput) {
        Manager.offsList();
    }

    public static void processShowProduct(String[] splitInput){
        Manager.showProduct(splitInput[2]);
    }

}
