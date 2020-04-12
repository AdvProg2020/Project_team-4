package View;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static View.Manager.getMatcher;

public enum Commands {
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

    CREATE_ACCOUNT("CREATE_ACCOUNT", "(?i)create\\s+account\\s+(manager|seller|customer)\\s+(\\w)+"),
    LOGIN("LOGIN", ""),


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
    DETAILS_REQUEST(name, ""),
    ACCEPT_REQUEST(name, ""),
    DECLINE_REQUEST(name, ""),
    MANAGE_CATEGORIES(name, ""),
    EDIT_CATEGORY(name, ""),
    ADD_CATEGORY(name, ""),
    REMOVE_CATEGORY(name, ""),


    /**********************************SellerMenuCommands****************************************/

    VIEW_COMPANY_INFORMATION(name, "(?i)view\\s+company\\s+information\\s*"),
    VIEW_SALES_HISTORY(name, ""),
    MANAGE_PRODUCTS(name, ""),
    VIEW_PRODUCT(name, ""),
    VIEW_BUYERS(name, ""),
    EDIT_PRODUCT(name, ""),
    ADD_PRODUCT(name, ""),
    VIEW_OFFS(name, ""),
    VIEW_OFF(name, ""),
    EDIT_OFF(name, ""),
    ADD_OFF(name, ""),


    /**********************************CustomerMenuCommands***************************************/

    VIEW_CART(name, ""),
    PURCHASE(name, ""),
    VIEW_ORDERS(name, ""),
    SHOW_ORDERS(name, ""),
    RATE(name, ""),
    INCREASE_PRODUCT(name, ""),
    DECREASE_PRODUCT(name, ""),
    SHOW_TOTAL_PRICE(name, ""),


    /***********************************ProductsMenuCommands**************************************/

    PRODUCTS(name, ""),
    VIEW_CATEGORIES(name, ""),
    FILTERING(name, ""),
    SHOW_AVAILABLE_FILTERS(name, ""),
    FILTER(name, ""),
    CURRENT_FILTERS(name, ""),
    DISABLE_FILTER(name, ""),
    SHOW_AVAILABLE_SORTS(name, ""),
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


    // ------> constructor
    Commands(String name, String regex) {
        this.name = name;
        this.regex = regex;
        allRegex.add(regex);
        this.commandPattern = Pattern.compile(regex);
    }

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
        return null;
    }

    public static String getEnumNameByRegex(String regex) {
        for (Commands command : Commands.values()) {
            if (command.getRegex().equals(regex)) {
                return command.getName();
            }
        }
        return null;
    }
}
