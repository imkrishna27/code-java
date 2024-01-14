package java_core_2.NestedClass;

abstract class A{
    abstract void print();
}
public class AnanymousInnerClassUsingAbstractClass {
     final int a=10;
    public static void main(String[] args) {
        A a =new A() {
            @Override
            void print() {
                // cant directly access outer class
                System.out.println("Inside Abstract Print: "+ new AnanymousInnerClassUsingAbstractClass().a);
            }
        };
        a.print(); // only one object is created
    }
}
