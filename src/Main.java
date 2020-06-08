public class Main {

    public static void fun1(){
        try {
            System.out.println(1 / 0);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
	fun1();
    }
}
