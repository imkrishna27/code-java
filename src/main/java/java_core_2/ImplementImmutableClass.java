package java_core_2;

import java.util.Arrays;

public final class ImplementImmutableClass {
    private final int arr[];
    public ImplementImmutableClass(int arr[]) {
        this.arr = arr;
    }
    // returning clone of data member
    public int[] getArr() {
        return this.arr.clone();
    }

    public static void main(String[] args) {
        ImplementImmutableClass implementImmutableClass = new ImplementImmutableClass(new int[]{1, 2, 4, 5});
        System.out.println(Arrays.toString(implementImmutableClass.getArr()));
    }
}
