package java_core_2;

class A {
    public void fun() {
        System.out.println("in A");
    }
}

class B extends A {
    public void fun() {
        System.out.println("in B");
    }
}

class C extends A {
    public void fun() {
        System.out.println("in C");
    }
}
public class MethodOverride {
    public static void main(String[] args) {
        A a = new A();
        a.fun();
        A a1 = new B();
        a1.fun();
        A a2 = new C();
        a2.fun();
    }
}



