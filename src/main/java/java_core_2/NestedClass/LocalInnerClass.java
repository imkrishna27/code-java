package java_core_2.NestedClass;

public class LocalInnerClass {
    int a =10;

    void print() {
        class B {
            void printB() {
                System.out.println("Inside B "+ a);
            }
        }
        new B().printB();
    }

    public static void main(String[] args) {
        new LocalInnerClass().print();
    }
}
