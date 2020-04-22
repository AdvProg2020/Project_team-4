package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class SaveDataAndGetSystemOnBack {
    public static void saveData() {

    }

    public static void writeJSONAccount(SaveAble saveAble) throws IOException {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            FileWriter writer = new FileWriter(saveAble.getName());
            writer.write(gson.toJson(saveAble));
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static SaveAble readJSONAccount(String name) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(name));

            SaveAble saveAble = gson.fromJson(bufferedReader, SaveAble.class);
            return saveAble;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        }
    }


}