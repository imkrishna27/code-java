package java_core_2;

class StaticClass {
    // we want to use functionality off static class we can't do it directly, so we have to declare a inner static class
    static class InnerStaticClass {
        int a =20;

        int getA() {
            return a;
        }
    }
}

class Implementation {
    public static void main(String[] args) {
        StaticClass.InnerStaticClass innerStaticClass = new StaticClass.InnerStaticClass();
        System.out.println(innerStaticClass.getA());
    }
}
