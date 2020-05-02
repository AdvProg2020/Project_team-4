package View.Menu;


import Control.Controller;
import Model.*;
import View.Outputs;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;

import static Model.Customer.getCustomerByName;
import static View.Outputs.printRemoveProductResult;


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

    private Menu manageAllProducts(){
        options.add("remove a product");
        options.add("back");
        return new Menu() {
            @Override
            protected void execute() {
                String input;
                Matcher matcher;
                do {
                    show();
                    System.out.println("Enter number:");
                    if (!isThisRegexMatch("(\\d)", input = scanner.nextLine())) {
                        continue;
                    }
                    switch(input.trim()){
                        case "remove a product":
                            matcher = getField("please write in this format: remove [product name]", "view\\s(\\S+)");
                            //in this may a product has two section name
                            //should edit regex
                            printRemoveProductResult(Controller.getOurController().controllerRemoveProduct(matcher.group(1)));
                            break;
                        case "back":
                            return;
                    }
                } while(true);
            }
        };
    }

    private Menu manageUsersMenu() {
        return new Menu() {
            @Override
            public void execute() {
                String input;
                Matcher matcher;
                do {
                    show();
                    System.out.println("Enter Number 1 for show a user 2 for delete a user 3 for making a manager profile and end to go back:");
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
                            Matcher matcher1 = Menu.getField("Please enter a userName and pass in this format: [userName] [password]", "(\\S+)\\s(\\S+)");
                            Outputs.printCreateAccountResult(Controller.getOurController().controllerCreateNewManagerAccountFromManager(matcher1.group(1), matcher1.group(2)));
                            break;
                        default:
//                            System.out.println("Enter a valid command please.");
                    }
                } while (true);
            }
        };
    }

    private static void creatDiscountCode() {
        String input = "";
        ArrayList<Customer> containingCustomers = new ArrayList<>();
        Matcher matcher;
        String barcode = "";
        String expireDate = "";
        String startDate = "";
        String maximumOffAmount = "";
        String percentOfOff = "";
        String usageTime = "";
        do{

            System.out.println("Enter requested field or type \"back\" to back:");
            matcher = getField("Enter barcode:", "\\w+");
            if (matcher != null) {
                barcode = matcher.toString();
            }
            matcher = getField("Please enter a valid start date\nlike: 1399:1:7", "(\\d\\d\\d\\d):([1-12]):([1-31])");
            if (matcher != null) {
                startDate = matcher.toString();
            }
            matcher = getField("Please enter a valid expire date\nlike: 1400:6:14", "(\\d\\d\\d\\d):([1-12]):([1-31])");
            if (matcher != null) {
                expireDate = matcher.toString();
            }
            matcher = getField("Please enter a valid maximum off amount and percent of off\nlike: 10000 , 20%", "(\\d+) , ([1-100]%)");
            if (matcher != null) {
                maximumOffAmount = matcher.group(1);
                percentOfOff = matcher.group(2);
            }
            matcher = getField("Please enter a valid usage time", "(\\d+)");
            if (matcher != null) {
                usageTime = matcher.group(1);
            }
            System.out.println("Enter username of account do you want contain off code like \"ali\" or \"end\" to end");
            while(!((input = scanner.nextLine()).equalsIgnoreCase("end"))){
                containingCustomers.add(getCustomerByName(input.trim()));
            }
        }while(!(input = scanner.nextLine()).equalsIgnoreCase("back"));
        Controller.getOurController().controllerCreateOffCode(barcode, startDate, expireDate, maximumOffAmount, percentOfOff, usageTime, containingCustomers);
    }
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
                Matcher matcher = getField("Enter in this format: edit [field] for back write back", "edit\\s(\\S+)");
                if(matcher == null){
                    return;
                }
                switch (Controller.getOurController().editField(matcher.group(1))) {
                    case 1:
                        SaveAndLoad.getSaveAndLoad().writeJSONAccount(Controller.getOurController().getLoggedInAccount(), Controller.getOurController().getLoggedInAccount().getClass().toString());
                        System.out.println("Changed well");
                        break;
                    case 2:
                        System.out.println("Sth went wrong in changing");

                }
            }
            @Override
            protected void execute() {
                System.out.println(Controller.getOurController().getLoggedInAccount());
                String input;
                do {
                    System.out.println("Enter 1 for edit a field and 2 for back:");
                    if(!isThisRegexMatch("(\\d)", input = scanner.nextLine())){
                        continue;
                    }
                    switch (input) {
                        case "1":
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
                    manageUsersMenu().execute();
                    break;
                case "3":
                    manageAllProducts().execute();
                    break;
                case "4":
                    creatDiscountCode();
                    break;
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

