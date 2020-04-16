package View;

import Model.Customer;

import java.util.ArrayList;

public class Outputs {

    public static void printCreateAccountResult(int result) {
        switch (result) {
            case 1:
            case 2:
            case 3:
            default:
        }
    }

    public static void printLoginResult(int result) {
        switch (result) {
            case 1:
            case 2:
            case 3:
            default:
        }
    }

    public static void printOffsListResult(ArrayList resultOff) {
        for (Object off : resultOff) {
            System.out.println(off);
        }
    }

    public static void printLogoutResult(int result) {
        switch (result) {
            case 1:
            case 2:
        }
    }

    public static void printPersonalInfoResult(Customer customer) {
        System.out.println(customer);
    }

    public static void printProductBuyersResult(ArrayList result) {
        for (Object object : resultAccount) {
            System.out.println(object);
        }
    }

    public static void printShowProductResult(String result){
        System.out.println(result);
    }


}

