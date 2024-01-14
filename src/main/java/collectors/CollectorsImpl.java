package collectors;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorsImpl {
    public List<Integer> arr = Arrays.asList(1,2,3,3,5,6);
    public static void main(String[] args) {
        CollectorsImpl.implement();
    }

    private static void implement() {
        CollectorsImpl collectors = new CollectorsImpl();
        LinkedList<Integer> collect = collectors.arr.stream().collect(Collectors.toCollection(LinkedList::new));
        System.out.println(collect);
        HashSet<Integer> collect1 = collectors.arr.stream().collect(Collectors.toCollection(HashSet::new));
        System.out.println(collect1);
        // Collectors.toMap()
        // Need to provide two func - keyMapper and valueMapper and collision mapper
        // we can use lambda function to change key mapper
        Map<Integer, Integer> collect2 = collectors.arr.stream().collect(Collectors.toMap(Function.identity(), Integer::intValue, (item1,item2) -> item1));
        Map<String, Integer> collect3 = collectors.arr.stream().collect(Collectors.toMap(data-> data.toString()+ "key", Integer::intValue, (item1,item2) -> item1));
        System.out.println(collect2);
        System.out.println(collect3);
        // Collectors.collectingAndThen
        Integer collect4 = collectors.arr.stream().collect(Collectors.collectingAndThen(Collectors.toList(), List::size));
        System.out.println(collect4);
        // Summarizing results of stream
        DoubleSummaryStatistics collect5 = collectors.arr.stream().collect(Collectors.summarizingDouble(Integer::intValue));
        System.out.println(collect5);
        // group by
        Map<Integer, List<Integer>> collect6 = collectors.arr.stream().collect(Collectors.groupingBy(d -> d % 2, Collectors.toList()));
        System.out.println(collect6);
        /* PartitioningBy is a specialized case of groupingBy that accepts a Predicate instance, and then collects Stream elements into a Map instance
        that stores Boolean values as keys and collections as values. Under the “true” key,
        we can find a collection of elements matching the given Predicate, and under the “false” key,
        we can find a collection of elements not matching the given Predicate.*/
        Map<Boolean, List<Integer>> collect7 = collectors.arr.stream().collect(Collectors.partitioningBy(d -> d % 2 == 0));
        System.out.println(collect7);
        //teeing --> combining results of two collectors
        Long collect8 = collectors.arr.stream().collect(Collectors.teeing(Collectors.summingInt(Integer::intValue), Collectors.counting(), (first, second) -> first + second));
        System.out.println(collect8);


    }
}
