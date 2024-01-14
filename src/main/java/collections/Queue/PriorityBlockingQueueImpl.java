package collections.Queue;

import java.util.Arrays;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PriorityBlockingQueueImpl {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueueImpl.implement();
    }

    /**
     * @throws InterruptedException
     * If we were dealing with a standard queue, we would call poll() to retrieve elements.
     * However, if the queue was empty, a call to poll() would return null.
     * The PriorityBlockingQueue implements the BlockingQueue interface,
     * which gives us some extra methods that allow us to block when removing from an empty queue.
     * Let's try using the take() method, which should do exactly that:
     */
    private static void implement() throws InterruptedException {
        PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();
        Thread thread = new Thread(() -> {
            System.out.println("Polling ...");
            while(true) {
                try {
                    Integer poll = priorityBlockingQueue.take();
                    System.out.println("Polled " + poll);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        System.out.println("Adding element to queue");
        priorityBlockingQueue.addAll(Arrays.asList(3,333,434,343,4423,2,4,5));
        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
    }
}
