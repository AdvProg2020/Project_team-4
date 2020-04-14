package View.Menu;

import java.util.ArrayList;

    public abstract class Menu {
        protected Menu previousMenu;
        protected ArrayList<String> options = new ArrayList<>();

<<<<<<< HEAD
        public abstract void execute(Menu previousMenu, String input);
=======
        public abstract void run(Menu previousMenu, String input);
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb

        public void showCommands() {
            for (String option : options) {
                System.out.println("---> " + option);
            }
        }


    }
