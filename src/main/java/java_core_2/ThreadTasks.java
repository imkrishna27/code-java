package java_core_2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class ThreadTasks {
    public static void main(String[] args) throws InterruptedException {
        ThreadTasks threadTasks = new ThreadTasks();
        // create 2 threads
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // make list of callable tasks
        Collection<Callable<Integer>> tasks = new ArrayList<>(10);
        tasks.add(()-> threadTasks.sum(1,10));
        tasks.add(()-> threadTasks.sum(11,20));
        // submit all tasks to threads
        List<Future<Integer>> futures = executorService.invokeAll(tasks);
        // it will return list of future
        futures.forEach((sum)-> {
            try {
                System.out.println(sum.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            } finally {
                executorService.shutdown();
            }
        });
    }

    int sum(int x,int y) {
        int sum =0;
        for(int i=x;i<=y;i++) {
            sum+=i;
        }
        return sum;
    }
}

// class implements callable
class SumTask implements Callable<Integer> {
    // data members
    private final int x, y;
    // constructor
    SumTask(int x,int y) {
        this.x=x;
        this.y=y;
    }
    // sum method that will return sum of range
    public int sum(int x,int y) {
        int ans = 0;
        for(int i=x;i<=y;i++) {
            ans+=i;
        }
        return ans;
    }
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        // get x and y as a data member of SubTask
        return sum(this.x,this.y);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SumTask sumTask1 = new SumTask(1, 10);
        SumTask sumTask2 = new SumTask(11,20);
        // create future tasks for callable Tasks so that we can pass it to thread as a runnable task
        FutureTask<Integer> futureTask1 = new FutureTask<>(sumTask1);
        FutureTask<Integer> futureTask2 = new FutureTask<>(sumTask2);
        // create thread instance
        Thread thread1 = new Thread(futureTask1);
        Thread thread2 = new Thread(futureTask2);
        // start thread
        thread1.start();
        thread2.start();
        // print output
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
        // interrupt thread
        thread1.interrupt();
        thread2.interrupt();

    }
}
