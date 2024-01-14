package java_concurrency.AdvanceConcurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class CompletableFutureImpl {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFutureImpl.impl();
    }

    /**
     * In Java, a CompletableFuture is a class that represents a future result of an asynchronous computation. It is part of the java.util.concurrent package and was introduced in Java 8.
     * A CompletableFuture can be completed either synchronously or asynchronously, and it can also depend on other CompletableFutures to complete.
     * It supports a wide variety of operations such as combining multiple CompletableFutures, chaining operations, and handling errors.
     */
    private static void impl() throws ExecutionException, InterruptedException {
        System.out.println(calculateAsync().get());
        // CompletableFuture with Encapsulated Computation Logic
        // Static methods runAsync and supplyAsync allow us to create a CompletableFuture instance out of Runnable and Supplier functional types correspondingly.
        //  Runnable and Supplier are functional interfaces that allow passing their instances as lambda expressions thanks to the new Java 8 feature.
        //  The Runnable interface is the same old interface used in threads and does not allow to return a value.
        //  The Supplier interface is a generic functional interface with a single method that has no arguments and returns a value of a parameterized type.
        //  This allows us to provide an instance of the Supplier as a lambda expression that does the calculation and returns the result. It is as simple as:
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()-> "hello" );
        // Now if we perform some operation on completable future
        CompletableFuture<String> completableFutureFinal = completableFuture.thenApply(data -> data + " hari".toUpperCase());
        System.out.println(completableFutureFinal.get());
        test_code_with_computation_of_future();
        combining_futures();

    }

    private static void combining_futures() throws ExecutionException, InterruptedException {
        // combine futures to make chain
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "hello").thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " good night"));
        System.out.println(completableFuture.get());
        // then combine --> like flatmap
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> 10 * 10).thenCombine(CompletableFuture.supplyAsync(() -> 5 * 5), (res1, res2) -> res1 + res2);
        System.out.println(integerCompletableFuture.get());
        // Diff between thenApply and thenCompose
        // We can use this method to work with the result of the previous call. However, a key point to remember is that the return type will be combined of all calls.
        // So this method is useful when we want to transform the result of a CompletableFuture call:
        CompletableFuture<Integer> finalResult = CompletableFuture.completedFuture(10).thenApply(s-> s + 1);
        System.out.println(finalResult.get());
        // The thenCompose() is similar to thenApply() in that both return a new CompletionStage. However, thenCompose() uses the previous stage as the argument.
        // It will flatten and return a Future with the result directly, rather than a nested future as we observed in thenApply():

    }

    private static void test_code_with_computation_of_future() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello");
        // returns void future at the end of computation
        CompletableFuture<Void> future = completableFuture
                .thenAccept(s -> System.out.println("Computation returned: " + s));
        System.out.println(future.get());

        // if we neither want computation nor a return value
        CompletableFuture<Void> accepted = completableFuture.thenRun(() -> System.out.println("accepted"));
        System.out.println(accepted.get());
    }

    private static CompletableFuture<String> calculateAsync() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        Executors.newCachedThreadPool().submit(()-> {
            try {
                Thread.sleep(1000);
                completableFuture.complete("Hari Krishna");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        return completableFuture;
    }
}
