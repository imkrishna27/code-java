package java_concurrency.Concurrent;

import java.util.concurrent.*;
import java.util.concurrent.Future;

public class ThreadPoolImpl {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolImpl.impl();
    }

    /**
     *  For better understanding go to thread-life-cycle.md and see image.
     *  Executors, Executor and ExecutorService --> Used for creating thread pool
     */
    private static void impl() throws ExecutionException, InterruptedException {
        // Using Executor --> have one execute method.
        Executor executor1 = Executors.newSingleThreadExecutor();
        executor1.execute(()-> System.out.println("hello"));
        // Using Executors Service --> returns Future
        // Runnable single method does not throw an exception and does not return a value.
        // The Callable interface may be more convenient, as it allows us to throw an exception and return a value.
        // Finally, to let the compiler infer the Callable type, simply return a value from the lambda.
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> submit = executorService.submit(() -> { return 10;});
        System.out.println(submit.get());
        // Thread Pool Executors
        // The main configuration parameters that we'll discuss here are corePoolSize, maximumPoolSize and keepAliveTime.
        // The pool consists of a fixed number of core threads that are kept inside all the time. It also consists of some excessive threads that may be spawned and then terminated when they are no longer needed.
        // The corePoolSize parameter is the number of core threads that will be instantiated and kept in the pool.
        // When a new task comes in, if all core threads are busy and the internal queue is full, the pool is allowed to grow up to maximumPoolSize.
        // The keepAliveTime parameter is the interval of time for which the excessive threads (instantiated in excess of the corePoolSize) are allowed to exist in the idle state.
        // By default, the ThreadPoolExecutor only considers non-core threads for removal. In order to apply the same removal policy to core threads, we can use the allowCoreThreadTimeOut(true) method.
        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        // assertEquals(2, executor.getPoolSize()); --> task pool size currently running by 2 threads
        // assertEquals(1, executor.getQueue().size()); --> no of tasks in waiting stage

        /* Executors.newCachedThreadPool() --> do not require no of thread count , corePoolSize is 0 and maxPoolSize us Integer.MAX_VALUE */
        ThreadPoolExecutor executor2 =
                (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor2.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor2.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor2.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        //assertEquals(3, executor.getPoolSize()); task pool size currently running by core threads
        //assertEquals(0, executor.getQueue().size()); no of tasks in waiting stage

        /* ScheduledExecutorService */
        ScheduledExecutorService executor3 = Executors.newScheduledThreadPool(5);
        executor3.schedule(() -> {
            System.out.println("Hello World");
        }, 500, TimeUnit.MILLISECONDS);

    }
}
