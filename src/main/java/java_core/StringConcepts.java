package java_core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

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
        ArrayList<Integer> arrayList = new ArrayList<>();
       int a =10;
        String.valueOf(a);
    }
}