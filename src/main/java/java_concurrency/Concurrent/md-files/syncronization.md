### wait(), notify() , and notifyall()
wait(), notify(), and notifyAll() are methods in Java that are used for inter-thread communication and synchronization.

wait() method is used to make the current thread wait until another thread invokes the notify() or notifyAll() method for the same object. 
When a thread calls the wait() method, it releases the lock on the object and waits until another thread notifies it to wake up.

    public class SharedResource {
    private int sharedData;
    private boolean dataAvailable = false;

    public synchronized void write(int data) {
        while (dataAvailable) {
            try {
                wait(); // wait until the consumer has read the previous data
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sharedData = data;
        dataAvailable = true;
        notify(); // notify the consumer that new data is available
    }

    public synchronized int read() {
        while (!dataAvailable) {
            try {
                wait(); // wait until the producer has written new data
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int data = sharedData;
        dataAvailable = false;
        notify(); // notify the producer that the previous data has been read
        return data;
    }
    }

#### Difference between wait() and Sleep() :

Simply put, wait() is an instance method that's used for thread synchronization.

It can be called on any object, as it's defined right on java.lang.Object, but it can only be called from a synchronized block. 
It releases the lock on the object so that another thread can jump in and acquire a lock.

On the other hand, Thread.sleep() is a static method that can be called from any context. Thread.sleep() pauses the current thread and does not release any locks.

    private static Object LOCK = new Object();
    private static void sleepWaitExamples()
    throws InterruptedException {

    Thread.sleep(1000);
    System.out.println(
      "Thread '" + Thread.currentThread().getName() +
      "' is woken after sleeping for 1 second");
 
    synchronized (LOCK) {
        LOCK.wait(1000);
        System.out.println("Object '" + LOCK + "' is woken after" +
          " waiting for 1 second");
    }
    }

#### Thread.join()

In Java, the Thread.join() method is used to wait for a thread to complete its execution before moving forward with the rest of the code. 
It is a method provided by the Thread class in the Java Concurrency API.

When join() is called on a thread, the calling thread will wait for the specified thread to finish its execution. 
The calling thread will not continue until the specified thread has completed.

In the example below, the join() method is called on the t1 thread. This means that the main thread will wait for t1 to finish before continuing with the rest of the code. 
If the join() method is not called, the main thread will continue with its execution even if t1 is still running.

It's important to note that the join() method can throw an InterruptedException if the thread on which it is called is interrupted while waiting. 
Therefore, it is important to handle this exception properly in your code.

    Thread t1 = new Thread(new Runnable() {
    public void run() {
    // do some work here
    }
    });
    
    // start the thread
    t1.start();
    
    try {
    // wait for the thread to complete before continuing
    t1.join();
    } catch (InterruptedException e) {
    // handle the exception here
    }

#### Thread.join() Methods with Timeout

The join() method will keep waiting if the referenced thread is blocked or takes too long to process. 
This can become an issue as the calling thread will become non-responsive. 
To handle these situations, we use overloaded versions of the join() method that allow us to specify a timeout period.

    @Test
    public void givenStartedThread_whenTimedJoinCalled_waitsUntilTimedout()
    throws InterruptedException {
    Thread t3 = new SampleThread(10); // extends thread class
    t3.start();
    t3.join(1000);
    assertTrue(t3.isAlive());
    }

#### Different ways to implement mutex.

-- Synchronized keyword
-- Using Reentrant lock
    
    public class SequenceGeneratorUsingReentrantLock extends SequenceGenerator {
    private ReentrantLock mutex = new ReentrantLock();

    @Override
    public int getNextSequence() {
        try {
            mutex.lock();
            return super.getNextSequence();
        } finally {
            mutex.unlock();
        }
    }
    }

-- Using Semaphore Variables

    public class SequenceGeneratorUsingSemaphore extends SequenceGenerator {
    private Semaphore mutex = new Semaphore(1);

    @Override
    public int getNextSequence() {
        try {
            mutex.acquire();
            return super.getNextSequence();
        } catch (InterruptedException e) {
            // exception handling code
        } finally {
            mutex.release();
        }
    }
    }

#### Ways of doing asynchronous programming in java

1. Thread
2. Future Task
3. Completable Future

