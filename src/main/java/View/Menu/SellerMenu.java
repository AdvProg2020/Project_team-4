package View.Menu;

import View.Manager;

<<<<<<< HEAD
import static View.CommandsSource.*;
=======
import static View.Commands.*;
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
import static View.Manager.*;


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



<<<<<<< HEAD
    public void execute(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = Manager.scanner.nextLine()).equalsIgnoreCase("end")) {
            switch (findEnum(commandsSource.getAllRegex(), input)) {
                case "VIEW_PERSONAL_INFO":
                    getPersonalInfo();
                    if ("EDIT_PERSONAL_INFO".equals(findEnum(commandsSource.getAllRegex(), input))) {
=======
    public void run(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = Manager.scanner.nextLine()).equalsIgnoreCase("end")) {
            switch (findEnum(commands.getAllRegex(), input)) {
                case "VIEW_PERSONAL_INFO":
                    getPersonalInfo();
                    if ("EDIT_PERSONAL_INFO".equals(findEnum(commands.getAllRegex(), input))) {
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb

                    }
                case "VIEW_COMPANY_INFORMATION":
                    getCompanyInfo();
                    break;
                case "VIEW_SALES_HISTORY":
                    getSalesHistory();
                    break;
                case "MANAGE_PRODUCTS":
                    manageProducts();
<<<<<<< HEAD
                    switch (findEnum(commandsSource.getAllRegex(), input)) {
=======
                    switch (findEnum(commands.getAllRegex(), input)) {
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
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
<<<<<<< HEAD
                    addProduct();
=======
                    getProductInfoForAdd();
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
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
                    showCommands();
                    break;
                case "BACK":
                    if (previousMenu == null) {
                        System.err.println("This your first menu.");
                    } else {
<<<<<<< HEAD
                        previousMenu.execute(this, input);
=======
                        previousMenu.run(this, input);
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
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