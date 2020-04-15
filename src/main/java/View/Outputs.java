package View;

import java.util.ArrayList;

public class Outputs {
    public static void printCreateAccountResult(int result){
        switch(result){
            case 1:
            case 2:
            case 3:
            default:
        }
    }

    public static void printLoginResult(int result){
        switch(result){
            case 1:
            case 2:
            case 3:
            default:
        }
    }

    public static void printOffsListResult(ArrayList result){
        if(result.equals(null)){
            System.err.println("No off exist right now.");;
        }
        else{
            for (Object off : result) {
                System.out.println(off);
            }
        }
    }

    public static void printLogoutResult(int result){
        switch(result){
            case 1:
            case 2:
        }
    }



}

