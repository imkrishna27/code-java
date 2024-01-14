package collections.Queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * BlockingQueue Types
 * We can distinguish two types of BlockingQueue:

 * unbounded queue – can grow almost indefinitely
 * bounded queue – with maximal capacity defined
 */
public class BlockingQueueImpl {
    public static void main(String[] args) {
        BlockingQueueImpl.implement();
    }

    /**
     * .1. Adding Elements
     * add() – returns true if insertion was successful, otherwise throws an IllegalStateException
     * put() – inserts the specified element into a queue, waiting for a free slot if necessary
     * offer() – returns true if insertion was successful, otherwise false
     * offer(E e, long timeout, TimeUnit unit) – tries to insert element into a queue and waits for an available slot within a specified timeout
     * 3.2. Retrieving Elements
     * take() – waits for a head element of a queue and removes it. If the queue is empty, it blocks and waits for an element to become available
     * poll(long timeout, TimeUnit unit) – retrieves and removes the head of the queue, waiting up to the specified wait time if necessary for an element to become available. Returns null after a timeout
     * These methods are the most important building blocks from BlockingQueue interface when building producer-consumer programs.
     */
    private static void implement() {
        int BOUND = 10;
        int N_PRODUCERS = 4;
        int N_CONSUMERS = Runtime.getRuntime().availableProcessors();
        int poisonPill = Integer.MAX_VALUE;
        int poisonPillPerProducer = N_CONSUMERS / N_PRODUCERS;
        int mod = N_CONSUMERS % N_PRODUCERS;

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(BOUND);

        for (int i = 1; i < N_PRODUCERS; i++) {
            new Thread(new NumbersProducer(queue, poisonPill, poisonPillPerProducer)).start();
        }

        for (int j = 0; j < N_CONSUMERS; j++) {
            new Thread(new NumbersConsumer(queue, poisonPill)).start();
        }

    }

    public static class NumbersProducer implements Runnable {
        private BlockingQueue<Integer> numbersQueue;
        private final int poisonPill;
        private final int poisonPillPerProducer;

        public NumbersProducer(BlockingQueue<Integer> numbersQueue, int poisonPill, int poisonPillPerProducer) {
            this.numbersQueue = numbersQueue;
            this.poisonPill = poisonPill;
            this.poisonPillPerProducer = poisonPillPerProducer;
        }
        public void run() {
            try {
                generateNumbers();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void generateNumbers() throws InterruptedException {
            for (int i = 0; i < 100; i++) {
                int data = ThreadLocalRandom.current().nextInt(100);
                System.out.println("produced = " + data);
                Thread.sleep(2000);
                numbersQueue.put(data);
            }
            for (int j = 0; j < poisonPillPerProducer; j++) {
                numbersQueue.put(poisonPill);
            }
        }
    }

    public static class NumbersConsumer implements Runnable {
        private BlockingQueue<Integer> queue;
        private final int poisonPill;

        public NumbersConsumer(BlockingQueue<Integer> queue, int poisonPill) {
            this.queue = queue;
            this.poisonPill = poisonPill;
        }
        public void run() {
            try {
                while (true) {
                    Integer number = queue.take();
                    if (number.equals(poisonPill)) {
                        return;
                    }
                    System.out.println(Thread.currentThread().getName() + " consumed: " + number);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
