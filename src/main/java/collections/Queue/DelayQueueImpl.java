package collections.Queue;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueImpl {
    public static void main(String[] args) {
        DelayQueueImpl.implement();
    }

    /**
     * when the consumer wants to take an element from the queue, they can take it only when the delay for that particular element has expired.
     * When the consumer tries to take an element from the queue, the DelayQueue will execute getDelay() to find out if that element is allowed to be returned from the queue.
     * If the getDelay() method will return zero or a negative number, it means that it could be retrieved from the queue.
     * We also need to implement the compareTo() method, because the elements in the DelayQueue will be sorted according to the expiration time.
     * The item that will expire first is kept at the head of the queue and the element with the highest expiration time is kept at the tail of the queue:
     */
    private static void implement() {
        BlockingQueue<DelayObject> queue = new DelayQueue<>();
        DelayObject object
                = new DelayObject(
                UUID.randomUUID().toString(), 10000);
        System.out.println("Put object: " + object);
        try {
            queue.put(object);
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

    }

    public static class DelayObject implements Delayed {
        private final String data;
        private final long startTime;

        public DelayObject(String data, long delayInMilliseconds) {
            this.data = data;
            this.startTime = System.currentTimeMillis() + delayInMilliseconds;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = startTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }
}
