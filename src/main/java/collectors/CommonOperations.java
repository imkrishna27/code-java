package collectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonOperations {
    public static void main(String[] args) {
        CommonOperations.implement();
    }

    private static void implement() {
        List<Optional<Integer>> optionals = List.of(Optional.of(1),Optional.empty(),
                Optional.of(2), Optional.of(3), Optional.of(4),Optional.empty());

        // Filtering optional values using filter and map
        System.out.println(optionals.stream().filter(Optional::isPresent).map(Optional::get)
                .collect(Collectors.toList()));
        // Using flat map
        System.out.println(optionals.stream().flatMap(d-> d.isPresent() ? Stream.of(d.get()) : Stream.empty()).collect(Collectors.toList()));
        // Java 9 optional Stream
        System.out.println(optionals.stream().flatMap(Optional::stream).collect(Collectors.toList()));
        // map vs flatmap

        // map() method is used to apply a given function to each element of a Stream and returns a new Stream consisting of the results
        List<String> words = Arrays.asList("hello world", "java programming", "stream api");
        List<String> upperCaseWords = words.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(upperCaseWords);
        /*  return type --> (Optional.of(Optional.of("STRING")) === Optional.of("string").map(s -> Optional.of("STRING"))); */
        /* List<String> myList = Stream.of("a", "b").map(String::toUpperCase).collect(Collectors.toList()); assertEquals(asList("A", "B"), myList);*/

        //flatMap() method is used to transform each element of a Stream into a Stream of zero or more elements,
        // and then flatten the resulting Streams into a single Stream.

        List<String> phrases = Arrays.asList("hello world", "java programming", "stream api");
        List<String> words1 = phrases.stream().flatMap(str -> Arrays.stream(str.split(" "))).collect(Collectors.toList());
        System.out.println(words1);

        /*  return type --> (Optional.of("STRING") === Optional.of("string").flatmap(s -> Optional.of("STRING"))); */
        /*  list =[[a],[b],[c]] --> System.out.println(list.stream().flatMap(Collection::stream).collect(Collectors.toList()));*/


        /* ----------------------------------------------------------------------------------------------------------------------- */

        /* distinct by */
        ArrayList<Human> objects = new ArrayList<>(5);
        objects.add(new Human("hari",10));
        objects.add(new Human("krishna",12));
        objects.add(new Human("mohit",13));
        objects.add(new Human("mohit",14));

        List<Human> collect = objects.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);


    }

}
