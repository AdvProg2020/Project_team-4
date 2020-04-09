package View;

import java.util.ArrayList;

public abstract class Menu {
    protected Menu previousMenu;
    protected ArrayList<String> options = new ArrayList<>();
    public abstract void run(Menu previousMenu, String input);
    public void help(){
        for (String option : options) {
            System.out.println("---> " + option);
        }
    }


}
