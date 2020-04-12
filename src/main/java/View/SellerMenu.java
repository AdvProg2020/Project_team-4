package View;

import java.util.ArrayList;
import java.util.regex.Pattern;
import static View.Commands.getEnumNameByRegex;
import static View.Manager.*;


public class SellerMenu extends Menu {

    private ArrayList<String> regexOfThisMenu = new ArrayList<>();
    private Commands commands;

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
    }

    private String getEnumName(ArrayList<String> allRegex, String input) {
        for (String regex : allRegex) {
            if (getMatcher(input, regex).find()) {
                return getEnumNameByRegex(regex);
            }
        }
        return null;
    }

    public void run(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = Manager.scanner.nextLine()).equalsIgnoreCase("end")) {
            switch (getEnumName(commands.getAllRegex(), input)) {
                case "VIEW_PERSONAL_INFO":
                    getPersonalInfo();
                    if ("EDIT_PERSONAL_INFO".equals(getEnumName(commands.getAllRegex(), input))) {

                    }
                case "VIEW_COMPANY_INFORMATION":
                    getCompanyInfo();
                    break;
                case "VIEW_SALES_HISTORY":
                    getSalesHistory();
                    break;
                case "MANAGE_PRODUCTS":
                    manageProducts();
                    switch (getEnumName(regexOfThisMenu, input)) {
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
                case "HELP":
                    help();
                    break;
                case "BACK":
                    if (previousMenu == null) {
                        System.err.println("This your first menu.");
                    } else {
                        previousMenu.run(this, input);
                    }
                    break;
                default:
                    if (Manager.isValidCommand(input)) {
                        System.err.println("You must login first");
                    } else {
                        System.err.println("invalid command");
                    }
                    break;
            }
        }


    }

}