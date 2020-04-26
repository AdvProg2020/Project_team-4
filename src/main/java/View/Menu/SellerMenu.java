package View.Menu;

import Control.Controller;
import Model.Product;

import java.util.ArrayList;

import static View.CommandsSource.findEnum;
import static View.Outputs.*;
import static java.lang.Double.parseDouble;

public class SellerMenu extends Menu {

    public SellerMenu() {
        options.add("view company information");
        options.add("view sales history");
        options.add("manage products");
        options.add("add product");
        options.add("remove product [productId]");
        options.add("show categories");
        options.add("view offs");
        options.add("view balance");
        options.add("help");
        options.add("back");
        options.add("logout");
    }

    private int getInfoForAddProduct() {
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
        return Controller.getOurController().requestAddProduct(name, company, cost, category, description);
    }

    private static void getCompanyInfo() {
        ArrayList<String> companyInfos = new ArrayList<>();
        companyInfos.addAll(Controller.getOurController().requestCompanyInfo());
        for (String companyInfo : companyInfos) {
            System.out.println(companyInfo);
        }
    }

    static void personalInfo() {
        String input = scanner.nextLine();
        String[] splitInput =
        System.out.println(Controller.getLoggedInAccount());
        switch (Controller.getOurController().editField(splitInput[1], splitInput[2])) {
            case true:

            case false:

        }
    }

    private static void getSalesHistory() {
        ArrayList<String> salesHistory = new ArrayList<>();
        salesHistory.addAll(Controller.getOurController().requestSalesHistoryInfo());
        for (String saleHistory : salesHistory) {
            System.out.println(saleHistory);
        }
    }

    private static void manageProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.addAll(Controller.getOurController().requestListOfProducts());
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void getProductInfo() {
        System.out.println(Controller.getOurController().requestProductInfo());
    }

    private static void productBuyers() {
        printProductBuyersResult(Controller.getOurController().requestProductBuyers());
    }

    private static void editPersonalInfo(){

    }

    private void viewPersonalInfo(String input) {
        printPersonalInfoResult(Controller.getOurController().requestLoggedInUser());
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            String[] splitInput = input.split("\\s");
            switch (findEnum(commands.getAllRegex(), input)) {
                case "EDIT_PERSONAL_INFO":
                    editPersonalInfo();
                default:
                    super.execute(input);
            }
        }
    }

    private static void getProductInfoForAdd(){

    }

    private static void removeProduct(){

    }

    private static void showCategories(){

    }

    private static void viewOffs(){

    }

    private static void viewBalance(){

    }

    public void execute(String input) {
        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            String[] splitInput = input.split("\\s");
            switch (findEnum(commands.getAllRegex(), input)) {
                case "VIEW_PERSONAL_INFO":
                    viewPersonalInfo(input);
                case "VIEW_COMPANY_INFORMATION":
                    getCompanyInfo();
                    break;
                case "VIEW_SALES_HISTORY":
                    getSalesHistory();
                    break;
                case "MANAGE_PRODUCTS":
                    manageProducts();
                    switch (findEnum(commands.getAllRegex(), input)) {
                        case "VIEW_PRODUCT":
                            getProductInfo();
                            break;
                        case "VIEW_BUYERS":
                            productBuyers();
                            break;
                        case "EDIT_PRODUCT":

                            break;
                    }
                    break;

                case "ADD_PRODUCT":
                    getProductInfoForAdd();
                    break;
                case "REMOVE_PRODUCT":
                    removeProduct();
                    break;
                case "SHOW_CATEGORIES":
                    showCategories();
                    break;
                case "VIEW_OFFS":
                    viewOffs();
                    break;
                case "VIEW_BALANCE":
                    viewBalance();
                    break;
                default:
                    super.execute(input);
            }
        }


    }

}