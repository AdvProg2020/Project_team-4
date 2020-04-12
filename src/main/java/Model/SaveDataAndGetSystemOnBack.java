package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class SaveDataAndGetSystemOnBack {
    public void saveData() {

    }
    private void writeJSONAccount(Account account) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(account.getUserName());
        writer.write(gson.toJson(account));
        writer.close();
    }

    private Account readJSONAccount() throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("student.json"));

        Account account = gson.fromJson(bufferedReader, Account.class);
        return account;
    }
}
