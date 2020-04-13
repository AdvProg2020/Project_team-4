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

    private Account readJSONAccount(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        Account account = gson.fromJson(bufferedReader, Account.class);
        return account;
    }

    private void writeJSONBuyLog(BuyLog buyLog) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(buyLog.getLogBarcode());
        writer.write(gson.toJson(buyLog));
        writer.close();
    }

    private BuyLog readJSONBuyLog(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        BuyLog buyLog = gson.fromJson(bufferedReader, BuyLog.class);
        return buyLog;
    }

    private void writeJSONCategory(Category category) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(category.getName());
        writer.write(gson.toJson(category));
        writer.close();
    }

    private Category readJSONCategory(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        Category category = gson.fromJson(bufferedReader, Category.class);
        return category;
    }

    private void writeJSONCodedOff(CodedOff codedOff) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(codedOff.getOffBarcode());
        writer.write(gson.toJson(codedOff));
        writer.close();
    }

    private CodedOff readJSONCodedOff(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        CodedOff codedOff = gson.fromJson(bufferedReader, CodedOff.class);
        return codedOff;
    }

    private void writeJSONComment(Comment comment) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(comment.getCommentBarcode());
        writer.write(gson.toJson(comment));
        writer.close();
    }

    private Comment readJSONComment(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        Comment comment = gson.fromJson(bufferedReader, Comment.class);
        return comment;
    }

    private void writeJSONCustomer(Customer customer) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(customer.getUserName());
        writer.write(gson.toJson(customer));
        writer.close();
    }

    private Customer readJSONCustomer(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("student.json"));

        Customer customer = gson.fromJson(bufferedReader, Customer.class);
        return customer;
    }

    private void writeJSONManager(Account account) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(account.getUserName());
        writer.write(gson.toJson(account));
        writer.close();
    }

    private Account readJSONManager(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        Account account = gson.fromJson(bufferedReader, Account.class);
        return account;
    }

    private void writeJSONOff(Account account) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(account.getUserName());
        writer.write(gson.toJson(account));
        writer.close();
    }

    private Account readJSONOff(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        Account account = gson.fromJson(bufferedReader, Account.class);
        return account;
    }

    private void writeJSONProduct(Account account) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(account.getUserName());
        writer.write(gson.toJson(account));
        writer.close();
    }

    private Account readJSONProduct(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        Account account = gson.fromJson(bufferedReader, Account.class);
        return account;
    }

    private void writeJSONRequest(Account account) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(account.getUserName());
        writer.write(gson.toJson(account));
        writer.close();
    }

    private Account readJSONRequest(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        Account account = gson.fromJson(bufferedReader, Account.class);
        return account;
    }

    private void writeJSONRequestANewSellerAccount(Account account) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(account.getUserName());
        writer.write(gson.toJson(account));
        writer.close();
    }

    private Account readJSONRequestANewSellerAccount(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        Account account = gson.fromJson(bufferedReader, Account.class);
        return account;
    }

    private void writeJSONScore(Account account) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(account.getUserName());
        writer.write(gson.toJson(account));
        writer.close();
    }

    private Account readJSONScore(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        Account account = gson.fromJson(bufferedReader, Account.class);
        return account;
    }

    private void writeJSONSeller(Account account) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(account.getUserName());
        writer.write(gson.toJson(account));
        writer.close();
    }

    private Account readJSONSeller(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        Account account = gson.fromJson(bufferedReader, Account.class);
        return account;
    }

    private void writeJSONSellLog(Account account) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(account.getUserName());
        writer.write(gson.toJson(account));
        writer.close();
    }

    private Account readJSONSellLog(String name) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(name));

        Account account = gson.fromJson(bufferedReader, Account.class);
        return account;
    }



}
