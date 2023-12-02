package normal_tests;

public class ThreadImpl {
    int start;
    int end;
    static volatile int sum = 0;

    ThreadImpl(int start,int end) {
        this.start= start;
        this.end = end;
    }
    Runnable runnable1 = () -> {
        synchronized (this) {
            for(int i=start;i<=end;i++) {
                sum+=i;
            }
        }
    };
}

class ThreadMain {
    static ThreadImpl t1 = new ThreadImpl(1,100);
    static ThreadImpl t2 = new ThreadImpl(101,6000);
    static ThreadImpl t3 = new ThreadImpl(6001,10000);
    static Thread thread1 = new Thread(t1.runnable1);
    static Thread thread2 = new Thread(t2.runnable1);
    static Thread thread3 = new Thread(t3.runnable1);

    public static void main(String[] args) {
        thread1.start();
        thread2.start();
        thread3.start();
        while(thread1.isAlive() || thread2.isAlive() || thread3.isAlive()) {
            System.out.println("Thread is calculating sum...");
        }
        System.out.println("Calculated sum is... "+ ThreadImpl.sum);
    }
}
