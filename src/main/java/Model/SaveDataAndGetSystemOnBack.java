package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class SaveDataAndGetSystemOnBack {
    public static void saveData() {

    }
    public static void writeJSONAccount(Account account) throws IOException {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            FileWriter writer = new FileWriter(account.getUserName());
            writer.write(gson.toJson(account));
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static Account readJSONAccount(String name) throws FileNotFoundException {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(name));

            Account account = gson.fromJson(bufferedReader, Account.class);
            return account;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        }
    }

    public static void writeJSONBuyLog(BuyLog buyLog) throws IOException {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            FileWriter writer = new FileWriter(buyLog.getLogBarcode());
            writer.write(gson.toJson(buyLog));
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static BuyLog readJSONBuyLog(String name) throws FileNotFoundException {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(name));

            BuyLog buyLog = gson.fromJson(bufferedReader, BuyLog.class);
            return buyLog;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        }
    }

    public static void writeJSONCategory(Category category) throws IOException {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            FileWriter writer = new FileWriter(category.getName());
            writer.write(gson.toJson(category));
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }

    public static Category readJSONCategory(String name) throws FileNotFoundException {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(name));

            Category category = gson.fromJson(bufferedReader, Category.class);
            return category;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        }

    }

    public static void writeJSONCodedOff(CodedOff codedOff) throws IOException {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            FileWriter writer = new FileWriter(codedOff.getOffBarcode());
            writer.write(gson.toJson(codedOff));
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }

    public static CodedOff readJSONCodedOff(String name) throws FileNotFoundException {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(name));

            CodedOff codedOff = gson.fromJson(bufferedReader, CodedOff.class);
            return codedOff;
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found");
            return null;
        }

    }

    public static void writeJSONComment(Comment comment) throws IOException {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            FileWriter writer = new FileWriter(comment.getCommentBarcode());
            writer.write(gson.toJson(comment));
            writer.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found");
        }

    }

    public static Comment readJSONComment(String name) throws FileNotFoundException {
        try {

        }

    }

    public static void writeJSONCustomer(Customer customer) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(customer.getUserName());
        writer.write(gson.toJson(customer));
        writer.close();
    }

    public static Customer readJSONCustomer(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("student.json"));

        Customer customer = gson.fromJson(bufferedReader, Customer.class);
        return customer;
    }

    public static void writeJSONManager(Account account) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(account.getUserName());
        writer.write(gson.toJson(account));
        writer.close();
    }

    public static Account readJSONManager(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        Account account = gson.fromJson(bufferedReader, Account.class);
        return account;
    }

    public static void writeJSONOff(Account account) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(account.getUserName());
        writer.write(gson.toJson(account));
        writer.close();
    }

    public static Account readJSONOff(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        Account account = gson.fromJson(bufferedReader, Account.class);
        return account;
    }

    public static void writeJSONProduct(Account account) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(account.getUserName());
        writer.write(gson.toJson(account));
        writer.close();
    }

    public static Account readJSONProduct(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        Account account = gson.fromJson(bufferedReader, Account.class);
        return account;
    }

    public static void writeJSONRequest(Account account) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(account.getUserName());
        writer.write(gson.toJson(account));
        writer.close();
    }

    public static Account readJSONRequest(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        Account account = gson.fromJson(bufferedReader, Account.class);
        return account;
    }

    public static void writeJSONRequestANewSellerAccount(Account account) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(account.getUserName());
        writer.write(gson.toJson(account));
        writer.close();
    }

    public static Account readJSONRequestANewSellerAccount(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        Account account = gson.fromJson(bufferedReader, Account.class);
        return account;
    }

    public static void writeJSONScore(Account account) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(account.getUserName());
        writer.write(gson.toJson(account));
        writer.close();
    }

    public static Account readJSONScore(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        Account account = gson.fromJson(bufferedReader, Account.class);
        return account;
    }

    public static void writeJSONSeller(Account account) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(account.getUserName());
        writer.write(gson.toJson(account));
        writer.close();
    }

    public static Account readJSONSeller(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new  BufferedReader(
                new FileReader(name));

        Account account = gson.fromJson(bufferedReader, Account.class);
        return account;
    }

    public static void writeJSONSellLog(Account account) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(account.getUserName());
        writer.write(gson.toJson(account));
        writer.close();
    }

    public static Account readJSONSellLog(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        Account account = gson.fromJson(bufferedReader, Account.class);
        return account;
    }



}
