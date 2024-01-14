package java_core_2;

interface AInt {
    default void foo() {
        System.out.println("In A");
    }
}

interface BInt {
    default void foo() {
        System.out.println("In B");
    }
}
public class ImplementMultipleInterfaces implements AInt,BInt{

    public static void main(String[] args) {
        new ImplementMultipleInterfaces().foo();
    }

    @Override
    public void foo() {
        BInt.super.foo();
    }
}
