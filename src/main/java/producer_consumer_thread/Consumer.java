package producer_consumer_thread;

public class Consumer implements Runnable {
    Mango mango;
    Consumer(Mango mango) {
        this.mango = mango;
    }
    @Override
    public void run() {
        try {
            while (true) {
                this.mango.consumeMango();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
