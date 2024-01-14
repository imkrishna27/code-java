package collections.Set;

import java.util.HashSet;

/**
 * It stores unique elements and permits nulls
 * It's backed by a HashMap
 * It doesn't maintain insertion order
 * It's not thread-safe
 * Internally uses HashMap put method to add elements
 * It'd be a good idea to get familiar with the hashcode first to get a detailed understanding of how the elements are organized in hash-based data structures.
 * Summarizing:
 * A HashMap is an array of buckets with a default capacity of 16 elements – each bucket corresponds to a different hashcode value
 * If various objects have the same hashcode value, they get stored in a single bucket
 * If the load factor is reached, a new array gets created twice the size of the previous one and all elements get rehashed and redistributed among new corresponding buckets
 * To retrieve a value, we hash a key, mod it, and then go to a corresponding bucket and search through the potential linked list in case of there's more than a one object
 */
public class HashSetImpl {
    public static void main(String[] args) {
        HashSetImpl.createAndTraverseHashSet();

    }


    private static void createAndTraverseHashSet() {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(1);
        System.out.println(hashSet);
    }

    /*
    *   Performance of HashSet

        The performance of a HashSet is affected mainly by two parameters – its Initial Capacity and the Load Factor.
        The expected time complexity of adding an element to a set is O(1) which can drop to O(n) in the worst case scenario (only one bucket present) – therefore, it's essential to maintain the right HashSet's capacity.
        An important note: since JDK 8, the worst case time complexity is O(log*n).
        The load factor describes what is the maximum fill level, above which, a set will need to be resized.
        We can also create a HashSet with custom values for initial capacity and load factor:

        Set<String> hashset = new HashSet<>();
        Set<String> hashset = new HashSet<>(20);
        Set<String> hashset = new HashSet<>(20, 0.5f);
        *
        In the first case, the default values are used – the initial capacity of 16 and the load factor of 0.75. In the second, we override the default capacity and in the third one, we override both.
        A low initial capacity reduces space complexity but increases the frequency of rehashing which is an expensive process.
        On the other hand, a high initial capacity increases the cost of iteration and the initial memory consumption.

        As a rule of thumb:

        A high initial capacity is good for a large number of entries coupled with little to no iteration
        A low initial capacity is good for few entries with a lot of iteration
        It's, therefore, very important to strike the correct balance between the two. Usually,
        the default implementation is optimized and works just fine, should we feel the need to tune these parameters to suit the requirements, we need to do judiciously.*/
}
