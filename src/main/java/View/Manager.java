package View;

import Control.Controller;
import Model.Account;
import Model.Product;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;


public class Manager {
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


    /**************************general functions*************************************/

    public static boolean isValidCommand(String command) {
        for (String validCommand : validCommands) {
            if (validCommand.equals(command)) {
                return true;
            }
        }
        return false;
    }

    public static String getType(){
        return Controller.getOurController.requestLoginedUser();
    }

    /*****************************Create/Login****************************************/

    public static int CreateAccount(String type, String username, String password) {
        return Controller.getOurController().requestCreateAccount(type, username, password);
    }

    public static int Login(String username, String password) {
        return Controller.getOurController().requestLogin(username, password);
    }


    public static String requestPassword() {
        System.out.println("Enter your password:");
        return CommandProcessor.scanner.nextLine();
    }

    /******************************************************************************/

    public static void getPersonalInfo() {
        ArrayList<String> personalInfos = new ArrayList<>();
        personalInfos.addAll(Controller.getOurController().requestPersonalInfo(), getType());
        for (String personalInfo : personalInfos) {
            System.out.println(personalInfo);
        }
    }

    public static void getCompanyInfo() {
        ArrayList<String> companyInfos = new ArrayList<>();
        companyInfos.addAll(Controller.getOurController().requestCompanyInfo());
        for (String companyInfo : companyInfos) {
            System.out.println(companyInfo);
        }
    }

    public static void editField(String[] splitInput) {
        switch (Controller.getOurController().requestEditField(splitInput[1], splitInput[3])) {
            case true:
            case false:
        }
    }

    public static void getSalesHistory() {
        ArrayList<String> salesHistory = new ArrayList<>();
        salesHistory.addAll(Controller.getOurController().requestSalesHistoryInfo());
        for (String saleHistory : salesHistory) {
            System.out.println(saleHistory);
        }
    }

    public static void manageProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.addAll(Controller.getOurController().requestListOfProducts());
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static void getProductInfo() {
        System.out.println(Controller.getOurController().requestProductInfo());
    }

    public static void getProductBuyers() {
        ArrayList<Account> buyers = new ArrayList<>();
        buyers.addAll(Controller.getOurController().requestProductBuyers());
        for (Account buyer : buyers) {
            System.out.println(buyer);
        }
    }

    public static void showEditableField() {
        ArrayList<String> fields = new ArrayList<>();
        fields.addAll(Controller.getOurController().requestProductFields());
        for (String field : fields) {
            System.out.println(field);
        }
        getCommandEdit();
    }

    public static void getCommandEdit() {
        System.out.println("Enter field that you want to edit: ");
        String editField = scanner.nextLine();
        System.out.println("Enter new value: ");
        String newValue = scanner.nextLine();
        Controller.getOurController().requestEdit(editField, newValue);
    }

    public static int getProductInfoForAdd() {
        System.out.println("Enter the name of product:");
        String name = scanner.nextLine();
        System.out.println("Enter the name of company:");
        String company = scanner.nextLine();
        System.out.println("Enter cost:");
        double cost = parseDouble(scanner.nextLine());
        System.out.println("Enter category:");
        String category = scanner.nextLine();
        System.out.println("Enter description:");
        String description = scanner.nextLine();
        return Controller.getOurController().requestAddingNewProduct(name, company, cost, category, description);
    }

    public static void addProduct() {
        switch (getProductInfoForAdd()) {
            case 0:
            case 1:
        }

    }

    public static void removeProduct() {

    }

    public static void showCategories() {

    }

    public static void viewOffs() {

    }

    public static void viewBalance() {

    }

    public static void showOffProductList(){

    }

    public void run() {
        mainMenu.run(null, null);
    }

    public static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }


    /*****************ControllerFunctions*******************/


}
