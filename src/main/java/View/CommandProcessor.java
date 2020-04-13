package View;



import View.Menu.*;

import java.util.ArrayList;
import java.util.Scanner;
import static View.Manager.*;

public class CommandProcessor {
    public static Manager manager = new Manager();
    public static Scanner scanner;
    public static ArrayList<String> validCommands = new ArrayList<>();

    public CommandProcessor(Manager boss) {
        this.manager = manager;
        scanner = new Scanner(System.in);
    }

    public static void processCreateAccount(String[] splitInput) {
        Manager.createAccount(splitInput[2], splitInput[3], getPassword());
    }

    public static void processLogin(String[] splitInput) {
        Manager.login(splitInput[1], getPassword());
    }

    public static void processOffsList(String[] splitInput) {
        Manager.offsList();
    }

    public static void processShowProduct(String[] splitInput, Menu previousMenu, String input){
        Manager.showProduct(splitInput[2], previousMenu, input);
    }

    public static void processDigest(String productId){
        Manager.digest(productId);
    }

    public static void processAddToCart() {
        addToCart();
    }

    public static void processSelectSeller(String[] splitInput){
        selectSeller(splitInput[2]);
    }




}
