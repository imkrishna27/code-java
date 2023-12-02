package producer_consumer_thread;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Producer implements Runnable {
    Mango mango;
    Producer(Mango mango) {
       this.mango = mango;
    }

    @Override
    public void run() {
        try {
            while(true) {
                this.mango.produceMango();
                Thread.sleep(1000);
            }
            } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
