package concurrent;

import java.io.InputStreamReader;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecuterMain implements Runnable {
    volatile int i = 0;
    @Override
    public void run() {
        while(i<10) {
            System.out.print(i+" ");
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new ExecuterMain());
        System.out.println("task submitted");
        while(!executorService.awaitTermination(1000, TimeUnit.MICROSECONDS)) {
            executorService.shutdownNow();
        }
        System.out.println("\ntask completed");
    }
}