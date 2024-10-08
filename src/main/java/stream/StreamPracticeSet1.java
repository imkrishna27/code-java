package stream;

import lombok.ToString;
import stream.model.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

@ToString
public class StreamPracticeSet1 {
    public static void main(String[] args) {
        sortArrayIncreasingAndDecreasingOrder(Arrays.asList(76,3,232,12,3));
        transformArray(Arrays.asList(76,3,232,12,3));
        reduceArray(Arrays.asList(76,3,232,12,3));
        findingMinAndMax(Arrays.asList(76,3,232,12,3));
        skipElements(Arrays.asList(76,3,232,12,3));
        operationsOnObjects();
        findDuplicateElementsAndCreateCountMap("aa","a","cc","a","aa","d","e","a");
        partitionByEventOddCount(1,2,3,4,5,34,2,32,4);
        printDuplicateElements();
    }

    private static void printDuplicateElements() {
        List<Integer> integers = List.of(1, 1, 1, 2, 3);;
        System.out.println(Arrays.toString(integers.stream().filter(e -> Collections.frequency(integers, e) > 1).toArray()));
    }

    private static void partitionByEventOddCount(Integer... data) {
        Map<Boolean, Long> collect = Arrays.stream(data).map(ele -> ele.intValue()).collect(Collectors.partitioningBy(ele -> ele % 2 == 0, Collectors.counting()));
        System.out.println("count of even odd : "+ collect);
    }

    private static void findDuplicateElementsAndCreateCountMap(String... data) {
        Map<String, Long> collect = Arrays.stream(data)
                .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()))
                .entrySet().stream().filter(stringLongEntry -> stringLongEntry.getValue()>1)
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
        System.out.println("duplicate string count : "+ collect);
    }

    private static void operationsOnObjects() {
        Person sara = new Person("Sara", 4,"India");
        Person viktor = new Person("Viktor", 40,"India");
        Person eva = new Person("Eva", 42,"Pakistan");
        List<Person> collection = asList(sara, eva, viktor);
        // find out all names who have age > 4
        List<String> names = collection.stream().filter(p -> p.getAge() > 4).map(Person::getName).collect(Collectors.toList());
        System.out.println("name who have age more than 4 are: "+names);
        // out of names take out all the characters
        List<Character> listOfAllChars2 = names.stream().flatMap(s -> s.chars().mapToObj(ch -> (char) ch)).map(Character::toLowerCase).collect(Collectors.toList());
        System.out.println("list of all chars using chars() " + listOfAllChars2);
    }

    private static void skipElements(List<Integer> asList) {
        // first 2 elements are skipped
        List<Integer> skip = asList.stream().skip(2).collect(Collectors.toList());
        System.out.println("elements with skip order " + skip);
    }

    private static void findingMinAndMax(List<Integer> asList) {
        Optional<Integer> max = asList.stream().max(Comparator.comparing(Integer::intValue, (x, y) -> x.compareTo(y)));
        Optional<Integer> min = asList.stream().min(Comparator.comparing(Integer::intValue, (x, y) -> x.compareTo(y)));
        Optional<Integer> max1 = asList.stream().max(Comparator.naturalOrder());
        Optional<Integer> min1 = asList.stream().min(Comparator.naturalOrder());
        System.out.println("minimum and maximum are "+min.get()+" "+max.get());
        System.out.println("minimum and maximum are using precise lambda statements "+min1.get()+" "+max1.get());
    }

    private static void reduceArray(List<Integer> asList) {
        Optional<Integer> reduce = asList.stream().reduce((x, y) -> x - y);
        System.out.println("reduced array is " +reduce.get());
    }

    private static void transformArray(List<Integer> asList) {
        List<Integer> transformed = asList.stream().map(ele -> ele - 2).collect(Collectors.toList());
        System.out.println("each element subtracted by 2 " +transformed);
        // filter element which are odd
        List<Integer> oddValues = asList.stream().filter(ele -> ele % 2 != 0).collect(Collectors.toList());
        System.out.println("odd values" + oddValues);
        // find only first two odd values
        List<Integer> first1Odd = asList.stream().filter(ele -> ele % 2 != 0).limit(1).collect(Collectors.toList());
        System.out.println("First 1 odd "+ first1Odd);
    }

    private static void sortArrayIncreasingAndDecreasingOrder(List<Integer> asList) {
        // increasing order
        List<Integer> icollect = asList.stream().sorted(Comparator.comparing(Integer::intValue, (x, y) -> x.compareTo(y))).collect(Collectors.toList());
        System.out.println("Stream in increasing order " + icollect);
        // decreasing order
        List<Integer> dcollect = asList.stream().sorted(Comparator.comparing(Integer::intValue, (x, y) -> y.compareTo(x))).collect(Collectors.toList());
        System.out.println("Stream in decreasing order "+ dcollect);
        // other way around can be
        List<Integer> increasingOrder = asList.stream().sorted().collect(Collectors.toList());
        System.out.println("Stream in increasing order using sorted() default behavior "+ increasingOrder);
        List<Integer> decreasingOrder = asList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("Stream in decreasing order using sorted() reverse behavior "+ decreasingOrder);
    }
}
