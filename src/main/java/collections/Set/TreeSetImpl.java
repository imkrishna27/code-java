package collections.Set;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * It stores unique elements
 * It doesn't preserve the insertion order of the elements
 * It sorts the elements in ascending order
 * It's not thread-safe
 * In this implementation, objects are sorted and stored in ascending order according to their natural order.
 * The TreeSet uses a self-balancing binary search tree, more specifically a Red-Black tree.
 * When compared to a HashSet the performance of a TreeSet is on the lower side.
 * Operations like add, remove and search take O(log n) time while operations like printing n elements in sorted order require O(n) time.
 * A TreeSet should be our primary choice if we want to keep our entries sorted as a TreeSet may be accessed and traversed in either ascending or descending order,
 * and the performance of ascending operations and views is likely to be faster than that of descending ones.
 * The Principle of Locality – is a term for the phenomenon in which the same values,
 * or related storage locations, are frequently accessed, depending on the memory access pattern.
 * When we say locality:
 *  1.Similar data is often accessed by an application with similar frequency
 *  2.If two entries are nearby given an ordering, a TreeSet places them near each other in the data structure, and hence in memory
 */
public class TreeSetImpl {
    public static void main(String[] args) {
        TreeSetImpl treeSet = new TreeSetImpl();
        treeSet.createAndTraverseTreeSet();
    }

    private void createAndTraverseTreeSet() {
        TreeSet<String> treeSet = new TreeSet<>();
        /* Define order in which elements are sorted */
        TreeSet<String> treeSet1 = new TreeSet<>(Comparator.comparing(String::length));
        /* To Make it thread safe */
        Set<String> strings = Collections.synchronizedSet(treeSet1);
        treeSet.add("hari");
        treeSet.add("krishna");
        treeSet.add("aditya");
        /* this method will return elements of TreeSet which are smaller than the specified element: */
        System.out.println(treeSet.headSet("hari"));
        /* this method will return elements of TreeSet which are larger or equal than the specified element: */
        System.out.println(treeSet.tailSet("hari"));
        System.out.println(treeSet);
    }
}
