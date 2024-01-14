package collections.Arrays;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * Random access takes O(1) time
 * Adding element takes amortized constant time O(1)
 * Inserting/Deleting takes O(n) time
 * Searching takes O(n) time for unsorted array and O(log n) for a sorted one
 * Syntax to declare multi-dimensional lists --> ArrayList<ArrayList<String>> =new ArrayList();
 */
public class ArrayListImpl {
    public static void main(String[] args) {
        ArrayListImpl primaryClass = new ArrayListImpl();
        primaryClass.createAndGetArray();
        primaryClass.usingStreams();
        primaryClass.unmutableArrays();
        primaryClass.synchronizedArrayList();
    }

    private void createAndGetArray() {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("a", "b", "c"));
        System.out.println(arrayList);

    }

    private void usingStreams() {
        ArrayList<Integer> test1 = IntStream.range(0, 100).boxed().collect(Collectors.toCollection(ArrayList::new));
        System.out.println(test1);
        // Filter on basis of collections of elements
        List<Integer> collect = test1.stream().filter(Arrays.asList(1,2,3)::contains).collect(Collectors.toList());
        System.out.println(collect);
    }

    private void unmutableArrays() {
        List<String> strings = Collections.unmodifiableList(List.of("1"));;
    }
    /*
    * Collections.synchronizedList
    * Collections.copyOnWriteArrayList
    * both are thread safe
    * diff is SL block other threads if some operations are performing on lists while COWAL doesn't.
    * */
    private void synchronizedArrayList() {
        /* for add or remove we don't need explicit synchronization */
        /* for fetching we need explicit synchronization */
        /* this is thread safe
        * Iterator.forEachRemaining()
        * */
        List<String> strings = Collections.synchronizedList(new ArrayList<>());
        strings.add("hari");
        synchronized (strings) {
            Iterator<String> it = strings.iterator();
            it.forEachRemaining(System.out::println);
        }
        /* All operations are synchronized -- thread safe */
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<String>();
        copyOnWriteArrayList.add("1");
        copyOnWriteArrayList.forEach(System.out::println);
    }
     private void multiDimensionalList() {
        ArrayList<ArrayList<ArrayList<String>>> listArrayList = new ArrayList<>();
     }
}
