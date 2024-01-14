package java_core_2;

import java.util.ArrayList;

public class ArrayVSArrayList {
    public static void main(String[] args) {
        int[] arr = new int[10];
        System.out.println("size of a array "+arr.length);
        ArrayList<Integer> arrayList = new ArrayList<>(10);
        System.out.println("size of a arraylist "+ arrayList.size());
    }
}
