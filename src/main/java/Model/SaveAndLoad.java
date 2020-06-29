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


    public void writeJSON(Object object, String classType, String name) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            FileWriter writer = new FileWriter(String.valueOf(classType + "\\" + name));
            writer.write(gson.toJson(object));
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

//    public void writeJSONArray(ArrayList<SaveAble> saveAble){
//        try {
//            GsonBuilder builder = new GsonBuilder();
//            Gson gson = builder.create();
//            FileWriter writer = new FileWriter(String.valueOf(saveAble.getClass() + "\\" + saveAble));
//            writer.write(gson.toJson(saveAble));
//            writer.close();
//        }catch (IOException e){
//            System.out.println(e);
//        }
//    }
//
//    public void writeJSONArrayProducts(ArrayList<Product> products){
//        try {
//            GsonBuilder builder = new GsonBuilder();
//            Gson gson = builder.create();
//            FileWriter writer = new FileWriter(String.valueOf(products.getClass() + "\\" + products));
//            writer.write(gson.toJson(products));
//            writer.close();
//        }catch (IOException e){
//            System.out.println(e);
//        }
//    }

    public Object readJSON(String name, Class className) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(className + "\\" + name));
            Object object = gson.fromJson(bufferedReader, className);
            bufferedReader.close();
            return object;
        } catch (IOException e) {
            return null;
        }
    }

    public Object readJSONByType(String name, Type classType) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(ArrayList.class + "\\" + name));
            Object object = gson.fromJson(bufferedReader, classType);
            return object;
        } catch (FileNotFoundException e) {
            return null;
        }
    }


    public void saveGenerally() {
        if (Controller.getOurController().getLoggedInAccount() != null)
            SaveAndLoad.getSaveAndLoad().writeJSON(Controller.getOurController().getLoggedInAccount(), Controller.getOurController().getLoggedInAccount().getClass().toString(), Controller.getOurController().getLoggedInAccount().getUserName());
        SaveAndLoad.getSaveAndLoad().writeJSON(Category.getAllCategories(), ArrayList.class.toString(), "allCategories");
        SaveAndLoad.getSaveAndLoad().writeJSON(CodedOff.getAllDiscounts(), ArrayList.class.toString(), "allOffCodes");
        SaveAndLoad.getSaveAndLoad().writeJSON(Off.getAllOffs(), ArrayList.class.toString(), "allOffs");
        SaveAndLoad.getSaveAndLoad().writeJSON(Product.getAllProducts(), ArrayList.class.toString(), "allProducts");
        SaveAndLoad.getSaveAndLoad().writeJSON(Manager.getEditOffRequests(), ArrayList.class.toString(), "editOffRequests");
        SaveAndLoad.getSaveAndLoad().writeJSON(Manager.getEditProductsRequests(), ArrayList.class.toString(), "editProductsRequests");
        SaveAndLoad.getSaveAndLoad().writeJSON(Manager.getRegisterSellerAccountRequests(), ArrayList.class.toString(), "registerSellerAccountRequests");
    }

}