package View.Menu;

//import View.Manager;

import Control.Controller;
import Model.*;
import java.util.regex.Matcher;

import static Control.Controller.editField;


public class ManagerMenu extends Menu {

    private static final Menu managerMenu = new ManagerMenu();


    private ManagerMenu() {
        options.add("view personal info ");
        options.add("manage users #");
        options.add("manage all products #");
        options.add("create discount code #");
        options.add("view discount codes #");
        options.add("manage requests #");
        options.add("manage categories #");
        options.add("help");
        options.add("back");
    }

    public static Menu getManagerMenu() {
        return managerMenu;
    }

    private Menu getManageUsersMenu() {
        return new Menu() {
            @Override
            public void execute() {
                String input;
                Matcher matcher;
                do {
                    show();
                    System.out.println("Enter Number 1 for show a user 2 for delete a user 3 for making a manager profile and end too go back:");
                    if(!isThisRegexMatch("(\\d)", input = scanner.nextLine())){
                        continue;
                    }
                    switch (input.trim()) {
                        case "1" :
                            matcher = getField("please write in this format: view [username]", "view\\s(\\S+)");
                            Controller.getOurController().controllerShowUser(matcher.group(1));
                            break;
                        case "2" :
                            matcher = getField("Please write in this format: delete [username]", "delete\\s+(\\S+)");
                            Controller.getOurController().controllerDeleteAnUser(matcher.group(1));
                            break;
                        case "3" :
//                            System.out.println("Please enter a userName:");
//                            input = scanner.nextLine();
//                            System.out.println("Enter firstName: lastName: email: phoneNumber passWord:");
//                            input = scanner.nextLine();
//                            String[] splitInput = input.split("\\s");
//
//                            Outputs.printCreateAccountResult(Controller.getOurController().controllerCreateNewManagerAccountFromManager(splitInput[0], splitInput[1]));
                            break;
                        default:
//                            System.out.println("Enter a valid command please.");
                    }
                } while (!input.equalsIgnoreCase("end"));
            }
        };
    }
//
//    private static void creatDiscountCode() {
//        String input = "";
//        ArrayList<Customer> usersToContain = new ArrayList<>();
//        System.out.println("Enter barcode:\nstartingTime:\nendingTime:\noffAmount:\nusageTimes:\nusersToContain");
//        Matcher barcode = CommandsSource.getField("Please enter a valid barcode", "(\\S+)");
//        Matcher startingTime = CommandsSource.getField("Please enter a valid starTime", "(\\d\\d):(\\d\\d):(\\d\\d)");
//        Matcher endingTime = CommandsSource.getField("Please enter a valid endingTime", "(\\d\\d):(\\d\\d):(\\d\\d)");
//        double offAmount = Double.parseDouble(CommandsSource.getField("Please enter a valid offAmount", "(\\d+)"));
//        int usageTimes = Integer.parseInt(CommandsSource.getField("Please enter a valid usageTime", "(\\d+)"));
//        String containingCustomers = scanner.nextLine().trim();
//        Controller.getOurController().controllerCreateOffCode(barcode, startingTime, endingTime, offAmount, usageTimes, containingCustomers);
//    }
//
//    private static Menu getDiscountCodeMenu() {
//        return new Menu() {
//            @Override
//            protected void showCommands() {
//
//            }
//
//            @Override
//            public void execute() {
//                String input = "";
//                System.out.println(Controller.getOurController().getAllDiscounts());
//                while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
//                    String[] splitInput = input.split("\\s");
//                    switch (findEnum(commands.getAllRegex(), input)) {
//                        case "VIEW_DISCOUNT_CODE" :
//                            System.out.println(Controller.getOurController().getDiscount(splitInput[3]));
//                            break;
//                        case "EDIT_DISCOUNT_CODE" :
//                            Controller.getOurController().removeDiscount(splitInput[3]);
//                            creatDiscountCode();
//                        case "REMOVE_DISCOUNT_CODE" :
//                            Controller.getOurController().removeDiscount(splitInput[3]);
//                            break;
//                    }
//                }
//            }
//        };
////        System.out.println(Controller.getOurController().showAllDiscountCodes());
//    }
//
//    private static Menu getManageRequestMenu() {
//        return new Menu() {
//            @Override
//            protected void showCommands() {
//
//            }
//
//            @Override
//            public void execute() {
//                String input = "";
//                System.out.println(Controller.getOurController().showAllRequests());
//                Request request = null;
//                while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
//                    String[] splitInput = input.split("\\s");
//                    switch (findEnum(commands.getAllRegex(), input)) {
//                        case "DETAILS_REQUEST" :
//                            request = Request.getRequestByName(splitInput[1]);
//                            System.out.println(request);
//                            break;
//                        case "ACCEPT_REQUEST" :
//                            Controller.getOurController().acceptRequest(request);
//                            break;
//                        case "DECLINE_REQUEST" :
//                            Controller.getOurController().declineRequest(request);
//                            break;
//                    }
//                }
//            }
//        };
//    }
//
//    private static Menu getManageCategoriesMenu() {
//        return new Menu() {
//            @Override
//            protected void showCommands() {
//
//            }
//
//            @Override
//            public void execute() {
//                String input = "";
//                System.out.println(Category.getAllCategories());
//                Request request = null;
//                while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
//                    String[] splitInput = input.split("\\s");
//                    switch (findEnum(commands.getAllRegex(), input)) {
//                        case "EDIT_CATEGORY" :
//                            request = Request.getRequestByName(splitInput[1]);
//                            System.out.println(request);
//                            break;
//                        case "ADD_CATEGORY" :
//                        case "REMOVE_CATEGORY" :
//                            addCategory();
//                            break;
//                    }
//                }
//            }
//        };
//    }
//
//    private static void addCategory() {
//        String input = "";
//        ArrayList<Customer> usersToContain = new ArrayList<>();
//        System.out.println("Enter name:\nsubCategorie:\ntags:\nproductsList:");
//        String name = CommandsSource.getField("Please enter a valid barcode", "(\\S+)");;
//        String subCategories = scanner.nextLine().trim();
//        String tags = scanner.nextLine().trim();
//        String productsList = scanner.nextLine().trim();
//        Controller.getOurController().createCategory(name, subCategories, tags, productsList);
//    }


    private static Menu viewAndEditPersonalInfo() {
        return new Menu() {
            private void personalInfo() {
                Matcher matcher = getField("Enter in this format: edit [field]", "edit\\s(\\S+)");
                switch (editField(matcher.group(1))) {
                    case 1:
                        SaveAndLoad.getSaveAndLoad().writeJSONAccount(Controller.getLoggedInAccount(), Controller.getLoggedInAccount().getClass().toString());
                        System.out.println("Changed well");
                        break;
                    case 2:
                        System.out.println("Sth went wrong in changing");

                }
            }
            @Override
            protected void execute() {
                System.out.println(Controller.getLoggedInAccount());
                String input;
                do {
                    System.out.println("Enter 1 for edit a field and 2 for back:");
                    if(!isThisRegexMatch("(\\d)", input = scanner.nextLine())){
                        continue;
                    }
                    switch (input) {
                        case "1":
                            System.out.println("dsifjfd");
                            personalInfo();
                            break;
                        case "2":
                            return;
                    }
                }while (!input.equalsIgnoreCase("end"));
            }
        };
    }

    @Override
    public void execute() {
        String input;
        do {
            show();
            System.out.println("Enter Number :");
            if(!isThisRegexMatch("(\\d)", input = scanner.nextLine())){
                continue;
            }
            switch (input.trim()) {
                case "1":
                    viewAndEditPersonalInfo().execute();
                    break;

                case "2":
                    getManageUsersMenu().execute();
                    break;
//                case "3":
//                    while (!(input = scanner.nextLine()).equalsIgnoreCase("back")) {
//                        System.out.println("remove a product:");
//                        //input = scanner.nextLine();
//                        if (CommandsSource.isThisRegexMatch("remove\\s(\\w+)", input)) {
//                            String[] splitInput = input.split("\\s");
//                            Controller.getOurController().controllerRemoveProduct(splitInput[1]);
//                        }
//                    }
//                    break;
//                case "4":
//                    creatDiscountCode();
//                    break;
//                case "5":
//                    nextMenu = getDiscountCodeMenu();
//                    getDiscountCodeMenu.showCommands();
//                    nextMenu.execute();
//                    break;
//                case "6":
//                    nextMenu = getManageRequestMenu();
//                    nextMenu.showCommands();
//                    nextMenu.execute();
//                    break;
//                case "7":
//                    nextMenu = getManageCategoriesMenu();
//                    nextMenu.showCommands();
//                    nextMenu.execute();
                case "8":
                    show();
                    break;
                case "9":
                    return;
            }
        }while (!input.equalsIgnoreCase("end"));
    }

}

