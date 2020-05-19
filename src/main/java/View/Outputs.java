package View;

import Model.Customer;
import Model.Product;


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

    public static void printOffsListResult(ArrayList<String> resultOff) {
        for (String product : resultOff) {
            System.out.println(product);
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
        if(result == 0){
            System.out.println("product not found!");
        }else if(result == 1) {
            System.out.println("Successfully added.");
        }else if(result == 2){
            System.out.println("just customers can buy product!");
        }else if(result == 5){
            System.out.println("Oops product doesn't exist now!");
        }
    }

    public static void printRemoveProductResult(boolean result){
        if(result)
            System.out.println("Successfully removed.");
        else
            System.err.println("Product not found!");
    }

    public static void printDeletingAccountResult(int controllerDeleteAnUserResult) {
        if(controllerDeleteAnUserResult == 1){
            System.out.println("account removed successfully");
        }else{
            System.out.println("no account found with this name");
        }
    }

    public static void printReadFileResult(String result){
        System.out.println(result);
    }

    public static boolean printCreateCodedOffResult(int controllerCreateOffCode) {
        switch (controllerCreateOffCode){
            case 1:
                System.out.println("codedoff create successfully");
                return true;
            case 2:
                System.out.println("expire date should be after start date");
                return false;
            case 3:
                System.out.println("please enter valid date");
                return false;
        }
        return false;
    }

    public static void printPayResult(boolean payedOrNot){
        if (payedOrNot) {
            System.out.println("Payed successfully");
        } else {
            System.out.println("sth went wrong in paying.");
        }
    }

    public static void printNewCommentResult(int result){
        if(result == 1)
            System.out.println("Successfully registered.");
        else
            System.err.println("Something went wrong!");
    }

    public static void printAttributeResult(Product product){
        System.out.println(product);
    }

    public static void printIncreaseOrDecreaseResult(int increaseOrDecreaseProductNo) {
        if(increaseOrDecreaseProductNo == 1){
            System.out.println("well done!");
        }else if(increaseOrDecreaseProductNo == 2){
            System.out.println("product removed successfully!");
        }else if(increaseOrDecreaseProductNo == 3){
            System.out.println("This barcode is none of your selected products in cart");
        }else if(increaseOrDecreaseProductNo == 4){
            System.out.println("this product in not availAble any more");
        }
    }
}

