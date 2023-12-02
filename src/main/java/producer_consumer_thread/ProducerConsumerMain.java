package producer_consumer_thread;

public class ProducerConsumerMain {
    public static void main(String[] args) {
        Mango mango = new Mango();
        Producer producer = new Producer(mango);
        Consumer consumer = new Consumer(mango);
        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(consumer);
        thread1.start();
        thread2.start();
    }
}
