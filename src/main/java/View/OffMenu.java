package View;

import static View.Manager.getMatcher;
import static View.Manager.showOffProductList;

public class OffMenu extends Menu {
    public OffMenu() {
        options.add("");

    }

    public void run(Menu previousMenu, String input) {
        showOffProductList();
        System.out.println("Enter your command :");
        while (!(input = CommandProcessor.scanner.nextLine()).equalsIgnoreCase("end")) {
            if (getMatcher(input, "show product [productId]").find()) {
            }
        }
    }
}
