package Control;

public class Controller {
    private static Controller ourController = new Controller();

    public static Controller getOurController() {
        return ourController;
    }

}

