package View.Menu;

import Control.Controller;

import java.util.ArrayList;

public class SellerMenu extends Menu {

    private static final Menu sellerMenu = new SellerMenu();

    public SellerMenu() {
        options.add("view company information #");
        options.add("view sales history #");
        options.add("manage products #");
        options.add("add product #");
        options.add("remove product [productId] #");
        options.add("show categories #");
        options.add("view offs #");
        options.add("view balance #");
        options.add("help");
        options.add("back");
        options.add("logout #");
    }

    private static void addProduct() {
        String name = getField("Enter a valid name", "(\\S+)").group(1);
        String companyName = getField("enter companyname: ", "(\\S+)").group(1);
        int cost = Integer.parseInt(getField("Enter cost: ", "(\\d+)").group(1));
        String nameToAdd = "";
        ArrayList<String> sellersNames = new ArrayList<>();
        while (true) {
            nameToAdd = getField("enter seller to add to sellers of this product and end to end: ", "(\\S+)").group(1);
            if (nameToAdd.equalsIgnoreCase("end")) {
                break;
            }
            sellersNames.add(nameToAdd);
        }
        int amountOfExist = Integer.parseInt(getField("enter how many of this product exists: ", "(\\S+)").group(1));
        String categoryName = getField("Enter category to add this product to that and end to end", "(\\S+)").group(1);
        String description = getField("enter dscription", "(\\S+)").group(1);
        ArrayList<String> tags = new ArrayList<>();
        while (true) {
            nameToAdd = getField("enter tag to add to tags of this product and end to end: ", "(\\S+)").group(1);
            if (nameToAdd.equalsIgnoreCase("end")) {
                break;
            }
            tags.add(nameToAdd);
        }
        Controller.getOurController().createProductRequest(name, companyName, cost, categoryName, description, amountOfExist, tags, sellersNames);
    }

    public static Menu getSellerMenu() {
        return sellerMenu;
    }

    public void execute() {
        String input;
        System.out.println("Enter your command :");
        do {
            show();
            System.out.println("Enter Number :");
            if (!isThisRegexMatch("(\\d)", input = scanner.nextLine())) {
                continue;
            }
            switch (input.trim()) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    addProduct();
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
                    break;
                case "9":
                    show();
                    break;
                case "10":
                    return;
                case "11":
                    LoginMenu.logout();
                    break;
            }
        } while (!input.equalsIgnoreCase("end"));
    }

}