package singerstone.com.superapp.javassist;

public class Demo2 {

    public static void main(String[] args) {
//        System.out.println();
//        Any any = new Any();
//        any.show();
//        System.out.println("my age is : " + any.getAge());

        MathOperation addition = (int a, int b) -> a + b;
        System.out.println("10 + 5 = " + exe(10, 5, addition));
    }

    private static int exe(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }

    interface MathOperation {
        int operation(int a, int b);
    }
}