package View.Menu;

import View.CommandsSource;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
        protected Menu previousMenu;
        protected Scanner scanner;
        protected CommandsSource commands;
        protected ArrayList<String> options = new ArrayList<>();

        public void execute(Menu previousMenu, String input){

        }

        public void showCommands() {
            for (String option : options) {
                System.out.println("---> " + option);
            }
        }


    }


    /************************************************/
    private String name;
    protected HashMap<Integer, Menu> submenus;
    protected Menu parentMenu;
    public static Scanner scanner;
    protected static Manager manager;
    protected static ArrayList<Menu> allMenus;
