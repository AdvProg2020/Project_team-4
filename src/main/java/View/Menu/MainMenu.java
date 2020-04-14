package View.Menu;

import View.*;

import static View.CommandProcessor.*;
<<<<<<< HEAD
import static View.CommandsSource.findEnum;
=======
import static View.Commands.findEnum;
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
import static View.Manager.*;

public class MainMenu extends Menu {


    private Menu productMenu = new ProductMenu();
    private Menu productsMenu = new ProductsMenu();
    private Menu mainMenu = new MainMenu();
    private Menu createLoginMenu = new CreateLoginMenu();
    private Menu offMenu = new OffMenu();

    public MainMenu() {
        options.add("create account [manager|seller|customer] [username]");
        options.add("login [username]");
        options.add("products");
        options.add("offs");
        options.add("help");
        options.add("back");
        options.add("logout");
    }

<<<<<<< HEAD
    public void execute(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = Manager.scanner.nextLine()).equalsIgnoreCase("end")) {
            switch (findEnum(commandsSource.getAllRegex(), input)) {
=======
    public void run(Menu previousMenu, String input) {
        System.out.println("Enter your command :");
        while (!(input = Manager.scanner.nextLine()).equalsIgnoreCase("end")) {
            switch (findEnum(commands.getAllRegex(), input)) {
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
                case "CREATE_ACCOUNT":
                    processCreateAccount(input.split("\\s"));
                    break;
                case "LOGIN":
                    processLogin(input.split("\\s"));
                    break;

                case "PRODUCTS":
<<<<<<< HEAD
                    productsMenu.execute(this, input);
                    break;

                case "OFFS":
                    offMenu.execute(this, input);
=======
                    productsMenu.run(this, input);
                    break;

                case "OFFS":
                    offMenu.run(this, input);
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
                    break;

                case "HELP":
                    showCommands();
                    break;

                case "BACK":
                    if (previousMenu == null) {
                        System.err.println("This your first menu.");
                    } else {
<<<<<<< HEAD
                        previousMenu.execute(this, input);
=======
                        previousMenu.run(this, input);
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
                    }
                    break;
                default:
                    if (Manager.isValidCommand(input)) {
                        System.err.println("You must login first");
                    } else {
                        System.err.println("invalid command");
                    }
                    break;
            }
        }
    }
}
