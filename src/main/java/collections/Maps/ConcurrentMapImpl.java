package collections.Maps;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentMap is an extension of the Map interface.
 * It aims to provide a structure and guidance to solving the problem of reconciling throughput with thread-safety.
 * By overriding several interface default methods,
 * ConcurrentMap gives guidelines for valid implementations to provide thread-safety and memory-consistent atomic operations.

 * Several default implementations are overridden, disabling the null key/value support:

 * getOrDefault
 * forEach
 * replaceAll
 * computeIfAbsent
 * computeIfPresent
 * compute
 * merge
 * The following APIs are also overridden to support atomicity, without a default interface implementation:

 * putIfAbsent
 * remove
 * replace(key, oldValue, newValue)
 * replace(key, value)
 * The rest of actions are directly inherited with basically consistent with Map.
 */
public class ConcurrentMapImpl {
    public static void main(String[] args) {
        ConcurrentMapImpl.implement();
    }

    private static void implement() {
        /* Thread Safe , Default method can be overridden  */
        /* Now implementing concurrent hash map */
        /* Its implementation of concurrentMap */
        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<String,String>(100,0.5f,4);
        concurrentHashMap.put("hari","krishna");
        System.out.println(concurrentHashMap);


    }
}
