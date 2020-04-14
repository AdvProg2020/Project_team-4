package View;

<<<<<<< HEAD
import Control.Controller;
import Model.Product;
import View.Menu.*;
=======
import Controll.Controller;
import Controll.Controller.*;
import Model.Product;
import View.Menu.Menu;
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

<<<<<<< HEAD
import static Model.Category.getCategoryByName;
import static View.CommandsSource.findEnum;
import static View.Menu.ProductMenu.giveProductId;
import static View.Outputs.*;
=======
import static View.Commands.findEnum;
import static View.Outputs.*;
import static ProductMenu.giveProductId;
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
import static java.lang.Double.parseDouble;


public class Manager {

<<<<<<< HEAD
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




=======

    //--------> fields
    public static Scanner scanner;
    public static Commands commands;
    public static CommandProcessor commandProcessor = new CommandProcessor();
    public static ArrayList<String> validCommands = new ArrayList<>();

    // -------> menus
    private static Menu createLoginMenu = new CreateLoginMenu();
    private static Menu managerMenu = new ManagerMenu();
    private static Menu sellerMenu = new SellerMenu();
    private static Menu customerMenu = new CustomerMenu();
    private static Menu productMenu = new ProductMenu();
    private static Menu offMenu = new OffMenu();
    private static Menu mainMenu = new MainMenu();


    /**************************general functions*************************************/

    public static boolean isValidCommand(String command) {
        if(findEnum(commands.getAllRegex(), command).equals(null))
            return false;
        else
            return true;
    }
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb

    public static boolean isAnyUserLogin(){
        return Controller.getOurController().requestIsAnyUserLogin();
    }
<<<<<<< HEAD

=======
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
    public static String getType(){
        return Controller.getOurController().requestLoginedUser();
    }

<<<<<<< HEAD



    //----> ProductMenu





    //----> OffsMenu

=======
    /*****************************Create/Login****************************************/
    public static void createAccount(String type, String username, String password) {
        printCreateAccountResult(Controller.getOurController().requestCreateAccount(type, username, password));
    }

    public static void login(String username, String password) {
        printLoginResult(Controller.getOurController().requestLogin(username, password));
    }

    public static String requestPassword() {
        System.out.println("Enter your password:");
        return CommandProcessor.scanner.nextLine();
    }

    /***************************************OffsMenu***************************************/
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
    public static void offsList(){
        printOffsListResult(Controller.getOurController().requestOffsList());
    }

<<<<<<< HEAD
    public static void showProduct(String productId, Menu previousMenu, String input){
        giveProductId(productId);
        productMenu.execute(previousMenu, input);
=======
    public static void showProduct(String productId){
        giveProductId(productId);

>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
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

<<<<<<< HEAD
=======
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

>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
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

<<<<<<< HEAD


    // ----> checked methods
    public static void digest(String productId){
        printDigestResult(Controller.getOurController().requestDigest(productId));
    }

    public void execute() {
        mainMenu.execute(null, null);
=======
    public void run() {
        mainMenu.run(null, null);
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
    }

    public static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

<<<<<<< HEAD
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
=======

    /*****************RelatedControllerFunctions*******************/

>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb

}
