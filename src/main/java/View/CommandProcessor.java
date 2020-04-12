package View;

import Control.Controller;

import java.util.ArrayList;
import java.util.Scanner;
import static View.Manager.*;

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
        switch (Manager.CreateAccount(splitInput[2], splitInput[3], requestPassword())) {
            case 1:
            case 2:
            case 3:
        }
    }

    public static void processLogin(String[] splitInput) {
        switch (Manager.Login(splitInput[1], requestPassword())) {
            case 1:
            case 2:
            case 3:
        }
        switch (Controller.getOurController().getTypeFromUsername(splitInput[1])) {
            case "manager":
                managerMenu.run(createLoginMenu, null);
                break;
            case "seller":
                sellerMenu.run(createLoginMenu, null);
                break;
            case "customer":
                customerMenu.run(createLoginMenu, null);
                break;
        }
    }


}
