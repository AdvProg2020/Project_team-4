package View.Menu;

import java.util.ArrayList;

    public abstract class Menu {
        protected Menu previousMenu;
        protected ArrayList<String> options = new ArrayList<>();

        public abstract void execute(Menu previousMenu, String input);

        public void showCommands() {
            for (String option : options) {
                System.out.println("---> " + option);
            }
        }


    }
