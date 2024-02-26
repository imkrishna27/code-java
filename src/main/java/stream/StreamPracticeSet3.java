package stream;

import java.util.*;
import java.util.stream.*;

public class StreamPracticeSet3 {
    public static void main(String[] args) {
        StreamPracticeSet3.creationOfStream();
        /*
        Java 8 offers the possibility to create streams out of three primitive types: int, long and double.
        As Stream<T> is a generic interface, and there is no way to use primitives as a type parameter with generics,
        three new special interfaces were created: IntStream, LongStream, DoubleStream.
        * */
        StreamPracticeSet3.primitiveStream();
        StreamPracticeSet3.stringStreamsAndMore();
        StreamPracticeSet3.streamPipeline();
        /* Two ways of reducing stream  -- collect() or reduce() */
        StreamPracticeSet3.reduceStreamUsingReduce();
        /* Now Reducing Stream Using Collect method */
        StreamPracticeSet3.reduceStreamUsingCollect();
        StreamPracticeSet3.parallelStremas();
        StreamPracticeSet3.flatMap2D();
    }

    private static void flatMap2D() {
        ArrayList<ArrayList<Integer>> twoD = new ArrayList<>();
        twoD.add(new ArrayList<Integer>(Arrays.asList(1,12,4,5)));
        twoD.add(new ArrayList<Integer>(Arrays.asList(18,17,64,95)));

        List<Integer> collect = twoD.stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println(collect);
    }

    private static void parallelStremas() {
        List<Integer> integers = Arrays.asList(2, 3, 4, 5);
        List<Integer> collect = integers.parallelStream().map(d -> d * 3).collect(Collectors.toList());
        System.out.println(collect);
        // If the source of a stream is something other than a Collection or an array, the parallel() method should be used.
    }

    private static void reduceStreamUsingCollect() {
        List<String> strings = Arrays.asList("a", "b", "c");
        List<Integer> integers = Arrays.asList(33,44,5,6,3,12,55,6,67,55);
        System.out.println(strings.stream().collect(Collectors.toList()));
        // Reducing to a string
        System.out.println("joining : " + strings.stream().collect(Collectors.joining(",","`","`")));
        /* Collectors has many operation where we can reduce our array to take sum, avg and statistical info */
        System.out.println(integers.stream().collect(Collectors.partitioningBy(d-> d>15)));
        /* Pushing collector to perform additional information */
        Set<Integer> collect = integers.stream().collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
        System.out.println(collect);


    }

    private static void reduceStreamUsingReduce() {

        /* reduce takes 3 things */
        /* 1 .identity – the initial value for an accumulator, or a default value if a stream is empty and there is nothing to accumulate
           2.accumulator – a function which specifies the logic of the aggregation of elements.
           As the accumulator creates a new value for every step of reducing,
           the quantity of new values equals the stream's size and only the last value is useful. This is not very good for the performance.

           3.combiner – a function which aggregates the results of the accumulator.
           We only call combiner in a parallel mode to reduce the results of accumulators from different thread
        * */

        Integer reduce1 = Arrays.asList(1, 2, 3).stream().reduce(10, Integer::sum); //sequential
        /* The result here is different (36), and the combiner was called twice.
        Here the reduction works by the following algorithm:
        the accumulator ran three times by adding every element of the stream to identity. T
        hese actions are being done in parallel. As a result, they have (10 + 1 = 11; 10 + 2 = 12; 10 + 3 = 13;). Now combiner can merge these three results. It needs two iterations for that (12 + 13 = 25; 25 + 11 = 36). */
        Integer reduce2 = Arrays.asList(1, 2, 3).parallelStream().reduce(10, Integer::sum); //parallel
        System.out.println(reduce1+ "  " +reduce2);
        /* if you want to achieve same result you have to make your operations associative */
    }

    /* stream pipeline, which is a chain of the stream source, intermediate operations, and a terminal operation:*/
    private static void streamPipeline() {
        /* We can only use one terminal operation per stream. */
        List<String> collect = Stream.of("hari", "krishna", "singh").skip(1).map(d -> d.substring(0, 3)).sorted().collect(Collectors.toList());
        List<Character> collect1 = collect.stream().flatMap(ele -> ele.chars().mapToObj(c -> (char) c)).collect(Collectors.toList());
        System.out.println(collect1);
        System.out.println(collect);
        /* What we can do is to create an array , so you can have multiple stream pipeline */
        List<String> arr = Arrays.asList("a","b","c");
        System.out.println(arr.stream().skip(2).map(d-> d + "ty").collect(Collectors.toList()));
        System.out.println(arr.stream().skip(1).map(d-> d + "ty").collect(Collectors.toList()));
        /* In Java Streams, a terminal operation (also known as a terminal condition or a stream terminal method) is an operation that
        triggers the processing of the stream and produces a non-stream result, such as a collection, a value, or an I/O operation.
        Terminal operations are the final step in a stream pipeline, after one or more intermediate operations have been applied to the stream.
        They cause the intermediate operations to be executed and produce a result that is not a stream.

        Some examples of terminal operations in Java Streams include
        forEach(), count(), toArray(), reduce(), collect(), min(), max(), findFirst(), findAny(), and anyMatch().

        It's important to note that once a terminal operation is applied to a stream, the stream cannot be reused.
        A new stream must be created if you want to apply another operation.*/
    }

    private static void stringStreamsAndMore() {
        System.out.println("hari".chars().mapToObj(d-> (char) d).collect(Collectors.toList()));
        /* referencing a stream */
        Stream<String> stringStream = Stream.of("1", "2", "4").filter(d -> d.contains("4"));
        Optional<String> max = stringStream.max(String::compareTo);
        /* You can not use same reference of stream it will give an error */
//        Optional<String> any = stringStream.findAny();
//        max.ifPresent(System.out::println);
//        any.ifPresent(System.out::println);
        /* workaround */
        List<String> collect = Stream.of("1", "2", "4").collect(Collectors.toList());
        System.out.println(collect.stream().findAny().get());
        System.out.println(collect.stream().max(String::compareTo).get());

    }

    private static void primitiveStream() {
        System.out.println(IntStream.range(1,12).boxed().collect(Collectors.toList()));
        System.out.println(LongStream.rangeClosed(1,12).boxed().collect(Collectors.toList()));
        System.out.println(DoubleStream.iterate(42,n-> n+3).limit(5).boxed().collect(Collectors.toList()));
    }

    private static void creationOfStream() {
        /* Creating an empty stream */
        Stream<Object> empty = Stream.empty();
        System.out.println(empty);
        /* Creating Stream via Array*/
        System.out.println(Arrays.asList("a","b").stream());
        /* Creating Stream using of operator */
        System.out.println(Stream.of("a","b","c"));
        /* Creating stream using stream builder */
        System.out.println(Stream.<String>builder().add("a").add("b").add("c").build());
        /* Creating Stream using Stream generate method
        * The Supplier functional interface is used in many places throughout Java's functional programming API,
        * including Stream.generate(), Stream.ofNullable(), and Optional.orElseGet().
        * It is also commonly used in lambda expressions and method references to provide a source of dynamically generated data.

        For example, you might use a Supplier to generate a stream of random numbers:
        Stream<Integer> stream = Stream.generate(() -> (int) (Math.random() * 100)).limit(10);
        Or, you might use a Supplier to provide a default value for an optional:
        Optional<String> optional = Optional.ofNullable(null);
        String value = optional.orElseGet(() -> "default value");
        In this example, we create an Optional that is null, and then use the orElseGet() method to provide a default value using a Supplier.
        * If the Optional is null, the orElseGet() method will call the Supplier to generate a default value.*/
        System.out.println(Stream.generate(()-> Math.random()*0.5).limit(10).collect(Collectors.toList()));
        /* generate using stream.iterate() */
        System.out.println(Stream.iterate(20,n -> n=n+2).limit(10).collect(Collectors.toList()));
    }
}

