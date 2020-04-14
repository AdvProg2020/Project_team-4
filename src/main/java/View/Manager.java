package View;

import Control.Controller;
import Model.Product;
import View.Menu.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Model.Category.getCategoryByName;
import static View.CommandsSource.findEnum;
import static View.Menu.ProductMenu.giveProductId;
import static View.Outputs.*;
import static java.lang.Double.parseDouble;


public class Manager {

    //--------> fields
    public static Scanner scanner;
    public static CommandsSource commandsSource;
    public static CommandProcessor commandProcessor = new CommandProcessor(this);
    public static ArrayList<String> validCommands = new ArrayList<>();

    //Menus.
    private static Menu createLoginMenu;
    private static Menu managerMenu;
    private static Menu sellerMenu;
    private static Menu customerMenu;
    private static Menu productMenu;
    private static Menu offMenu;
    private static Menu mainMenu;

    // ----> constructor
    public Manager() {
    }


    // ----> methods





    public static boolean isAnyUserLogin(){
        return Controller.getOurController().requestIsAnyUserLogin();
    }

    public static String getType(){
        return Controller.getOurController().requestLoginedUser();
    }




    //----> ProductMenu





    //----> OffsMenu

    public static void offsList(){
        printOffsListResult(Controller.getOurController().requestOffsList());
    }

    public static void showProduct(String productId, Menu previousMenu, String input){
        giveProductId(productId);
        productMenu.execute(previousMenu, input);
    }

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



    // ----> checked methods
    public static void digest(String productId){
        printDigestResult(Controller.getOurController().requestDigest(productId));
    }

    public void execute() {
        mainMenu.execute(null, null);
    }

    public static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

    public static void createAccount(String type, String username, String password) {
        printCreateAccountResult(Controller.getOurController().requestCreateAccount(type, username, password));
    }

    public static void login(String username, String password) {
        printLoginResult(Controller.getOurController().requestLogin(username, password));
    }

    public static String getPassword() {
        System.out.println("Enter your password:");
        return CommandProcessor.scanner.nextLine();
    }

    public static int getProductInfoForAddProduct() {
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
        printAddProductResult(Controller.getOurController().requestAddProduct(name, company, cost, getCategoryByName(category), description,
                requestLoggedInUser()));
    }

    public static void addProduct() {
        getProductInfoForAddProduct();
    }

    public static boolean isValidCommand(String command) {
        if(findEnum(commandsSource.getAllRegex(), command).equals(null))
            return false;
        else
            return true;
    }
}
