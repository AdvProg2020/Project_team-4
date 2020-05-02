package View.Menu;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Menu {

    protected static Scanner scanner = new Scanner(System.in);
    protected ArrayList<String> options = new ArrayList<>();

    public enum CommandsSource {
        CREATE_ACCOUNT("(manager|seller|customer)\\s+(\\S+)\\s+(\\S+)"),
        LOGIN("(\\S+)\\s+(\\S+)");

        private String regex;
        public Pattern commandPattern;
        private ArrayList<String> allRegex = new ArrayList<>();
        private static Scanner scanner = Menu.getScanner();

        CommandsSource(String regex) {
            this.regex = regex;
            allRegex.add(regex);
            this.commandPattern = Pattern.compile(regex);
        }

    }

    public static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

    public static boolean isThisRegexMatch(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static Matcher getField(String error, String regex) {
        String input = "";
        do{
            if (input.equalsIgnoreCase("back")) {
                return null;
            }
            System.out.println(error);
        }
        while (!isThisRegexMatch(regex, input = scanner.nextLine().trim()));
        Matcher matcher = Pattern.compile(regex).matcher(input);
        matcher.matches();
        return matcher;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    protected void show() {
        int number = 0;
        for (String option : options) {
            System.out.printf("%2d. %s\n", ++number , option);
        }
    }

    protected void execute() {}

}
