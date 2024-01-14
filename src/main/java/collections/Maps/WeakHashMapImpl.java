package collections.Maps;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * An entry in a WeakHashMap will automatically be removed when its key is no longer in ordinary use,
 * meaning that there is no single Reference that point to that key.
 * When the garbage collection (GC) process discards a key, its entry is effectively removed from the map,
 * so this class behaves somewhat differently from other Map implementation
 */
public class WeakHashMapImpl {
    public static void main(String[] args) {
        WeakHashMapImpl.implement();
        WeakHashMapImpl.callGCForWeakMapReference();
    }

    private static void callGCForWeakMapReference() {
        /* call GC to clear memory when reference no longer exist */

//        WeakHashMap<UniqueImageName, BigImage> map = new WeakHashMap<>();
//        BigImage bigImage = new BigImage("image_id");
//        UniqueImageName imageName = new UniqueImageName("name_of_big_image");
//
//        map.put(imageName, bigImage);
//        assertTrue(map.containsKey(imageName));
//
//        imageName = null;
//        System.gc();
//
//        await().atMost(10, TimeUnit.SECONDS).until(map::isEmpty);
    }

    private static void implement() {
        WeakHashMap<String,String> weakHashMap = new WeakHashMap<>();
        weakHashMap.put("animal","lion");
        for(Map.Entry<String,String> entry: weakHashMap.entrySet()) {
            System.out.println(entry);
        }
    }
}
