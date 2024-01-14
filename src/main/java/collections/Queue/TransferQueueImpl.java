package collections.Queue;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TransferQueueImpl {
    public static void main(String[] args) throws InterruptedException {
        TransferQueueImpl.implement();
    }

    /**
     * The implementation is actually similar to the BlockingQueue – but gives us the new ability to implement a form of backpressure. This means that,
     * when the producer sends a message to the consumer using the transfer() method, the producer will stay blocked until the message is consumed.
     */
    private static void implement() throws InterruptedException {
        TransferQueue<Integer> transferQueue = new LinkedTransferQueue<>();
        ExecutorService exService = Executors.newFixedThreadPool(2);
        Producer producer = new Producer(transferQueue,"1",3);
        Consumer consumer = new Consumer(transferQueue,"1",3);
        // when
        exService.execute(producer);
        exService.execute(consumer);

        // then
        exService.awaitTermination(5000, TimeUnit.MILLISECONDS);
        exService.shutdown();


    }

    private static class Producer implements Runnable {
        private final TransferQueue<Integer> transferQueue;
        private final String name;
        private final Integer numOfMessageToProduce;

        private AtomicInteger numberOfProducedMessages = new AtomicInteger();
        public Producer(TransferQueue<Integer> transferQueue, String name, Integer numOfMessageToProduce) {
            this.name = name;
            this.transferQueue = transferQueue;
            this.numOfMessageToProduce = numOfMessageToProduce;
        }

        @Override
        public void run() {
            for (int i=0; i< numOfMessageToProduce;i++) {
                try {
                    System.out.println("Trying to Transfer");
                    if(transferQueue.tryTransfer(i,4000, TimeUnit.MILLISECONDS)) {
                        System.out.println("Transferred = " + i);
                        numberOfProducedMessages.incrementAndGet();
                    };
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    private static class Consumer implements Runnable {
        private final TransferQueue<Integer> transferQueue;
        private final String name;
        private final Integer numberOfRecordsToConsume;

        public AtomicInteger numberOfConsumedMessages
                = new AtomicInteger();

        private Consumer(TransferQueue<Integer> transferQueue, String name, Integer numberOfRecordsToConsume) {
            this.transferQueue = transferQueue;
            this.name = name;
            this.numberOfRecordsToConsume = numberOfRecordsToConsume;
        }

        @Override
        public void run() {
            for (int i = 0; i < numberOfRecordsToConsume; i++) {
                System.out.println("Trying to consume");
                try {
                    Integer element = transferQueue.take();
                    longProcessing(element);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void longProcessing(Integer element)
                throws InterruptedException {
            numberOfConsumedMessages.incrementAndGet();
            System.out.println("Consumed = " + element );
            Thread.sleep(500);
        }
    }
}
