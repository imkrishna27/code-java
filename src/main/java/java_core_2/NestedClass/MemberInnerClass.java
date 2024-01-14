package java_core_2.NestedClass;

public class MemberInnerClass {
    // can access private fields also
    private int a = 10;

    class MemberInnerClassInner {
        void print() {
            System.out.println("Value of a is: "+a);
        }
    }

    public static void main(String[] args) {
        // instantiate a inner member class
        MemberInnerClassInner memberInnerClassInner = new MemberInnerClass().new MemberInnerClassInner();
        memberInnerClassInner.print();
    }
}
