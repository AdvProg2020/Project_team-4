import Control.Controller;
import Model.*;
import View.Menu.MainMenu;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;

import  static javafx.application.Application.launch;


public class Main {

    @Override
    public void start(Stage primaryStage) throws Exception{
        readFiles();
        loadFirstMenu();
//        test(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void readFiles() {
        File file = new File(String.valueOf(Customer.class));
        file.mkdir();
        File file6 = new File(String.valueOf(Seller.class));
        file6.mkdir();
        File file7 = new File(String.valueOf(Manager.class));
        file7.mkdir();
        File file1 = new File(Product.class + "");
        file1.mkdir();
        File file2 = new File(Off.class + "");
        file2.mkdir();
        File file3 = new File(CodedOff.class + "");
        file3.mkdir();
        File file4 = new File(Seller.class + "");
        file4.mkdir();
        File file5 = new File(String.valueOf(RequestANewSellerAccount.class));
        file5.mkdirs();
        File file8 = new File(String.valueOf(CodedOff.class));
        file8.mkdirs();
        File file9 = new File(String.valueOf(String.class));
        file9.mkdirs();
        File file10 = new File(String.valueOf(ArrayList.class));
        file10.mkdirs();
        File file11 = new File(String.valueOf(Category.class));
        file11.mkdirs();
        Controller.readOffCodesFromFile();
        Controller.readRequestsFromFile();
        Controller.readOffsFromFile();
        Controller.readProductsFromFile();
        Controller.readCategoriesFromFile();
    }

    private static void loadFirstMenu(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FirstMenuFx.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("FirstMenu");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }

}