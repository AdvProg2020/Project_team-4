package View.Menu;

import static View.CommandsSource.findEnum;

public class MainMenu extends Menu {

    private Menu productMenu = new ProductMenu();
    private Menu productsMenu = new ProductsMenu();
    private Menu mainMenu = new MainMenu();
    private Menu offMenu = new OffsMenu();

    public MainMenu() {
        options.add("create account [manager|seller|customer] [username]");
        options.add("login [username]");
        options.add("logout");
        options.add("products");
        options.add("offs");
        options.add("help");
        options.add("back");
        options.add("logout");
    }

    public void execute(String input) {
        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            String[] splitInput = input.split("\\s");
            switch (findEnum(commands.getAllRegex(), input)) {
                case "PRODUCTS":
                    productsMenu.execute(input);
                    break;
                case "OFFS":
                    offMenu.execute(input);
                    break;
                default:
                    super.execute(input);
            }
        }
    }
}
