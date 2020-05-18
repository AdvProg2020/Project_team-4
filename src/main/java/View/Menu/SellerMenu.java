package View.Menu;

import Control.Controller;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class SellerMenu extends Menu {

    private static Menu sellerMenu = new SellerMenu();

    public SellerMenu() {
        options.add("view personal info");
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

    private static void editProduct(String productId) {
        String companyName = getField("enter companyname: ", "(\\S+)").group(1);
        int cost = Integer.parseInt(getField("Enter cost: ", "(\\d+)").group(1));
        String nameToAdd = "";
        int amountOfExist = Integer.parseInt(getField("enter how many of this product exists: ", "(\\S+)").group(1));
        String categoryName = getField("Enter category to add this product to that and end to end", "(\\S+)").group(1);
        String description = getField("enter description", "(\\S+)").group(1);
        ArrayList<String> tags = new ArrayList<>();
        while (true) {
            nameToAdd = getField("enter tag to add to tags of this product and end to end: ", "(\\S+)").group(1);
            if (nameToAdd.equalsIgnoreCase("end")) {
                break;
            }
            tags.add(nameToAdd);
        }
        Controller.getOurController().editProductRequest(productId, companyName, cost, categoryName, description, amountOfExist, tags);
    }

    public static Menu getSellerMenu() {
        return sellerMenu;
    }

    public static Menu getViewOffsMenu () {
        return new Menu() {
            @Override
            protected void execute() {
                options.add("view [offId]");
                options.add("edit [offId]");
                options.add("add off");
                options.add("end");
                System.out.println(Controller.getOurController().getAllOffsOfSeller());
                String input = "";
                do {
                    show();
                    if (!isThisRegexMatch("(\\d)", input = scanner.nextLine())) {
                        continue;
                    }
                    switch (input.trim()) {
                        case "1":
                            String nameOfOff = getField("Enter an off your offs barcode: ", "(\\S+)").group(1);
                            viewOff(nameOfOff);
                            break;
                        case "2":
                            String nameOfOffToEdit = getField("Enter on off your offs barcode: ", "(\\S+)").group(1);
                            editOff(nameOfOffToEdit);
                            break;
                        case "3":
                            createOff();
                            break;
                        case "4":
                            return;

                    }
                }while (true);
            }

            private void viewOff(String name) {
                Controller.getOurController().getOffByName(name);
            }

            private void createOff() {
                ArrayList<String> productsToDeleteOrAdd = new ArrayList<>();
                String nameToAdd = "";
                while (true) {
                    nameToAdd = getField("enter productBarcode like this to add if not exists and remove if does: productBarcode1 and end to end", "(\\S+)").group(1);
                    if (nameToAdd.equalsIgnoreCase("end")) {
                        break;
                    }
                    productsToDeleteOrAdd.add(nameToAdd);
                }
                Matcher startDate = getField("Enter start to change", "(\\d\\d\\d\\d), (\\d\\d), (\\d\\d), (\\d\\d), (\\d\\d)");
                Matcher endDate = getField("Enter endDate to change", "(\\d\\d\\d\\d), (\\d\\d), (\\d\\d), (\\d\\d), (\\d\\d)");
                int offAmount = Integer.parseInt(getField("Enter max Of offAmount", "(\\S+)").group(1));
                Controller.getOurController().createOrEditOffRequest(productsToDeleteOrAdd, startDate, endDate, offAmount);
            }
            private void editOff(String name) {
                createOff();
                Controller.getOurController().removeOff(name);
            }
        };
    }

    private Menu manageProducts(){
        return new Menu() {
            @Override
            protected void execute() {
                options.add("view [productId]");
                options.add("view buyers [productId]");
                options.add("edit [productId]");
                options.add("back");
                String input;
                Matcher matcher;
                do {
                    show();
                    System.out.println("Enter number:");
                    if (!isThisRegexMatch("(\\d)", input = scanner.nextLine())) {
                        continue;
                    }
                    switch(input.trim()){
                        case "1":
                            String productId = getField("enter productId", "(\\S+)").group(1);
                            ProductMenu productMenu = new ProductMenu();
                            productMenu.execute(productId);
                            break;
                        case "2":
                            productId = getField("enter productId", "(\\S+)").group(1);
                            System.out.println(Controller.getOurController().viewByers(productId));
                            break;
                        case "3":
                            productId = getField("enter productId", "(\\S+)").group(1);
                            editProduct(productId);
                            break;
                        case "4":
                            return;
                    }
                } while(true);
            }
        };
    }


    public void execute() {
        String input;
        do {
            show();
            System.out.println("Enter Number :");
            if (!isThisRegexMatch("(\\d+)", input = scanner.nextLine())) {
                continue;
            }
            switch (input.trim()) {
                case "1":
                    CustomerMenu.viewAndEditPersonalInfo().execute();
                    break;
                    //////////////////////////////////////////////jaye bahs dare
                case "2":
                    System.out.println(Controller.getOurController().getCompanyNameInSeller());
                    break;
                case "3":
                    System.out.println(Controller.getOurController().requestSalesHistoryInfoInSeller());
                    break;
                case "4":
                    manageProducts().execute();
                    break;
                case "5":
                    addProduct();
                    break;
                case "6":
                    String productId = getField("enter productId", "(\\S+)").group(1);
                    Controller.getOurController().removeProductFromSellerProducts(productId);
                    break;
                case "7":
                    System.out.println(Controller.getOurController().showCategories());
                    break;
                case "8":
                    getViewOffsMenu().execute();
                    break;
                case "9":
                    System.out.println(Controller.getOurController().getCredit());
                    break;
                case "10":
                    show();
                    break;
                case "11":
                    return;
            }
        } while (!input.equalsIgnoreCase("end"));
    }

}