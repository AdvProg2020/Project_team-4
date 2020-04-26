package View.Menu;

public class MainMenu extends Menu {

//    private Menu productMenu = new ProductMenu();
//    private Menu productsMenu = new ProductsMenu();
//    private Menu offMenu = new OffsMenu();

    public MainMenu() {
        options.add("products");
        options.add("offs");
        options.add("login menu");
        options.add("help");
        options.add("back");

    }

    public void execute() {
        String input;
        do {
            showCommands();
            System.out.println("Enter Number :");
            if(!getMatcher(input = scanner.nextLine().trim(), "(\\d)").matches()){
                continue;
            }
            switch (input.trim()) {
                case "1":
//                    productsMenu.execute();
                    break;
                case "2":
                    //off menu
//                    offMenu.execute();
                    break;
                case "3":
                    LoginMenu.getLoginMenu().execute();
                    showCommands();
                    break;
                case "4":
                    showCommands();
                    break;
                case "5":
                    return;
            }
        }while (!input.equalsIgnoreCase("end"));
    }
}
