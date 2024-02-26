package lambda_expressions;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaExpressionsMain {
    public static void main(String[] args) {
        // create a function interface it takes one param and return some value
        Function<Integer,Integer> function = (x) -> x*x;
        Integer apply = function.apply(4);
        System.out.println(apply);
        // create a supplier interface it takes no arg and return some value
        Supplier<Integer> runnable = () -> 2*2;
        System.out.println(runnable.get());
        // create a consumer interface that takes an arg and returns no value
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("hello java..");
        // represents boolean-valued func it takes an arg and returns boolean value
        Predicate<Integer> isEven = x-> x%2==0;
        System.out.println(isEven.test(7));
    }
}
