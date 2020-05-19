package View.Menu;


import Control.Controller;
import Model.*;
import View.Outputs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;

import static Model.Account.getAccountWithName;
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

    public static Menu managerMenu() {
        return managerMenu;
    }

    private static Menu viewAndEditPersonalInfo() {
        return new Menu() {
            private void personalInfo() {
                Matcher matcher = getField("Enter in this format: edit [field] for back write back\n" +
                        "warning you can't change username!", "edit\\s(firstName|lastName|credit|phoneNumber|email|password)");
                if(matcher == null){
                    return;
                }
                Controller.getOurController().editField(matcher.group(1));
                System.out.println("Changed well");
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

    private Menu manageAllProducts(){
        return new Menu() {
            @Override
            protected void execute() {
                options.add("remove a product");
                options.add("back");
                String input;
                Matcher matcher;
                do {
                    show();
                    System.out.println("Enter number:");
                    printAllProduct();
                    if (!isThisRegexMatch("(\\d)", input = scanner.nextLine())) {
                        continue;
                    }
                    switch(input.trim()){
                        case "1":
                            matcher = getField("please write in this format: remove [product name]", "remove\\s(\\S+)");
                            if(matcher == null){
                                return;
                            }
                            printRemoveProductResult(Controller.getOurController().controllerRemoveProduct(matcher.group(1)));
                            break;
                        case "2":
                            return;
                    }
                } while(true);
            }

            private void printAllProduct() {
                for (Product allProduct : Product.getAllProducts()) {
                    System.out.println(allProduct);
                }
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
                    showA‌llUsers();
                    System.out.println("Enter Number 1 for show a user 2 for delete a user 3 for making a manager profile and 4 to go back:");
                    if(!isThisRegexMatch("(\\d)", input = scanner.nextLine())){
                        continue;
                    }
                    switch (input.trim()) {
                        case "1" :
                            matcher = getField("please write in this format: view [username] write back for back", "view\\s(\\S+)");
                            if(matcher == null){
                                continue;
                            }
                            System.out.println(Account.getAccountWithName(matcher.group(1)));
                            break;
                        case "2" :
                            matcher = getField("Please write in this format: delete [username] write back for back", "delete\\s+(\\S+)");
                            if(matcher == null){
                                continue;
                            }
                            Outputs.printDeletingAccountResult(Controller.getOurController().controllerDeleteAnUser(matcher.group(1)));
                            break;
                        case "3" :
                            matcher = Menu.getField("Please enter a userName and pass in this format: [userName] [password]  write back for back", "(\\S+)\\s(\\S+)");
                            if(matcher == null){
                                continue;
                            }
                            Outputs.printCreateAccountResult(Controller.getOurController().controllerCreateNewManagerAccountFromManager(matcher.group(1), matcher.group(2)));
                            break;
                        case "4":
                            return;
                     }
                } while (true);
            }
        };
    }

    private void showA‌llUsers() {
        System.out.println("Customers :");
        System.out.println(Arrays.toString(Controller.getOurController().getusers(Customer.class)));
        System.out.println("Sellers :");
        System.out.println(Arrays.toString(Controller.getOurController().getusers(Seller.class)));
        System.out.println("Managers :");
        System.out.println(Arrays.toString(Controller.getOurController().getusers(Manager.class)));
    }

    private static void creatDiscountCode() {
        String input;
        ArrayList<String> containingCustomers = new ArrayList<>();
        Matcher matcher;
        String barcode;
        Matcher expireDate;
        Matcher startDate;
        String maximumOffAmount;
        String percentOfOff;
        String usageTime;
        do{
            System.out.println("Enter requested field or type \"back\" to back:");
            matcher = getField("Enter barcode:", "(\\w+)");
            if (matcher == null) {
                return;
            }
            barcode = matcher.group(1);
            matcher = getField("Please enter a valid start date\nlike: 0000, 00, 00, 00, 00", "(\\d\\d\\d\\d), (\\d\\d), (\\d\\d), (\\d\\d), (\\d\\d)");
            if (matcher == null) {
                return;
            }
            startDate = matcher;
            matcher = getField("Please enter a valid expire date\nlike: 0000, 00, 00, 00, 00", "(\\d\\d\\d\\d), (\\d\\d), (\\d\\d), (\\d\\d), (\\d\\d)");
            if (matcher == null) {
                return;
            }
            expireDate = matcher;
            matcher = getField("Please enter a valid maximum off amount and percent of off\nlike: 10000, 20", "(\\d+), (\\d+)");
            if (matcher == null) {
                return;
            }
            maximumOffAmount = matcher.group(1);
            percentOfOff = matcher.group(2);
            matcher = getField("Please enter a valid usage time", "(\\d+)");
            if (matcher == null) {
                return;
            }
            usageTime = matcher.group(1);
            System.out.println("Enter username of account you want contain off code like \"ali\" or \"end\" to end");
            do {
                showCustomers();
                input = scanner.nextLine();
                if(input.equals("end")){
                    break;
                }
                Account account = getAccountWithName(input.trim());
                if(account == null){
                    System.out.println("this username doesn't exist!");
                    continue;
                }
                if(!account.getClass().equals(Customer.class)){
                    System.out.println("please enter customer for using codedoff");
                    continue;
                }
                Customer customer = (Customer) account;
                if(containingCustomers.contains(account)){
                    System.out.println("this name was added one time");
                    continue;
                }
                containingCustomers.add(input.trim());
                System.out.println("customer added successfully!");
            }while(true);
            Outputs.printCreateCodedOffResult(Controller.getOurController().controllerCreateOffCode(barcode, startDate, expireDate, maximumOffAmount, percentOfOff, usageTime, containingCustomers));
        }while(true);
    }

    private static void showCustomers() {
        System.out.println("Customers :");
        System.out.println(Arrays.toString(Controller.getOurController().getusers(Customer.class)));
    }

    private static Menu discountCodeMenu() {
        return new Menu() {
            @Override
            public void execute() {
                String input;
                Matcher matcher;
                do {
                    for (CodedOff showAllDiscountCode : Controller.getOurController().showAllDiscountCodes()) {
                        System.out.println(showAllDiscountCode);
                    }
                    System.out.println("Enter Number 1 for show a discount code 2 for edit a discount code 3 for delete a discount code and 4 to go back:");
                    if (!isThisRegexMatch("(\\d)", input = scanner.nextLine())) {
                        continue;
                    }
                    switch (input.trim()) {
                        case "1":
                            matcher = Menu.getField("please enter in this format: view [code]", "view\\s(\\S+)");
                            if(matcher == null){
                                return;
                            }
                            System.out.println(Controller.getOurController().getDiscount(matcher.group(1)));
                            break;
                        case "2" :
                            matcher = Menu.getField("please enter in this format: edit [code]", "edit\\s(\\S+)");
                            if(matcher == null){
                                return;
                            }
                            Controller.getOurController().removeDiscount(matcher.group(1));
                            creatDiscountCode();
                        case "3" :
                            matcher = Menu.getField("please enter in this format: delete [code]", "delete\\s(\\S+)");
                            if(matcher == null) {
                                return;
                            }
                            Controller.getOurController().removeDiscount(matcher.group(1));
                            break;
                        case "4":
                            return;
                    }
                }while (true);
            }
        };
    }

    private static Menu manageRequestMenu() {
        return new Menu() {
            @Override
            public void execute() {
                String input;
                Matcher matcher;
                Request request = null;
                options.add("DETAILS_REQUEST");
                options.add("ACCEPT_REQUEST");
                options.add("DECLINE_REQUEST");
                options.add("end");
                do {
                    for (SaveAble showAllRequest : Controller.getOurController().showAllRequests()) {
                        System.out.println(showAllRequest);
                    }
                    show();
                    System.out.println("requestid = productbarcod or seller name!");
                    if (!isThisRegexMatch("(\\d)", input = scanner.nextLine())) {
                        continue;
                    }
                    switch (input.trim()) {
                        case "1":
                            matcher = getField("like this: [requestId]", "(\\S+)");
                            if(matcher == null){
                                continue;
                            }
                            request = Manager.getRequestByName(matcher.group(1));
                            System.out.println(request);
                            break;
                        case "2":
                            matcher = getField("like this: [requestId]", "(\\S+)");
                            if(matcher == null){
                                continue;
                            }
                            Controller.getOurController().acceptRequest(matcher.group(1));
                            break;
                        case "3":
                            matcher = getField("like this: [requestId]", "(\\S+)");
                            if(matcher == null){
                                continue;
                            }
                            Controller.getOurController().declineRequest(matcher.group(1));
                            break;
                        case "4":
                            return;
                    }
                } while (true);
            }
        };
    }

    private static Menu getManageCategoriesMenu() {
        return new Menu() {
            @Override
            public void execute() {
                options.add("EDIT_CATEGORY");
                options.add("ADD_CATEGORY");
                options.add("REMOVE_CATEGORY");
                options.add("back");
                String input = "";
                Request request = null;
                do {
                    for (Category allCategory : Category.getAllCategories()) {
                        System.out.println(allCategory);
                    }
                    show();
                    if (!isThisRegexMatch("(\\d)", input = scanner.nextLine())) {
                        continue;
                    }
                    switch (input.trim()) {
                        case "1":
                            addCategory();
                            break;
                        case "2":
                            addCategory();
                            break;
                        case "3":
                            Matcher matcher = getField("Please enter a valid name: [name]", "(\\S+)");
                            if(matcher == null){
                                return;
                            }
                            String name = matcher.group(1);
                            if(Category.getCategoryByName(name) == null){
                                System.out.println("category not found");
                                continue;
                            }
                            Controller.getOurController().removeCategory(name);
                            break;
                        case "4":
                            return;
                    }
                }while (true);
            }
        };
    }

    private static void addCategory() {
        String input = "";
        ArrayList<String> subCategories = new ArrayList<>();
        ArrayList<String> tags = new ArrayList<>();
        ArrayList<String> productsList = new ArrayList<>();
        String nameToAdd;
        Matcher matcher= getField("Please enter a valid name", "(\\S+)");
        if(matcher == null){
            return;
        }
        String name = matcher.group(1);
        while (true) {
            for (Category allCategory : Category.getAllCategories()) {
                System.out.println(allCategory);
            }
            Matcher matcher1 = getField("enter subCategories like this: categoryNo1 and end to end", "(\\S+)");
            if(matcher1 == null){
                return;
            }
            nameToAdd = matcher1.group(1);
            if (nameToAdd.equalsIgnoreCase("end")) {
                break;
            }
            if(Category.getCategoryByName(nameToAdd) == null){
                System.out.println("category not found");
                continue;
            }
            subCategories.add(nameToAdd);
        }
        while (true) {
            Matcher matcher1 = getField("enter tag like this: tag and end to end", "(\\S+)");
            if(matcher1 == null){
                return;
            }
            nameToAdd = matcher1.group(1);
            if (nameToAdd.equalsIgnoreCase("end")) {
                break;
            }
            tags.add(nameToAdd);
        }
        while (true) {
            for (Product allProduct : Product.getAllProducts()) {
                System.out.println(allProduct.getNameOfProductNotBarcode() + " --->" + allProduct.getName());
            }
            Matcher matcher1 = getField("enter product like this: product and end to end", "(\\S+)");
            if(matcher1 == null){
                return;
            }
            nameToAdd = matcher1    .group(1);
            if (nameToAdd.equalsIgnoreCase("end")) {
                break;
            }
            if(Product.getAllProducts().contains(Product.getProductWithBarcode(nameToAdd))){
                System.out.println("Product not found");
                continue;
            }
            productsList.add(nameToAdd);
        }
        Controller.getOurController().createCategory(name, subCategories, tags, productsList);
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
                case "5":
                    discountCodeMenu().execute();
                    break;
                case "6":
                    manageRequestMenu().execute();
                    break;
                case "7":
                    getManageCategoriesMenu().execute();
                case "8":
                    show();
                    break;
                case "9":
                    return;
            }
        }while (!input.equalsIgnoreCase("end"));
    }

}

