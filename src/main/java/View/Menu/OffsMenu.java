//package View.Menu;
//
//import Control.Controller;
//import View.CommandsSource;
//
//import static View.Outputs.*;
//
//public class OffsMenu extends Menu {
//
//    private ProductMenu productMenu = new ProductMenu();
//
//    public OffsMenu() {
//        options.add("show product [productId]");
//        options.add("help");
//        options.add("back");
//        options.add("logout");
//    }
//
//    public static void offsList() {
//        printOffsListResult(Controller.getOurController().requestOffsList());
//    }
//
//    private void showProduct(String[] splitInput, String input) {
//        productMenu.setProductId(splitInput[2]);
//        productMenu.execute();
//    }
//
//    public void execute() {
//        offsList();
//        System.out.println("Enter number :");
//        while(true) {
//            showCommands();
//            String input;
//            if(!getMatcher(input = scanner.nextLine().trim(), "(\\d)").matches()){
//                continue;
//            }
//            switch (input) {
//                case "1":
//                    System.out.println("show product");
//                    break;
//                case "2":
//                    return;
//                default:
//                    DefaultMenu.getInstance().execute(Integer.parseInt(input) - 2);
//            }
//        }
//    }
//}
