package View.Menu;

import Control.Controller;

import static View.Outputs.*;

public class OffsMenu extends Menu {

    private static Menu offsMenu = new OffsMenu();
    private ProductMenu productMenu = new ProductMenu();

    public OffsMenu() {
        options.add("show product [productId]");
        options.add("help");
        options.add("back");
    }

    public static Menu getOffsMenu() {
        return offsMenu;
    }

    public static void offsList() {
        printOffsListResult(Controller.getOurController().requestOffsList());
    }

    public void execute() {
        offsList();
        String input = "";
        do {
            show();
            System.out.println("Enter Number :");
            if (!isThisRegexMatch("(\\d)", input = scanner.nextLine())) {
                continue;
            }
            switch (input.trim()) {
                case "1":
                    String productId = getField("enter productId", "(\\S+)").group(1);
                    ProductMenu productMenu = new ProductMenu();
                    productMenu.execute(productId);
                    break;
                case "2":
                    show();
                    break;
                case "3":
                    return;
            }
        }while (true);
    }
}
