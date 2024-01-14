package collections.Maps;


import java.util.concurrent.ConcurrentSkipListMap;

/**
 * This construct allows us to create thread-safe logic in a lock-free way.
 * It's ideal for problems when we want to make an immutable snapshot of the data while other threads are still inserting data into the map.
 * We will be solving a problem of sorting a stream of events and getting a snapshot of the events that arrived in the last 60 seconds using that construct.
 */
public class ConcurrentSkipListMapImpl {
    public static void main(String[] args) {
        ConcurrentSkipListMapImpl.implement();
    }
    /*
     there's no concurrent implementation of the red-black tree in Java.
     A concurrent variant of SkipLists is implemented in ConcurrentSkipListMap,
     providing an expected average log(n) time cost for the containsKey, get, put and remove operations and their variants.
     In addition to TreeMap‘s features, key insertion, removal, update and access operations are guaranteed with thread-safety.
     */
    private static void implement() {
        ConcurrentSkipListMap<String , Integer> concurrentSkipListMap = new ConcurrentSkipListMap<>();
    }
}
