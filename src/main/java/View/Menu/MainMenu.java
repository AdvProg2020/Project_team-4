package View.Menu;

import View.CommandsSource;

import java.util.ArrayList;
import java.util.regex.Matcher;

import static View.CommandsSource.findEnum;

public class MainMenu extends Menu{

    private Menu productMenu = new ProductMenu();
    private Menu productsMenu = new ProductsMenu();
    private Menu offMenu = new OffsMenu();

    public MainMenu() {
        options.add("products");
        options.add("offs");
        options.add("back");
        options.add("create account [manager|seller|customer] [username]");
        options.add("login [username]");
        options.add("logout");
    }

    public void execute() {
        System.out.println("Enter Number :");
        String input;
        do {
            showCommands();
            if(!getMatcher(input = scanner.nextLine().trim(), "(\\d)").matches()){
                continue;
            }
            switch (input.trim()) {
                case "1":
                    productsMenu.execute();
                    break;
                case "2":
                    offMenu.execute();
                    break;
                case "3":
                    return;
                default:
                    DefaultMenu.getInstance().execute(Integer.parseInt(input) - options.size() + 3);
            }
        }while (!scanner.nextLine().trim().equalsIgnoreCase("end"));
    }
}
