package View.Menu;

import View.CommandProcessor;

import static View.CommandProcessor.*;
import static View.Manager.*;

public class OffMenu extends Menu {
    public OffMenu() {
        options.add("show product [productId]");
        options.add("help");
        options.add("back");
        options.add("logout");
    }

<<<<<<< HEAD
    public void execute(Menu previousMenu, String input) {
        processOffsList(input.split("\\s"));
        System.out.println("Enter your command :");
        while (!(input = CommandProcessor.scanner.nextLine()).equalsIgnoreCase("end")) {
            switch(commandsSource.findEnum(commandsSource.getAllRegex(), input)){
                case "SHOW_PRODUCT":
                    processShowProduct(input.split("\\s"), this, input);
=======
    public void run(Menu previousMenu, String input) {
        processOffsList(input.split("\\s"));
        System.out.println("Enter your command :");
        while (!(input = CommandProcessor.scanner.nextLine()).equalsIgnoreCase("end")) {
            switch(commands.findEnum(commands.getAllRegex(), input)){
                case "SHOW_PRODUCT":
                    processShowProduct(input.split("\\s"));
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
                case "HELP":
                    showCommands();
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
                case "LOGOUT":
            }
        }
    }
}
