package java_concurrency.Concurrent;

import java_concurrency.Concurrent.utiltiy.SumTask;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * To provide effective parallel execution, the fork/join framework uses a pool of threads called the ForkJoinPool.
 * This pool manages worker threads of type ForkJoinWorkerThread.
 */
public class ForkJoinImpl {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinImpl.impl();
    }

    /**
     *
     ForkJoinPool doesn’t create a separate thread for every single subtask.
     Instead, each thread in the pool has its own double-ended queue (or deque, pronounced “deck”) that stores tasks.
     This architecture is vital for balancing the thread’s workload with the help of the work-stealing algorithm.
     */
    private static void impl() throws ExecutionException, InterruptedException {
        /*  ForkJoinTask<V> ---->
            ForkJoinTask is the base type for tasks executed inside ForkJoinPool. In practice, one of its two subclasses should be extended:
            the RecursiveAction for void tasks and the RecursiveTask<V> for tasks that return a value.
            They both have an abstract method compute() in which the task’s logic is defined.
            https://www.baeldung.com/java-fork-join
            */

        int[] array = new int[5];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(array, 0, array.length);
        long result = pool.invoke(task);
        System.out.println("Result: " + result);

        /* Custom thread pool to execute streams */
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        List<Integer> collect = IntStream.range(0, 1000).boxed().collect(Collectors.toList());
        ForkJoinTask<Integer> submit = forkJoinPool.submit(() -> collect.parallelStream().reduce(0, Integer::sum));
        System.out.println(submit.get());
        forkJoinPool.shutdown();
        pool.shutdown();

    }
}

/* Work - Stealing Algorithm
    Simply put, free threads try to “steal” work from dequeue of busy threads.
    By default, a worker thread gets tasks from the head of its own deque.
    When it is empty, the thread takes a task from the tail of the deque of another busy thread or
    from the global entry queue since this is where the biggest pieces of work are likely to be located.
    This approach minimizes the possibility that threads will compete for tasks. It also reduces the number of times the thread
    will have to go looking for work, as it works on the biggest available chunks of work first.
* */


