package View.Menu;

import Control.Controller;
import Model.Account;

import java.util.ArrayList;

import static View.CommandsSource.findEnum;
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

    public void execute(String input) {
        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            String[] splitInput = input.split("\\s");
            switch (findEnum(commands.getAllRegex(), input)) {
                case "VIEW_PERSONAL_INFO":
                    getPersonalInfo();
                    if ("EDIT_PERSONAL_INFO".equals(findEnum(commands.getAllRegex(), input))) {

                    }
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
                            getProductBuyers();
                            break;
                        case "EDIT_PRODUCT":
                            showEditableField();
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