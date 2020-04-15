package View.Menu;

//import View.Manager;

import Control.Controller;
import View.CommandsSource;

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
                            Controller.getOurController.showAnUser(splitInput[1]);
                            break;
                        case "REMOVE_USER" :
                            Controller.getOurController.deleteAnUser(splitInput[2]);
                            break;
                        case "CREATE_MANAGER_PROFILE" :
                            System.out.println("Please enter a userName:");
                            input = scanner.nextLine();
                            if (Controller.getOurController.checkTheUserNameBeforeRegister(input)) {
                                System.out.println("Enter firstName: lastName: email: phoneNumber passWord:");
                                input = scanner.nextLine();
                                splitInput = input.split("\\s");
                                if (Controller.getOurController.createAnewManagerAccountFromManagerPortal(splitInput[0], splitInput[1], splitInput[2], splitInput[3], splitInput[4]);
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

    private void creatDiscountCode() {
        System.out.println("Enter barcode: startingTime: endingTime: offAmount: usageTimes: usersToContain");
        String input = scanner.nextLine();
    }

    public void execute(Menu previousMenu, String input) {
        Menu nextMenu = null;
        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            String[] splitInput = input.split("\\s");
            switch (findEnum(commands.getAllRegex(), input)) {
                case "edit [field] to [new]":
                    editField(input.split("\\s"));
                case "MANAGE_USERS":
                    nextMenu = getManageUsersMenu();
                    nextMenu.showCommands();
                    nextMenu.execute("");
                case "MANAGE_ALL_PRODUCT":
                    while (!(input = scanner.nextLine()).equalsIgnoreCase("back")) {
                        System.out.println("remove a product:");
                        //input = scanner.nextLine();
                        if (CommandsSource.isThisRegexMatch("remove\\s(\\w+)", input)) {
                            splitInput = input.split("\\s");
                            Controller.getOurController.removeProduct(splitInput[1]);
                        }
                    }
                case "CREATE_DISCOUNT_CODE":

                case "VIEW_DISCOUNT_CODE":

                case "MANAGE_REQUESTS":

                case "MANAGE_CATEGORIES":

                default:
                    super.execute(input);

            }
        }
    }

}

