package static_keyword.other;

// static methods cant be override
class Parent {
    public static void display() {
        System.out.println("In parent");
    }

    public void block() {
        System.out.println("In parent block");
    }
}
class Child extends Parent {
    public static void display() {
        System.out.println("in child");
    }

    public void block() {
        System.out.println("In child block");
    }
}
public class StaticOverrideUtil {
    public static void main(String[] args) {
        // As per overriding rules this
        // should call to class Child static
        // overridden method. Since static
        // method can not be overridden, it
        // calls Parent's display()
        Parent p = new Child();
        p.display(); // in parent
        p.block(); // in child block
    }
}
