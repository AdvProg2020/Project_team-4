package View;


import Controll.Controller;
import Model.Account;
import Model.Customer;
import Model.Product;
import Model.Seller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        switch (manager.CreateAccount(splitInput[2], splitInput[3], requestPassword())) {
            case 1:
            case 2:
            case 3:
        }
    }

    public static void processLogin(String[] splitInput) {
        switch (manager.Login(splitInput[1], requestPassword())) {
            case 1:
            case 2:
            case 3:
        }
        switch (Controller.getController().getTypeFromUsername(splitInput[1])) {
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

    public static void getPersonalInfo(){
        ArrayList<String> personalInfos = new ArrayList<>();
        personalInfos.addAll(Controller.getController().requestPersonalInfo());
        for (String personalInfo : personalInfos) {
            System.out.println(personalInfo);
        }
    }

    public static void getCompanyInfo(){
        ArrayList<String> companyInfos = new ArrayList<>();
        companyInfos.addAll(Controller.getController().requestCompanyInfo());
        for (String companyInfo : companyInfos) {
            System.out.println(companyInfo);
        }
    }

    public static void editField(String[] splitInput){
        switch(Controller.getController().requestEditField(splitInput[1], splitInput[3])){
            case true:
            case false:
        }
    }

    public static void getSalesHistory(){
        ArrayList<String> salesHistory = new ArrayList<>();
        salesHistory.addAll(Controller.getController().requestSalesHistoryInfo());
        for (String saleHistory : salesHistory) {
            System.out.println(saleHistory);
        }
    }

    public static void manageProducts(){
        ArrayList<Product> products = new ArrayList<>();
        products.addAll(Controller.getController().requestListOfProducts());
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static void getProductInfo(){
        System.out.println(Controller.getController().requestProductInfo());
    }

    public static void getProductBuyers(){
        ArrayList<Account> buyers = new ArrayList<>();
        buyers.addAll(Controller.getController().requestProductBuyers());
        for (Account buyer : buyers) {
            System.out.println(buyer);
        }
    }

    public static void showEditableField(){
        ArrayList<String> fields = new ArrayList<>();
        fields.addAll(Controller.getController().requestProductFields());
        for (String field : fields) {
            System.out.println(field);
        }
        getCommandEdit();
    }

    public static void getCommandEdit(){
        System.out.println("Enter field that you want to edit: ");
        String editField = scanner.nextLine();
        System.out.println("Enter new value: ");
        String newValue = scanner.nextLine();
        Controller.getController().requestEdit(editField, newValue);
    }

    public static void addProduct(){
        Controller.getController().requestAddingNewProduct();
    }

    public static void showAddProductFields(){
        System.out.println("Enter product barcode:");
        String productBarcode = scanner.nextLine();
        System.out.println("");
        scanner.nextLine();
        System.out.println("");
        String generalFields = scanner.nextLine();
        System.out.println("");
        scanner.nextLine();
    }

    public static void

    public static void

    public static void

    public static void

    public static void

    public void run() {
        mainMenu.run(null, null);

    }

    public static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
