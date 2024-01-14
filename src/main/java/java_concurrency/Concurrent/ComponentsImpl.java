package java_concurrency.Concurrent;

import java_concurrency.Concurrent.utiltiy.BaledungThreadFactory;
import java_concurrency.Concurrent.utiltiy.Invoker;

import java.util.concurrent.*;
import java.util.concurrent.Future;

/**
 * The java.util.concurrent contains way too many features to discuss in a single write-up.
   In this article, we will mainly focus on some of the most useful utilities from this package like:
 * Executor
 * ExecutorService
 * ScheduledExecutorService
 * Future
 * CountDownLatch
 * CyclicBarrier
 * Semaphore
 * ThreadFactory
 * BlockingQueue  ---> consumer - producer problem explained in queue
 * DelayQueue --> explained in queue
 * Locks --> blocking other threads from accessing certain segment of code.
 * Phaser  --> Phaser is a more flexible solution than CyclicBarrier and CountDownLatch – used to act as a reusable barrier on which the dynamic number of threads need to wait before continuing execution.
 *            We can coordinate multiple phases of execution, reusing a Phaser instance for each program phase.*/
public class ComponentsImpl {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ComponentsImpl.impl();
    }

    private static void impl() throws ExecutionException, InterruptedException, TimeoutException {
        /* Executor -- > interface that represents an object that execute provided tasks */
        Executor executor = new Invoker();
        executor.execute(()->   {
            String substring = "hari".substring(0, 2);
            System.out.println(substring);
        });

        /* ExecutorService -- > ExecutorService is a complete solution for asynchronous processing.

        --------------------------------------------------------------------------------------------
        It manages an in-memory queue and schedules submitted tasks based on thread availability.
        It also comes with two out-of-the-box execution termination methods.
        The first one is shutdown(); it waits until all the submitted tasks finish executing.
        The other method is shutdownNow() which attempts to terminate all actively executing tasks and halts the processing of waiting tasks.
        There is also another method awaitTermination(long timeout, TimeUnit unit) which forcefully blocks until all tasks have completed execution
        after a shutdown event triggered or execution-timeout occurred, or the execution thread itself is interrupted,*/
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        /* execute is used we don't want any result */
        executorService.execute(()-> System.out.println("hari".toUpperCase()));
        /* submit returns a future */
        Future<Boolean> ar = executorService.submit(() -> "hari".contains("ar"));
        executorService.shutdown();
        System.out.println(ar.get());
        /* -------------------------------------------------------------------------------------------- */

        /* Scheduled Executor Service --> perform tasks periodically
        * Zero or any negative value signifies that the request needs to be executed instantly.
        We can use both Runnable and Callable interface to define the task.
        * */
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> schedule = scheduledExecutorService.schedule(() -> {
            return "hero".toUpperCase();
        }, 1, TimeUnit.SECONDS);
        System.out.println(schedule.get());
        scheduledExecutorService.shutdownNow();

        /* -------------------------------------------------------------------------------------------- //
        Future ---> Future is used to return the result of async operation.
        It comes with methods for checking if the asynchronous operation is completed or not, getting the computed result, etc.
        What's more, the cancel(boolean mayInterruptIfRunning) API cancels the operation and releases the executing thread.
        If the value of mayInterruptIfRunning is true, the thread executing the task will be terminated instantly.
        * */

        if(ar.isDone() && !ar.isCancelled()) {
            ar.get();
            /* also can specify timeout */
            ar.get(10,TimeUnit.MILLISECONDS);
        }

        /* --------------------------------------------------------------------------------------------------------------------- */
        /*
        CountDownLatch ---> it is a class that blocks set of threads until some operation completes.
        A CountDownLatch is initialized with a counter(Integer type);
        this counter decrements as the dependent threads complete execution. But once the counter reaches zero, other threads get released.
        * */

        /* --------------------------------------------------------------------------------------------------------------------- */
        /*
        CyclicBarrier ---> CyclicBarrier works almost the same as CountDownLatch except that we can reuse it.
        Unlike CountDownLatch,
        It allows multiple threads to wait for each other using await() method(known as barrier condition) before invoking the final task.

        A cyclic barrier is a synchronization mechanism in concurrent programming that enables a set of threads to wait for each other to reach a particular point of execution before continuing.
        It allows multiple threads to cooperate and synchronize their execution,
        ensuring that certain operations are only performed once all participating threads have reached the synchronization point.
        In a cyclic barrier, each thread waits at a barrier until all the other threads have arrived. Once all the threads have arrived,
        the barrier is released, and all the threads can proceed to execute the next stage of their code. This process can be repeated cyclically as many times as necessary.

        Cyclic barriers are useful in scenarios where multiple threads need to cooperate and coordinate their work.
        For example, in a parallel matrix multiplication algorithm, each thread can calculate a portion of the result matrix independently.
        However, before the threads can proceed to the next stage of the calculation, they must wait for all the other threads to finish their calculations.
        In such cases, a cyclic barrier can be used to synchronize the threads and ensure that all calculations are complete before proceeding.

       Cyclic barriers are typically implemented using a countdown mechanism.
       Each thread that arrives at the barrier decrements a counter, and when the counter reaches zero, the barrier is released.
       The counter is then reset, and the process can be repeated.

       -----------------------------------------------------------------------------------------------------------------------------------------------
       Semaphore -->

       The Semaphore is used for blocking thread level access to some part of the physical or logical resource.
       A semaphore contains a set of permits; whenever a thread tries to enter the critical section,
       it needs to check the semaphore if a permit is available or not.
       If a permit is not available (via tryAcquire()),
       the thread is not allowed to jump into the critical section;
       however, if the permit is available the access is granted, and the permit counter decreases.

       Once the executing thread releases the critical section, again the permit counter increases (done by release() method).
       We can specify a timeout for acquiring access by using the tryAcquire(long timeout, TimeUnit unit) method.
       We can also check the number of available permits or the number of threads waiting to acquire the semaphore.

       */

        Semaphore semaphore = new Semaphore(10);
        System.out.println("available semaphores " + semaphore.availablePermits());
        System.out.println("no of threads waiting to acquire "+ semaphore.getQueueLength());

        if(semaphore.tryAcquire()) {
            System.out.println("acquired");
        } else  {
            semaphore.release();
            System.out.println("released");
        }

        /* ------------------------------------------------------------------------------------------
        *
        * Thread Factory ---> ThreadFactory acts as a thread (non-existing) pool which creates a new thread on demand.
        * It eliminates the need of a lot of boilerplate coding for implementing efficient thread creation mechanisms.
        We can define a ThreadFactory:
        * */

        BaledungThreadFactory baledungThreadFactory = new BaledungThreadFactory("MyThread");
        for (int i=0;i<5;i++ ) {
            Thread thread = baledungThreadFactory.newThread(() -> {
                String res = "hello".toUpperCase();
                System.out.println(res);
            });
            thread.start();
        }
    }
}

