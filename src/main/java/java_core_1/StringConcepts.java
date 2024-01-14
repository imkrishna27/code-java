package java_core_1;

public class StringConcepts {
    public static void main(String[] args) {
        // Mutable String
        StringBuilder stringBuilder = new StringBuilder("Hello");
        System.out.println(stringBuilder.hashCode());
        stringBuilder.append("ooo");
        System.out.println(stringBuilder.hashCode());
        // Immutable String
        String s = new String("Krishna");
        System.out.println(s.hashCode());
        String s1 = "Krishna";
        System.out.println(s1.hashCode());
        System.out.println(s==s1); // == checks reference
    }
}