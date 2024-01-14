package collections.Maps;

import collections.Enums.Weekdays;

import java.util.EnumMap;
import java.util.Map;

public class EnumMapImpl {
    public static void main(String[] args) {
        EnumMapImpl.implement();
        
    }

    /**
     * Using Enum as key makes it possible to do some extra performance optimization,
     * like a quicker hash computation since all possible keys are known in advance.
     * The simplicity of having enum as key means EnumMap only need to be backed up by a plain old Java Array with very simple logic for storage and retrieval.
     * On the other hand, generic Map implementations need to cater for concerns related to having a generic object as its key.
     * For example, HashMap needs a complex data structure and a considerably more complex storing and retrieval logic to cater for the possibility of hash collision.
     */
    private static void implement() {
        EnumMap<Weekdays,String> enumMap = new EnumMap<>(Weekdays.class);
        enumMap.put(Weekdays.Monday,"Cricket");
        for(Map.Entry<Weekdays,String> map : enumMap.entrySet()) {
            System.out.println(map
            );
        }
    }
}
