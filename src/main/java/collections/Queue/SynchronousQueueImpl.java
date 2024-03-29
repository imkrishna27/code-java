package collections.Queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;

public class SynchronousQueueImpl {
    public static void main(String[] args) {
        SynchronousQueueImpl.implement();
    }

    /**
     * The SynchronousQueue only has two supported operations: take() and put(), and both of them are blocking.
     * For example, when we want to add an element to the queue, we need to call the put() method. That method will block until some other thread calls the take() method, signaling that it is ready to take an element.
     * Although the SynchronousQueue has an interface of a queue, we should think about it as an exchange point for a single element between two threads, in which one thread is handing off an element, and another thread is taking that element.
     */
    private static void implement() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();

        Runnable producer = () -> {
            try {
                synchronousQueue.put(ThreadLocalRandom.current().nextInt());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable consumer = () -> {
            try {
                System.out.println("consumed = " + synchronousQueue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        executorService.execute(producer);
        executorService.execute(consumer);
    }
}
