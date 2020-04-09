import View.Manager;
import View.CommandProcessor;

public class Main {

    public static void main(String[] args) {
        Manager boss = new Manager();
        CommandProcessor commandProcessor = new CommandProcessor(boss);
        commandProcessor.run();
    }

}
