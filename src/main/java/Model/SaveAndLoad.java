package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class SaveAndLoad {

    private static SaveAndLoad saveAndLoad = new SaveAndLoad();

    private SaveAndLoad() { }

    public static SaveAndLoad getSaveAndLoad() {
        return saveAndLoad;
    }

    public void writeJSONAccount(SaveAble saveAble){
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            FileWriter writer = new FileWriter(saveAble.getName());
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
            System.out.println("File not found");
            return null;
        }
    }


}