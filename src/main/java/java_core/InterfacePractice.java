package java_core;

public interface InterfacePractice {
    // interface variables are final and static by default
    // interface data members and methods are public by default
    int a=1;
    int b=2;
}

abstract class AbstractPractice {
    // can be non-static and non-final
    // can be private as well as protected
    int c = 2;
}
class ImplementInterface  extends AbstractPractice implements InterfacePractice{
    public static void main(String[] args) {
        ImplementInterface implementInterface = new ImplementInterface();
        int dummy = implementInterface.c;
        System.out.println(dummy);
    }
}