package java_core_1;

public class AtomicIntegerArray {
    private static java.util.concurrent.atomic.AtomicIntegerArray atomicIntegerArray = new java.util.concurrent.atomic.AtomicIntegerArray(5);

    public static void main(String[] args) throws InterruptedException {
        for(int i =0 ;i< atomicIntegerArray.length();i++) {
            atomicIntegerArray.set(i,1);
        }

        Thread t1 = new Thread(new Increment());
        Thread t2 = new Thread(new Compare());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        for (int i = 0; i <atomicIntegerArray.length() ; i++) {
            System.out.print(atomicIntegerArray.get(i)+ " ");

        }
    }

    static class Increment implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < atomicIntegerArray.length(); i++) {
                int add = atomicIntegerArray.incrementAndGet(i);
                System.out.println("Thread " + Thread.currentThread().getId()
                        + ", index " +i + ", value: "+ add);            }
        }
    }

    static class Compare implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i <atomicIntegerArray.length() ; i++) {
                boolean swapped =atomicIntegerArray.compareAndSet(i,2,3);
                if(swapped) {
                    System.out.println("Thread " + Thread.currentThread().getId()
                            + ", index " +i + ", value: 3");
                }
            }
        }
    }

}
