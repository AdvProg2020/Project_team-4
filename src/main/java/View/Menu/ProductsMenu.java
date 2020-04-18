package View.Menu;

import java.util.regex.Matcher;

import static View.CommandsSource.findEnum;

public class ProductsMenu extends Menu {

    public ProductsMenu() {
        options.add("sorting");
        options.add("show products");
        options.add("show product");
        options.add("back");
        options.add("create account [manager|seller|customer] [username]");
        options.add("login [username]");
        options.add("logout");
        options.add("help");
    }

    private static void sorting(){

    }

    private static void showProducts(){}

    private static void showProduct(){

    }

    public void execute() {
        System.out.println("Enter your command :");
        Matcher matcher;
        while (true) {
            showCommands();
            String input = scanner.nextLine().trim();
            if(!getMatcher(input, "(\\d)").matches()){
                continue;
            }
            switch (input) {
                case "1":
                    sorting();
                    break;
                case "2":
                    showProducts();
                    break;
                case "3":
                    showProduct();
                    break;
                case "4":
                    return;
                default:
                    DefaultMenu.getInstance().execute(Integer.parseInt(input) - options.size() + 3);
            }
        }
    }
}

