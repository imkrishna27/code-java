package concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecuterServiceMain implements Runnable {
    volatile int i = 0;
    @Override
    public void run() {
        while(i<10) {
            System.out.print(i+" ");
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // it will have one schedule method
        // it will execute after one minute
        scheduledExecutorService.schedule(new ScheduledExecuterServiceMain(),
                1,
                TimeUnit.MINUTES
        );
        while(!scheduledExecutorService.awaitTermination(1,TimeUnit.MINUTES)) {
            scheduledExecutorService.shutdownNow();
        }
    }
}
