package producer_consumer_thread;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.PriorityQueue;
import java.util.Random;

@NoArgsConstructor
public class Mango {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
    public synchronized void produceMango() throws InterruptedException {
        while(priorityQueue.size()==10) { wait(); }
        int i = new Random().nextInt(10);
        System.out.println("Value Inserted "+ i);
        priorityQueue.add(i);
        notify();
    }
    public synchronized void consumeMango() throws InterruptedException {
        while (priorityQueue.isEmpty()) { wait(); }
        System.out.println("Value Removed " +priorityQueue.remove());
        notify();
    }
}
