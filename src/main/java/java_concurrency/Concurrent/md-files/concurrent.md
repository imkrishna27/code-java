### Tread Execution -

```
ExecutorService executorService = Executors.newFixedThreadPool(5);

// Submit tasks to the executor service
executorService.submit(() -> {
    // task 1
});
executorService.submit(() -> {
    // task 2
});

// Shutdown the executor service
executorService.shutdown();

try {
    // Wait for all tasks to complete execution or until 30 seconds have elapsed
    if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
        // Cancel any remaining tasks
        executorService.shutdownNow();
    }
} catch (InterruptedException e) {
    // Handle the exception
}

```

#### Lock API
There are a few differences between the use of synchronized block and using Lock APIs:

A synchronizedblock is fully contained within a method. We can have Lock APIs lock() and unlock() operation in separate methods.
A synchronized block doesn't support the fairness. Any thread can acquire the lock once released, and no preference can be specified. We can achieve fairness within the Lock APIs by specifying the fairness property. It makes sure that the longest waiting thread is given access to the lock.
A thread gets blocked if it can't get an access to the synchronized block. The Lock API provides tryLock() method. The thread acquires lock only if it's available and not held by any other thread. This reduces blocking time of thread waiting for the lock.
A thread that is in “waiting” state to acquire the access to synchronized block can't be interrupted. The Lock API provides a method lockInterruptibly() that can be used to interrupt the thread when it's waiting for the lock.

**void lock()** – Acquire the lock if it's available. If the lock isn't available, a thread gets blocked until the lock is released.

**void lockInterruptibly()** – This is similar to the lock(), but it allows the blocked thread to be interrupted and resume the execution through a thrown java.lang.InterruptedException.

**boolean tryLock()** – This is a nonblocking version of lock() method. It attempts to acquire the lock immediately, return true if locking succeeds.

**boolean tryLock(long timeout, TimeUnit timeUnit)** – This is similar to tryLock(), except it waits up the given timeout before giving up trying to acquire the Lock.

**void unlock()** unlocks the Lock instance.

In addition to the Lock interface, we have a ReadWriteLock interface that maintains a pair of locks, one for read-only operations and one for the write operation. The read lock may be simultaneously held by multiple threads as long as there is no write.

ReadWriteLock declares methods to acquire read or write locks:

_Lock readLock()_ returns the lock that's used for reading.

_Lock writeLock()_ returns the lock that's used for writing.

####  Lock Implementations

1. Reentrant Lock
2. ReentrantReadWrite Lock
3. Stamped Lock

#### 1. Reentrant Lock

    public class SharedObject {
    ReentrantLock lock = new ReentrantLock();
    int counter = 0;

    public void perform() {
        lock.lock();
        try {
            // Critical section here
            count++;
        } finally {
            lock.unlock();
        }
    }
    }
 we can also use tryLock :

    public void performTryLock(){
    boolean isLockAcquired = lock.tryLock(1, TimeUnit.SECONDS);
    if(isLockAcquired) {
        try {
            //Critical section here
        } finally {
            lock.unlock();
        }
    }
    }


#### 2. ReentrantReadWrite Lock

Let's see the rules for acquiring the ReadLock or WriteLock by a thread:

Read Lock – If no thread acquired the write lock or requested for it, multiple threads can acquire the read lock.

Write Lock – If no threads are reading or writing, only one thread can acquire the write lock.
Let's look at how to make use of the ReadWriteLock:

    public class SynchronizedHashMapWithReadWriteLock {
    Map<String,String> syncHashMap = new HashMap<>();
    ReadWriteLock lock = new ReentrantReadWriteLock();
    // ...
    Lock writeLock = lock.writeLock();

    public void put(String key, String value) {
        try {
            writeLock.lock();
            syncHashMap.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }
    ...
    public String remove(String key){
        try {
            writeLock.lock();
            return syncHashMap.remove(key);
        } finally {
            writeLock.unlock();
        }
    }
    //...
    }

Read Lock :-


    Lock readLock = lock.readLock();
    //...
    public String get(String key){
        try {
            readLock.lock();
            return syncHashMap.get(key);
        } finally {
            readLock.unlock();
        }
    }
    
    public boolean containsKey(String key) {
        try {
            readLock.lock();
            return syncHashMap.containsKey(key);
        } finally {
            readLock.unlock();
        }
    }

#### 3. Stamped Lock

StampedLock is introduced in Java 8. It also supports both read and write locks.

However, lock acquisition methods return a stamp that is used to release a lock or to check if the lock is still valid:

    public class StampedLockDemo {
    Map<String,String> map = new HashMap<>();
    private StampedLock lock = new StampedLock();
    public void put(String key, String value){
        long stamp = lock.writeLock();
        try {
            map.put(key, value);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public String get(String key) throws InterruptedException {
        long stamp = lock.readLock();
        try {
            return map.get(key);
        } finally {
            lock.unlockRead(stamp);
        }
    }
    }

Another feature provided by StampedLock is optimistic locking. Most of the time, read operations don't need to wait for write operation completion, and as a result of this, the full-fledged read lock isn't required.

Instead, we can upgrade to read lock:

    public String readWithOptimisticLock(String key) {
    long stamp = lock.tryOptimisticRead();
    String value = map.get(key);
    if(!lock.validate(stamp)) {
        stamp = lock.readLock();
        try {
            return map.get(key);
        } finally {
            lock.unlock(stamp);               
        }
    }
    return value;
    }

#### Working  With Conditions

The Condition class provides the ability for a thread to wait for some condition to occur while executing the critical section.
This can occur when a thread acquires the access to the critical section but doesn't have the necessary condition to perform its operation. For example, a reader thread can get access to the lock of a shared queue that still doesn't have any data to consume.

Traditionally Java provides wait(), notify() and notifyAll() methods for thread intercommunication.

    public class ReentrantLockWithCondition {
    Stack<String> stack = new Stack<>();
    int CAPACITY = 5;

    ReentrantLock lock = new ReentrantLock();
    Condition stackEmptyCondition = lock.newCondition();
    Condition stackFullCondition = lock.newCondition();

    public void pushToStack(String item){
        try {
            lock.lock();
            while(stack.size() == CAPACITY) {
                stackFullCondition.await();
            }
            stack.push(item);
            stackEmptyCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String popFromStack() {
        try {
            lock.lock();
            while(stack.size() == 0) {
                stackEmptyCondition.await();
            }
            return stack.pop();
        } finally {
            stackFullCondition.signalAll();
            lock.unlock();
        }
    }
    }