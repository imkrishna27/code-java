package java_concurrency.Concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future.impl();
    }

    /**
     *  Future class represents a future result of an asynchronous computation.
     *  This result will eventually appear in the Future after the processing is complete.
     *  Future --> get(),isDone(),cancel()
     *  cancel() --> when we don't care about future.
     */
    private static void impl() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        java.util.concurrent.Future<Integer> result = executorService.submit(() -> {
            Thread.sleep(1000);
            return calculate();
        });
        // result.get()
        result.cancel(true);
        System.out.println(result.isCancelled());
        executorService.shutdown();
    }

    private static <T> int calculate() {
        return 10*10;
    }
}
