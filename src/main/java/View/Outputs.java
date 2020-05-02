package View;

import Model.Customer;

import java.util.ArrayList;

public class Outputs {

    public static boolean printCreateAccountResult(int result) {
        switch (result) {
            case 1:
                System.out.println("Account created successfully");
                return true;
            case 2:
                System.out.println("request for being seller sent!");
                return true;
            case 3:
                System.err.println("only manager can create manager Account");
                return false;
            case 4:
                System.err.println("please Enter another username this name exist!");
                return false;
            case 5:
                System.err.println("type should be customer or seller or manager");
                return false;
        }
        return false;
    }

    public static boolean printLoginResult(int result) {
        switch (result) {
            case 1:
                System.out.println("successfully logged in!");
                return true;
            case 2:
                System.out.println("username not found please try again!");
                return false;
            case 3:
                System.out.println("invalid password! please try again!");
                return false;
            case 4:
                System.out.println("one account is now logged in please logout first");
                return false;
        }
        return false;
    }

    public static void printLogoutResult(int result) {
        switch (result) {
            case 1:
                System.out.println("you should login first!");
                return;
            case 2:
                System.out.println("logged out successfully!");
        }
    }

    public static void printOffsListResult(ArrayList resultOff) {
        for (Object off : resultOff) {
            System.out.println(off);
        }
    }

    public static void printPersonalInfoResult(Customer customer) {
        System.out.println(customer);
    }

    public static void printProductBuyersResult(ArrayList result) {
//        for (Object object : resultAccount) {
//            System.out.println(object);
//        }
    }

    public static void printShowProductResult(String result){
        System.out.println(result);
    }

    public static void printAddToCartResult(int result){
        switch(result){
            case 1:
            case 2:
        }
    }
}

