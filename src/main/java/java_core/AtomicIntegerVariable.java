package java_core;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerVariable {
    static class Counter {
        private ThreadLocal<Integer> c= new ThreadLocal<>() {
            @Override
            protected Integer initialValue() {
                return 0;
            }
        };

        public void increment() {
            c.set(c.get()+1);
        }

        public int getValue() {
            return c.get();
        }

        public static void main(String[] args) throws InterruptedException {
            Counter c = new Counter();

            for(int i=0;i<10;i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        c.increment();
                        System.out.println("Element we got is " + c.getValue());
                    }
                }).start();
            }
        }
    }
}
