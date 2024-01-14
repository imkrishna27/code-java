package stream;

import stream.model.Person;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;


public class StreamPracticeSet2 {

    public static void main(String[] args) {
        flat2DArray();
        List<Integer> integers = asList(1, 2, 4, 5, 6, 7);
        // if we want to calculate sum of a stream
        Integer reduce = integers.parallelStream().reduce(0, (integer1, integer2) -> integer1+integer2);
        System.out.println("reduced stream " + reduce);
        // Comparator takes two arg 1.key Extractor 2.key comparator
        Optional<Integer> max = integers.stream().max(Comparator.comparing(Integer::intValue,(a,b)->{
            return b.compareTo(a);
        }));
        // can be shorthand to Comparator.comparing(Integer::valueOf)

        System.out.println(max.orElse(-1));
        System.out.println("greater than 5 " + getTotalNumberOfLettersOfNamesLongerThanFive("hello","sachin","tendulkar"));
        System.out.println(transform(asList(asList("Viktor", "Farcic"), asList("John", "Doe", "Third"))));
        Person sara = new Person("Sara", 4,"India");
        Person viktor = new Person("Viktor", 40,"India");
        Person eva = new Person("Eva", 42,"Paksitan");
        List<Person> collection = asList(sara, eva, viktor);
        System.out.println(getOldestPerson(collection).getAge());
        System.out.println(calculate(asList(1, 2, 3, 4, 5)));
        System.out.println(getKidNames(collection));
        System.out.println(partitionAdults(collection));
        System.out.println(groupByNationality(collection));
        System.out.println(namesToString(collection));

    }

    private static void flat2DArray() {
        ArrayList<ArrayList<Integer>> arraysArrays = new ArrayList<>();
        arraysArrays.add(new ArrayList<>(asList(1,2,4)));
        arraysArrays.add(new ArrayList<>(asList(7,277,44)));
        arraysArrays.add(new ArrayList<>(asList(1,2,43)));

        List<Integer> collect = arraysArrays.
                stream()
                .flatMap(List::stream).filter(ele -> ele > 10)
                .collect(Collectors.toList());
        System.out.println("2D array after flatmap "+collect);
    }

    public static int getTotalNumberOfLettersOfNamesLongerThanFive(String... names) {
        long count = Arrays.stream(names)
                .filter(n -> n.length() > 5).flatMap(s -> s.chars().mapToObj(c -> (char) c)).count();
        return (int) count;
    }
    public static List<String> transform(List<List<String>> collection) {
        return collection.stream().flatMap(List::stream).collect(Collectors.toList());
    }
    public static Person getOldestPerson(List<Person> people) {
        return people.stream().max(Comparator.comparing(Person::getAge)).get();
    }

    public static int calculate(List<Integer> numbers) {
        return numbers.stream().reduce(0, Integer::sum);
    }
    public static Set<String> getKidNames(List<Person> people) {
        return people.stream().filter(person -> person.getAge()>18).map(Person::getName).collect(Collectors.toSet());
    }
    public static Map<Boolean, List<Person>> partitionAdults(List<Person> people) {
       return people.stream().filter(person -> person.getAge() > 18).collect(Collectors.partitioningBy(age -> age.getAge() > 18));
    }
    public static Map<String, List<Person>> groupByNationality(List<Person> people) {
        return people.stream().collect(Collectors.groupingBy(Person::getNationality));
    }
    public static String namesToString(List<Person> people) {
        return people.stream().map(Person::getName).collect(Collectors.joining(",","Names: ","."));
    }



}
