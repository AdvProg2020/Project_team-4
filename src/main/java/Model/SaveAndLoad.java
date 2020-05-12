package Model;

import Control.Controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SaveAndLoad {

    private static SaveAndLoad saveAndLoad = new SaveAndLoad();

    private SaveAndLoad() {

    }

    public static SaveAndLoad getSaveAndLoad() {
        return saveAndLoad;
    }

    public void writeJSONAccount(SaveAble saveAble){
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            FileWriter writer = new FileWriter(saveAble.getClass() + "\\" + saveAble.getName());
            writer.write(gson.toJson(saveAble));
            writer.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public void writeJSONAccount(ArrayList<SaveAble> saveAble){
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            FileWriter writer = new FileWriter(String.valueOf(  saveAble.getClass() + "\\" + saveAble.getClass()));
            writer.write(gson.toJson(saveAble));
            writer.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public SaveAble readJSONAccount(String name) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(name));

            return gson.fromJson(bufferedReader, SaveAble.class);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public Object readJSONByType(String name, Type classType) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(ArrayList.class + "\\" + name));
            return gson.fromJson(bufferedReader, classType);
        } catch (FileNotFoundException e) {
            return null;
        }
    }


    public void saveGenerally() {
        SaveAndLoad.getSaveAndLoad().writeJSON(Controller.getOurController().getLoggedInAccount(), Controller.getOurController().getLoggedInAccount().getClass(), Controller.getOurController().getLoggedInAccount().getUserName());
        SaveAndLoad.getSaveAndLoad().writeJSON(Category.getAllCategories(), ArrayList.class, "allCategories");
        SaveAndLoad.getSaveAndLoad().writeJSON(CodedOff.getAllDiscounts(), ArrayList.class, "allOfCodes");
        SaveAndLoad.getSaveAndLoad().writeJSON(Off.getAllOffs(), ArrayList.class, "allOffs");
        SaveAndLoad.getSaveAndLoad().writeJSON(Product.getAllProducts(), ArrayList.class, "allProducts");
        SaveAndLoad.getSaveAndLoad().writeJSON(Manager.getEditOffRequests(), ArrayList.class, "editOffRequests");

        SaveAndLoad.getSaveAndLoad().writeJSON(Manager.getEditProductsRequests(), ArrayList.class, "editProductsRequests");

        SaveAndLoad.getSaveAndLoad().writeJSON(Manager.getRegisterSellerAccountRequests(), ArrayList.class, "registerSellerAccountRequests");


        final File folder = new File("\\class Model.Seller");
        System.out.println(folder.getName());
        for (String nameOfFolder: folder.list()) {

        }


    }

}