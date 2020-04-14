package View;

<<<<<<< HEAD
import Control.Controller;

=======
>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb
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

<<<<<<< HEAD
    public static void printDigestResult(String result){
        System.out.println(result);
    }

    public static void printAddProductResult(int result){
        switch (result) {
            case 0:
            case 1:
        }

    }
=======



>>>>>>> 1215782625c187da6d753a32c21bccbe93b052eb

}

