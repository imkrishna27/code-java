package java_core;

//
public class StaticBlockAndInitializerBlockExample {
    StaticBlockAndInitializerBlockExample() {
        this(1,10);
        System.out.println("Inside the default constructor");
    }
    StaticBlockAndInitializerBlockExample(int a, int b) {
        System.out.println(a +"  "+ b);
    }
    static {
        System.out.println("Inside static block");
    }
    {
        System.out.println("Inside Instantiation Block");
    }
    public static void main(String[] args) {
        {
            StaticBlockAndInitializerBlockExample staticClassExample = new StaticBlockAndInitializerBlockExample();
        }

    }
}
