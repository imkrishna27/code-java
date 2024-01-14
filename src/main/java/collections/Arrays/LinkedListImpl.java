package collections.Arrays;

import java.util.LinkedList;

public class LinkedListImpl {
    public void createAndGetLL() {
        LinkedList<Integer> newLinkedList = new LinkedList<>();
        newLinkedList.add(12);
        newLinkedList.add(13);
        newLinkedList.add(14);
        newLinkedList.forEach(System.out::println);
    }

    public static void main(String[] args) {
        LinkedListImpl primaryClass = new LinkedListImpl();
        primaryClass.createAndGetLL();
    }
}
