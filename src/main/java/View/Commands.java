package View;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static View.Manager.getMatcher;

public enum Commands {
    /************************************GeneralCommands*****************************************/
    VIEW_PERSONAL_INFO(""),
    VIEW_DISCOUNT_CODES(""),
    EDIT_PERSONAL_INFO(""),
    SHOW_PRODUCTS(""),
    SHOW_PRODUCT(""),
    SHOW_CATEGORIES(""),
    REMOVE_PRODUCT(""),
    VIEW_BALANCE(""),
    SORTING(""),
    HELP(""),
    BACK(""),
    LOGOUT(""),

    /*********************************Create/LoginMenuCommands***********************************/
    CREATE_ACCOUNT("(?i)create\\s+account\\s+(manager|seller|customer)\\s+(\\w)+"),
    LOGIN(""),

    /************************************ManagerMenuCommands*************************************/
    MANAGE_USERS(""),
    VIEW_USER(""),
    CHANGE_TYPE_USER(""),
    REMOVE_USER(""),
    CREATE_MANAGER_PROFILE(""),
    MANAGE_ALL_PRODUCTS(""),
    CREATE_DISCOUNT_CODE(""),
    VIEW_DISCOUNT_CODE(""),
    EDIT_DISCOUNT_CODE(""),
    REMOVE_DISCOUNT_CODE(""),
    MANAGE_REQUESTS(""),
    DETAILS_REQUEST(""),
    ACCEPT_REQUEST(""),
    DECLINE_REQUEST(""),
    MANAGE_CATEGORIES(""),
    EDIT_CATEGORY(""),
    ADD_CATEGORY(""),
    REMOVE_CATEGORY(""),

    /**********************************SellerMenuCommands****************************************/
    VIEW_COMPANY_INFORMATION("(?i)view\\s+company\\s+information\\s*"),
    VIEW_SALES_HISTORY(""),
    MANAGE_PRODUCTS(""),
    VIEW_PRODUCT(""),
    VIEW_BUYERS(""),
    EDIT_PRODUCT(""),
    ADD_PRODUCT(""),
    VIEW_OFFS(""),
    VIEW_OFF(""),
    EDIT_OFF(""),
    ADD_OFF(""),

    /**********************************CustomerMenuCommands***************************************/
    VIEW_CART(""),
    PURCHASE(""),
    VIEW_ORDERS(""),
    SHOW_ORDERS(""),
    RATE(""),
    INCREASE_PRODUCT(""),
    DECREASE_PRODUCT(""),
    SHOW_TOTAL_PRICE(""),

    /***********************************ProductsMenuCommands**************************************/
    PRODUCTS(""),
    VIEW_CATEGORIES(""),
    FILTERING(""),
    SHOW_AVAILABLE_FILTERS(""),
    FILTER(""),
    CURRENT_FILTERS(""),
    DISABLE_FILTER(""),
    SHOW_AVAILABLE_SORTS(""),
    SORT(""),
    CURRENT_SORT(""),
    DISABLE_SORT(""),

    /**********************************ProductMenuCommands****************************************/
    DIGEST(""),
    ADD_TO_CART(""),
    SELECT_SELLER(""),
    ATTRIBUTES(""),
    COMPARE(""),
    COMMENTS(""),
    ADD_COMMENT(""),

    /*************************************OffMenuCommand*******************************************/
    OFFS("");




    /***************************************EnumBody***********************************************/


    // ------> fields
    private String name;
    private Pattern commandPattern;
    private ArrayList<String> allRegex = new ArrayList<>();


    // ------> constructor
    Commands(String regex) {
        allRegex.add(regex);
        this.commandPattern = Pattern.compile(regex);
    }

    //-------> methods
    public ArrayList getAllRegex() {
        return allRegex;
    }

    public String getName() {
        return name;
    }

    public static String findEnum(ArrayList<String> allRegex, String input) {
        for (String regex : allRegex) {
            if (getMatcher(input, regex).find()) {
                return getEnumNameByRegex(regex);
            }
        }
        return null;
    }

    public static String getEnumNameByRegex(String regex) {
        for (Commands command : Commands.values()) {
            if (command.getName().equals(regex)) {
                return command.getName();
            }
        }
        return null;
    }

}
