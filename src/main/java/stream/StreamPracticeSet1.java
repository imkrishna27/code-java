package stream;

import stream.model.Person;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class StreamPracticeSet1 {
    public static void main(String[] args) {
        sortArrayIncreasingAndDecreasingOrder(Arrays.asList(76,3,232,12,3));
        transformArray(Arrays.asList(76,3,232,12,3));
        reduceArray(Arrays.asList(76,3,232,12,3));
        findingMinAndMax(Arrays.asList(76,3,232,12,3));
        skipElements(Arrays.asList(76,3,232,12,3));
        operationsOnObjects();
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
        List<char[]> listOfAllChars = names.stream().flatMap(name -> Arrays.stream(name.split(","))).map(ch -> ch.toCharArray()).collect(Collectors.toList());
        System.out.println("list of all characters are" + listOfAllChars);
    }

    private static void skipElements(List<Integer> asList) {
        // first 2 elements are skipped
        List<Integer> skip = asList.stream().skip(2).collect(Collectors.toList());
        System.out.println("elements with skip order " + skip);
    }

    private static void findingMinAndMax(List<Integer> asList) {
        Optional<Integer> max = asList.stream().max(Comparator.comparing(Integer::intValue, (x, y) -> x.compareTo(y)));
        Optional<Integer> min = asList.stream().min(Comparator.comparing(Integer::intValue, (x, y) -> x.compareTo(y)));
        System.out.println("minimum and maximum are "+min+" "+max);
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
    }
}
