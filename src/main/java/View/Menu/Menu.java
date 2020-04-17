package View.Menu;

import Control.Controller;
import View.CommandsSource;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static View.Outputs.*;

public abstract class Menu {
//    protected Menu previousMenu;
    protected static Scanner scanner;
    protected CommandsSource commands;
    protected ArrayList<String> options = new ArrayList<>();
//    private ArrayList<String> validCommands = new ArrayList<>();

    public static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
//
//    public static String getType() {
//        return Controller.getOurController().requestLoginedUser();
//    }
//
//    public boolean isAnyUserLogin() {
//        return Controller.getOurController().requestIsAnyUserLogin();
//    }

//    public boolean isValidCommand(String command) {
//        return !findEnum(commands.getAllRegex(), command).equals("Very wrong");
//    }

    protected void showCommands() {
        int number = 0;
        for (String option : options) {
            System.out.println(++number + "" + option);
        }
    }

    protected abstract void execute();
}
