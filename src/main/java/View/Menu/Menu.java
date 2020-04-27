package View.Menu;

import View.CommandsSource;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Menu {

    protected static Scanner scanner = new Scanner(System.in);
    protected CommandsSource commands;
    protected ArrayList<String> options = new ArrayList<>();

    public static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

    public static Scanner getScanner() {
        return scanner;
    }

    protected void showCommands() {
        int number = 0;
        for (String option : options) {
            System.out.println(++number + " " + option);
        }
    }

}
