package View;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandProcessor {
    public static Manager manager = new Manager();
    public static Scanner scanner;
    public static ArrayList<String> validCommands = new ArrayList<>();

    //Menus.
    private Menu createLoginMenu = new CreateLoginMenu();
    private Menu managerMenu = new ManagerMenu();
    private Menu sellerMenu = new SellerMenu();
    private Menu customerMenu = new CustomerMenu();
    private Menu productMenu = new ProductMenu();
    private Menu offMenu = new OffMenu();
    private Menu mainMenu = new MainMenu();

    public CommandProcessor(Manager boss) {
        this.manager = manager;
        scanner = new Scanner(System.in);
    }


    public static boolean isValidCommand(String command) {
        for (String validCommand : validCommands) {
            if (validCommand.equals(command)) {
                return true;
            }
        }
        return false;
    }
    public static String requestPassword() {
        System.out.println("Enter your password:");
        return CommandProcessor.scanner.nextLine();
    }

    public static void processCreateAccount(String[] splitInput) {
        switch(manager.CreateAccount(splitInput[2], splitInput[3], requestPassword())){
            case 1:
            case 2:
            case 3:
        }
    }

    public static void processLogin(String[] splitInput) {
        switch (manager.Login(splitInput[1], requestPassword())){
            case 1:
            case 2:
            case 3:
        }
        switch(getTypeFromUsername(splitInput)){
            case "manager":
                managerMenu.run(createLoginAccount, null);
                break;
            case "seller":
                sellerMenu.run(createLoginAccount);
        }
    }



    public void managerMenu() {
        String input;
        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            if (getMatcher(input, "view personal info").find()) {

            } else if (getMatcher(input, "manage users").find()) {

            } else if (getMatcher(input, "manage all products").find()) {

            } else if (getMatcher(input, "create discount code").find()) {

            } else if (getMatcher(input, "view discount codes").find()) {

            } else if (getMatcher(input, "manage requests").find()) {

            } else if (getMatcher(input, "manage categories").find()) {

            } else if (getMatcher(input, "help").find()) {

            } else if (getMatcher(input, "back").find()) {

            } else if (getMatcher(input, "sort by [field]").find()) {

            } else {
                System.err.println("invalid command");
            }
        }
    }

    public void sellerMenu() {
        String input;
        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            if (getMatcher(input, "view personal info").find()) {

            } else if (getMatcher(input, "view company information").find()) {

            } else if (getMatcher(input, "view sales history").find()) {

            } else if (getMatcher(input, "manage products").find()) {

            } else if (getMatcher(input, "add product").find()) {

            } else if (getMatcher(input, "remove product [productId]").find()) {

            } else if (getMatcher(input, "show categories").find()) {

            } else if (getMatcher(input, "view offs").find()) {

            } else if (getMatcher(input, "view balance").find()) {

            } else if (getMatcher(input, "help").find()) {

            } else if (getMatcher(input, "back").find()) {

            } else if (getMatcher(input, "sort by [field]").find()) {

            } else {
                System.err.println("invalid command");
            }
        }
    }


    public void customerMenu() {
        String input;
        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            if (getMatcher(input, "view personal info").find()) {

            } else if (getMatcher(input, "view cart").find()) {

            } else if (getMatcher(input, "purchase").find()) {

            } else if (getMatcher(input, "view orders").find()) {

            } else if (getMatcher(input, "view balance").find()) {

            } else if (getMatcher(input, "view discount codes").find()) {

            } else if (getMatcher(input, "products").find()) {

            } else if (getMatcher(input, "view categories").find()) {

            } else if (getMatcher(input, "filtering").find()) {

            } else if (getMatcher(input, "sorting").find()) {

            } else if (getMatcher(input, "show products").find()) {

            } else if (getMatcher(input, "show product [productId]").find()) {

            } else if (getMatcher(input, "help").find()) {

            } else if (getMatcher(input, "back").find()) {

            } else if (getMatcher(input, "sort by [field]").find()) {

            } else {
                System.err.println("invalid command");
            }
        }
    }

    public void productMenu(String previousMenu) {
        String input;
        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            if (getMatcher(input, "digest").find()) {

            } else if (getMatcher(input, "attributes").find()) {

            } else if (getMatcher(input, "compare [productID]").find()) {

            } else if (getMatcher(input, "Comments").find()) {

            } else if (getMatcher(input, "help").find()) {

            } else if (getMatcher(input, "back").find()) {

            } else if (getMatcher(input, "sort by [field]").find()) {

            } else {
                System.err.println("invalid command");
            }
        }
    }

    public void run() {
        mainMenu.run(null, null);

    }


    public static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
