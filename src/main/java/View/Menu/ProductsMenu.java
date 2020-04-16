package View.Menu;

import static View.CommandsSource.findEnum;

public class ProductsMenu extends Menu {
    public void execute(String input) {
        System.out.println("Enter your command :");

        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            String[] splitInput = input.split("\\s");
            switch (findEnum(commands.getAllRegex(), input)) {
                case "SORTING":

                case"SHOW_PRODUCTS":

                case "SHOW_PRODUCT":

        }
    }
}

