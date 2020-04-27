package View;

import View.Menu.MainMenu;
import View.Menu.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static View.Menu.Menu.getMatcher;

public enum CommandsSource {
    /************************************GeneralCommands*****************************************/

    VIEW_PERSONAL_INFO("VIEW_PERSONAL_INFO", ""),
    VIEW_DISCOUNT_CODES("VIEW_DISCOUNT_CODES", ""),
    EDIT_PERSONAL_INFO("EDIT_PERSONAL_INFO", ""),
    SHOW_PRODUCTS("SHOW_PRODUCTS", ""),
    SHOW_PRODUCT("SHOW_PRODUCT", ""),
    SHOW_CATEGORIES("SHOW_CATEGORIES", ""),
    REMOVE_PRODUCT("REMOVE_PRODUCT", ""),
    VIEW_BALANCE("VIEW_BALANCE", ""),
    SORTING("SORTING", ""),
    HELP("HELP", ""),
    BACK("BACK", ""),
    LOGOUT("LOGOUT", ""),


    /*********************************Create/LoginMenuCommands***********************************/

    CREATE_ACCOUNT("CREATE_ACCOUNT", "(manager|seller|customer)\\s+(\\S+)\\s+(\\S+)"),
    LOGIN("LOGIN", "(\\S+)\\s+(\\S+)"),


    /************************************ManagerMenuCommands*************************************/

    MANAGE_USERS("MANAGE_USERS", ""),
    VIEW_USER("VIEW_USER", ""),
    CHANGE_TYPE_USER("CHANGE_TYPE_USER", ""),
    REMOVE_USER("REMOVE_USER", ""),
    CREATE_MANAGER_PROFILE("CREATE_MANAGER_PROFILE", ""),
    MANAGE_ALL_PRODUCTS("MANAGE_ALL_PRODUCTS", ""),
    CREATE_DISCOUNT_CODE("CREATE_DISCOUNT_CODE", ""),
    VIEW_DISCOUNT_CODE("VIEW_DISCOUNT_CODE", ""),
    EDIT_DISCOUNT_CODE("EDIT_DISCOUNT_CODE", ""),
    REMOVE_DISCOUNT_CODE("REMOVE_DISCOUNT_CODE", ""),
    MANAGE_REQUESTS("MANAGE_REQUESTS", ""),
    DETAILS_REQUEST("DETAILS_REQUEST", ""),
    ACCEPT_REQUEST("ACCEPT_REQUEST", ""),
    DECLINE_REQUEST("DECLINE_REQUEST", ""),
    MANAGE_CATEGORIES("MANAGE_CATEGORIES", ""),
    EDIT_CATEGORY("EDIT_CATEGORY", ""),
    ADD_CATEGORY("ADD_CATEGORY", ""),
    REMOVE_CATEGORY("REMOVE_CATEGORY", ""),


    /**********************************SellerMenuCommands****************************************/

    VIEW_COMPANY_INFORMATION("VIEW_COMPANY_INFORMATION", "(?i)view\\s+company\\s+information\\s*"),
    VIEW_SALES_HISTORY("VIEW_SALES_HISTORY", ""),
    MANAGE_PRODUCTS("MANAGE_PRODUCTS", ""),
    VIEW_PRODUCT("VIEW_PRODUCT", ""),
    VIEW_BUYERS("VIEW_BUYERS", ""),
    EDIT_PRODUCT("EDIT_PRODUCT", ""),
    ADD_PRODUCT("ADD_PRODUCT", ""),
    VIEW_OFFS("VIEW_OFFS", ""),
    VIEW_OFF("VIEW_OFF", ""),
    EDIT_OFF("EDIT_OFF", ""),
    ADD_OFF("ADD_OFF", ""),


    /**********************************CustomerMenuCommands***************************************/

    VIEW_CART("VIEW_CART", ""),
    PURCHASE("PURCHASE", ""),
    VIEW_ORDERS("VIEW_ORDERS", ""),
    SHOW_ORDERS("SHOW_ORDERS", ""),
    RATE("RATE", ""),
    INCREASE_PRODUCT("INCREASE_PRODUCT", ""),
    DECREASE_PRODUCT("DECREASE_PRODUCT", ""),
    SHOW_TOTAL_PRICE("SHOW_TOTAL_PRICE", ""),


    /***********************************ProductsMenuCommands**************************************/

    PRODUCTS("PRODUCTS", ""),
    VIEW_CATEGORIES("VIEW_CATEGORIES", ""),
    FILTERING("FILTERING", ""),
    SHOW_AVAILABLE_FILTERS("SHOW_AVAILABLE_FILTERS", ""),
    FILTER("FILTER", ""),
    CURRENT_FILTERS("CURRENT_FILTERS", ""),
    DISABLE_FILTER("DISABLE_FILTER", ""),
    SHOW_AVAILABLE_SORTS("SHOW_AVAILABLE_SORTS", ""),
    SORT("SORT", ""),
    CURRENT_SORT("CURRENT_SORT", ""),
    DISABLE_SORT("DISABLE_SORT", ""),


    /**********************************ProductMenuCommands****************************************/

    DIGEST("DIGEST", ""),
    ADD_TO_CART("ADD_TO_CART", ""),
    SELECT_SELLER("SELECT_SELLER", ""),
    ATTRIBUTES("ATTRIBUTES", ""),
    COMPARE("COMPARE", ""),
    COMMENTS("COMMENTS", ""),
    ADD_COMMENT("ADD_COMMENT", ""),


    /*************************************OffMenuCommand*******************************************/

    OFFS("OFFS", "");




    /***************************************EnumBody***********************************************/


    // ------> fields
    private String name;
    private String regex;
    public Pattern commandPattern;
    private ArrayList<String> allRegex = new ArrayList<>();
    private static Scanner scanner = Menu.getScanner();


    // ------> constructor
    CommandsSource(String name, String regex) {
        this.name = name;
        this.regex = regex;
        allRegex.add(regex);
        this.commandPattern = Pattern.compile(regex);
    }


//    // ------> Menus
//    private static Menu managerMenu = new ManagerMenu();
//    private static Menu sellerMenu = new SellerMenu();
//    private static Menu customerMenu = new CustomerMenu();
//    private static Menu productMenu = new ProductMenu();
//    private static Menu offMenu = new OffsMenu();
    private static Menu mainMenu = new MainMenu();


    //-------> methods
    public List<String> getAllRegex() {
        return allRegex;
    }

    public String getName() {
        return name;
    }

    public String getRegex() {
        return regex;
    }

    public static String findEnum(List<String> allRegex, String input) {
        for (String regex : allRegex) {
            if (getMatcher(input, regex).find()) {
                return getEnumNameByRegex(regex);
            }
        }
        return "Very wrong";
    }

    public static String getEnumNameByRegex(String regex) {
        for (CommandsSource command : CommandsSource.values()) {
            if (command.getRegex().equals(regex)) {
                return command.getName();
            }
        }
        return null;
    }

    public static boolean isThisRegexMatch(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static Matcher getField(String errorToPrint, String regex) {
        String input = "";
        do{
            if (input.equalsIgnoreCase("back")) {
                return null;
            }
            System.out.println(errorToPrint);
        }
        while (!isThisRegexMatch(regex, input = scanner.nextLine().trim()));
        Matcher matcher = Pattern.compile(regex).matcher(input);
        matcher.matches();
        return matcher;
    }
}
