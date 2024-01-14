package collections.Maps;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * If we want to find a specific element in a list, the time complexity is O(n) and if the list is sorted,
 * it will be O(log n) using, for example, a binary search.
 * The advantage of a HashMap is that the time complexity to insert and retrieve a value is O(1) on average.
 */
public class HashMapImpl {
    public static void main(String[] args) {
        HashMapImpl.differenceBtwHashMapAndHashTable();
    }

    /**
     * Firstly, Hashtable is thread-safe and can be shared between multiple threads in the application.
     * On the other hand, HashMap is not synchronized and can't be accessed by multiple threads without additional synchronization code.
     * We can use Collections.synchronizedMap() to make a thread-safe version of a HashMap.
     * We can also just create custom lock code or make the code thread-safe by using the synchronized keyword.
     * HashMap is not synchronized, therefore it's faster and uses less memory than Hashtable.
     * Generally, unsynchronized objects are faster than synchronized ones in a single threaded application.
     * HashMap allows null keys while HashTable doesn't
     */
    private static void differenceBtwHashMapAndHashTable() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("hari","pass");
        hashMap.put("krishna","fail");
        for (Map.Entry<String, String> stringStringEntry : hashMap.entrySet()) {
            System.out.println(stringStringEntry);
        }
        /* one more way of iterating.
        productsByName.forEach( (key, product) -> {
             System.out.println("Key: " + key + " Product:" + product.getDescription());
            //do something with the key and value
        });
        */
        /*
        But there are some differences too:

        HashMap doesn't provide any Enumeration, while Hashtable provides not fail-fast Enumeration
        Hashtable doesn't allow null keys and null values, while HashMap do allow one null key and any number of null values
        Hashtable‘s methods are synchronized while HashMaps‘s methods are not
        */
        Hashtable<String,String> hashtable = new Hashtable<>(); //deprecated
        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>(); // same as hash table thread safe
    }
}
