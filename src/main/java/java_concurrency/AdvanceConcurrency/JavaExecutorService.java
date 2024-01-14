package java_concurrency.AdvanceConcurrency;

import java.util.concurrent.*;

public class JavaExecutorService {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        JavaExecutorService.impl();
    }

    static Runnable runnableTask = () -> {
        try {
            Thread.sleep(1000);
            System.out.println("hari");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    static Callable<String> callableTask = () -> {
        try {
            Thread.sleep(1000);
            return "Hari".toUpperCase();
        } catch (InterruptedException e)  {
            e.printStackTrace();
        }
        return "couldn't perform tasks";
    };

    private static void impl() throws ExecutionException, InterruptedException {
        try {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
            threadPoolExecutor.submit(runnableTask);
            Future<String> callableFuture = threadPoolExecutor.submit(callableTask);
            System.out.println(callableFuture.get());
            threadPoolExecutor.shutdown();
        } catch (InterruptedException | ExecutionException ie) {
           ie.printStackTrace();
        }
    }
}
