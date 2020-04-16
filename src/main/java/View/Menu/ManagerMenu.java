package View.Menu;

//import View.Manager;

import Control.Controller;
import Model.Category;
import Model.Customer;
import Model.Request;
import View.CommandsSource;

import java.util.ArrayList;

import static View.CommandsSource.findEnum;
//import static View.Manager.*;
import static View.Menu.SellerMenu.editField;

public class ManagerMenu extends Menu {


    public ManagerMenu() {
        options.add("view personal info");
        options.add("edit [field] to [new]");
        options.add("manage users");
        options.add("manage all products");
        options.add("create discount code");
        options.add("view discount codes");
        options.add("manage requests");
        options.add("manage categories");
        options.add("help");
        options.add("back");
    }

    private Menu getManageUsersMenu() {
        return new Menu() {
            @Override
            protected void showCommands() {
                System.out.println("You can view or delete an account or create a manager profile.");
            }

            @Override
            public void execute(String input) {
                while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
                    String[] splitInput = input.split("\\s");
                    switch (findEnum(commands.getAllRegex(), input)) {
                        case "VIEW_USER" :
                            Controller.getOurController().showAnUser(splitInput[1]);
                            break;
                        case "REMOVE_USER" :
                            Controller.getOurController().deleteAnUser(splitInput[2]);
                            break;
                        case "CREATE_MANAGER_PROFILE" :
                            System.out.println("Please enter a userName:");
                            input = scanner.nextLine();
                            if (Controller.getOurController().checkTheUserNameBeforeRegister(input)) {
                                System.out.println("Enter firstName: lastName: email: phoneNumber passWord:");
                                input = scanner.nextLine();
                                splitInput = input.split("\\s");
                                if (Controller.getOurController().createAnewManagerAccountFromManagerPortal(splitInput[0], splitInput[1], splitInput[2], splitInput[3], splitInput[4])
                               ) {
                                    System.out.println("Manager created.");
                                } else {
                                    System.out.println("Sth went wrong in creation");
                                }
                            } else {
                                System.out.println("UserName exists already.");
                            }
                            break;
                        default:
                            System.out.println("Enter a valid command please.");
                    }
                }
            }
        };
    }

    private static void creatDiscountCode() {
        String input = "";
        ArrayList<Customer> usersToContain = new ArrayList<>();
        System.out.println("Enter barcode:\nstartingTime:\nendingTime:\noffAmount:\nusageTimes:\nusersToContain");
        String barcode = CommandsSource.getField("Please enter a valid barcode", "(\\S+)");;
        String startingTime = CommandsSource.getField("Please enter a valid starTime", "(\\d\\d):(\\d\\d):(\\d\\d)");
        String endingTime = CommandsSource.getField("Please enter a valid endingTime", "(\\d\\d):(\\d\\d):(\\d\\d)");
        double offAmount = Double.parseDouble(CommandsSource.getField("Please enter a valid offAmount", "(\\d+)"));
        int usageTimes = Integer.parseInt(CommandsSource.getField("Please enter a valid usageTime", "(\\d+)"));
        Controller.getOurController().createAOffCode(barcode, startingTime, endingTime, offAmount, usageTimes);
    }

    private static Menu getDiscountCodeMenu() {
        return new Menu() {
            @Override
            protected void showCommands() {

            }

            @Override
            public void execute(String input) {
                System.out.println(Controller.getOurController().getAllDiscounts());
                while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
                    String[] splitInput = input.split("\\s");
                    switch (findEnum(commands.getAllRegex(), input)) {
                        case "VIEW_DISCOUNT_CODE" :
                            System.out.println(Controller.getOurController().getDiscount(splitInput[3]));
                            break;
                        case "EDIT_DISCOUNT_CODE" :
                            creatDiscountCode();
                        case "REMOVE_DISCOUNT_CODE" :
                            Controller.getOurController().removeDiscount(splitInput[3]);
                            break;
                    }
                }
            }
        };
        System.out.println(Controller.getOurController().showAllDiscountCodes());
    }

    private static Menu getManageRequestMenu() {
        return new Menu() {
            @Override
            protected void showCommands() {

            }

            @Override
            public void execute(String input) {
                System.out.println(Controller.getOurController().showAllRequests());
                Request request = null;
                while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
                    String[] splitInput = input.split("\\s");
                    switch (findEnum(commands.getAllRegex(), input)) {
                        case "DETAILS_REQUEST" :
                            request = Request.getRequestByName(splitInput[1]);
                            System.out.println(request);
                            break;
                        case "ACCEPT_REQUEST" :
                            Controller.getOurController().acceptRequest(request);
                            break;
                        case "DECLINE_REQUEST" :
                            Controller.getOurController().declineRequest(request);
                            break;
                    }
                }
            }
        };
    }

    private static Menu getManageCategoriesMenu() {
        return new Menu() {
            @Override
            protected void showCommands() {

            }

            @Override
            public void execute(String input) {
                System.out.println(Category.getAllCategories());
                Request request = null;
                while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
                    String[] splitInput = input.split("\\s");
                    switch (findEnum(commands.getAllRegex(), input)) {
                        case "EDIT_CATEGORY" :
                            request = Request.getRequestByName(splitInput[1]);
                            System.out.println(request);
                            break;
                        case "ADD_CATEGORY" :
                        case "REMOVE_CATEGORY" :
                            addCategory();
                            break;
                    }
                }
            }
        };
    }

    private static void addCategory() {
        String input = "";
        ArrayList<Customer> usersToContain = new ArrayList<>();
        System.out.println("Enter name:\nsubCategorie:\ntags:\nproductsList:");
        String name = CommandsSource.getField("Please enter a valid barcode", "(\\S+)");;
        String subCategories = scanner.nextLine().trim();
        String tags = scanner.nextLine().trim();
        String productsList = scanner.nextLine().trim();
        Controller.getOurController().createCategory(name, subCategories, tags, productsList);
    }

    public void execute(Menu previousMenu, String input) {
        Menu nextMenu = null;
        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            String[] splitInput = input.split("\\s");
            switch (findEnum(commands.getAllRegex(), input)) {
                case "edit [field] to [new]":
                    editField(input.split("\\s"));
                    break;
                case "MANAGE_USERS":
                    nextMenu = getManageUsersMenu();
                    nextMenu.showCommands();
                    nextMenu.execute("");
                    break;
                case "MANAGE_ALL_PRODUCT":
                    while (!(input = scanner.nextLine()).equalsIgnoreCase("back")) {
                        System.out.println("remove a product:");
                        //input = scanner.nextLine();
                        if (CommandsSource.isThisRegexMatch("remove\\s(\\w+)", input)) {
                            splitInput = input.split("\\s");
                            Controller.getOurController().removeProduct(splitInput[1]);
                        }
                    }
                    break;
                case "CREATE_DISCOUNT_CODE":
                    creatDiscountCode();
                    break;
                case "VIEW_DISCOUNT_CODE":
                    nextMenu = getDiscountCodeMenu();
                    nextMenu.showCommands();
                    nextMenu.execute("");
                    break;
                case "MANAGE_REQUESTS":
                    nextMenu = getManageRequestMenu();
                    nextMenu.showCommands();
                    nextMenu.execute("");
                    break;
                case "MANAGE_CATEGORIES":
                    nextMenu = getManageCategoriesMenu();
                    nextMenu.showCommands();
                    nextMenu.execute("");
                default:
                    super.execute(input);

            }
        }
    }

}

