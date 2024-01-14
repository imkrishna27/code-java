package java_core_2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class WaysOfCreatingThreads  extends  Thread{
    @Override
    public void run() {
        System.out.println("Thread is running");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        WaysOfCreatingThreads waysOfCreatingThreads = new WaysOfCreatingThreads();
        CreateThreadUsingRunnable createThreadUsingRunnable = new CreateThreadUsingRunnable();
        CreateThreadUsingCallable createThreadUsingCallable = new CreateThreadUsingCallable();
        waysOfCreatingThreads.start();
        // need to explicitly pass object of thread implementation for runnable and callable
        Thread runnableThread = new Thread(createThreadUsingRunnable);
        runnableThread.start();
        // we have to store callable task to future, and then we can pass it to Thread() as a runnable
        FutureTask<String> futureTask = new FutureTask<String>(createThreadUsingCallable);
        // passing here
        Thread callableThread = new Thread(futureTask);
        callableThread.start();
        System.out.println(futureTask.get());

        runnableThread.interrupt();
        callableThread.interrupt();
        waysOfCreatingThreads.interrupt();
    }
}
 class CreateThreadUsingRunnable implements Runnable {
    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        System.out.println("Thread running using Runnable");
    }
}

class CreateThreadUsingCallable implements Callable<String> {
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        return "hello "+ "krishna";
    }
}