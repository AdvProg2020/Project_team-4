package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;

public class SaveAndLoad {

    private static SaveAndLoad saveAndLoad = new SaveAndLoad();

    private SaveAndLoad() {

    }

    public static SaveAndLoad getSaveAndLoad() {
        return saveAndLoad;
    }


    public void writeJSON(Object object, Class classType, String name){
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            FileWriter writer = new FileWriter(String.valueOf(  classType + "\\" + name));
            writer.write(gson.toJson(object));
            writer.close();
        }catch (IOException e){
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
            return gson.fromJson(bufferedReader, className);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

}