package java_core;

class CovarientReturnType {
    CovarientReturnType() {
        System.out.println("CovarientReturnType Constructor");
    }
    CovarientReturnType get() {
        return this;
    }
    void message() {
        System.out.println("Inside Co-Varient Type");
    }
}
class  Test extends CovarientReturnType {
    CovarientReturnType get() {
        return this;
    }
    void message() {
        System.out.println("Inside Test ype");
    }

    public static void main(String[] args) {
        new Test().get().message();
    }
}