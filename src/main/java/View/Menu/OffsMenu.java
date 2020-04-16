package View.Menu;

import Control.Controller;
import static View.Outputs.*;

public class OffsMenu extends Menu {
    public OffsMenu() {
        options.add("show product [productId]");
        options.add("help");
        options.add("back");
        options.add("logout");
    }

    public static void offsList(){
        printOffsListResult(Controller.getOurController().requestOffsList());
    }

    public void execute(String input) {
        System.out.println("Enter your command :");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            String[] splitInput = input.split("\\s");
            switch(commands.findEnum(commands.getAllRegex(), input)){
                case "SHOW_PRODUCT":
                    showProduct(input.split("\\s"));
                default:
                    super.execute(input);
            }
        }
    }
}
