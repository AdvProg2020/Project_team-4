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

}