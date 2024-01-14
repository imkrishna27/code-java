#### Volatile keyword and Synchronization

In Java, the volatile keyword is used to mark a variable as "volatile". When a variable is marked as volatile, it indicates to the Java Virtual Machine (JVM) that the variable may be accessed concurrently by multiple threads, 
and that the value of the variable should always be read directly from memory and not from a cache.

When a variable is marked as volatile, any writes to that variable are immediately visible to other threads. This means that when one thread writes to a volatile variable, other threads can immediately see the updated value of that variable without needing to synchronize or block.
Similarly, when a thread reads a volatile variable, it is guaranteed to see the most up-to-date value of that variable, even if another thread has updated the value since the last time it was read.

The use of the volatile keyword is one of the ways to achieve thread-safety in Java. By marking a variable as volatile, we can ensure that the variable is accessed atomically and that any updates to the variable are immediately visible to other threads. 
However, it's important to note that the use of volatile does not eliminate the need for other concurrency control mechanisms, such as locks or synchronization, when more complex operations need to be performed atomically.

While declaring volatile we make sure we follow two properties :

1. mutual exclusion --> only one thread access critical section
2. visibility --> changes made by one thread visible to another thread.

       public class Example {
         private static int x; // not volatile
         private volatile static boolean flag; // volatile
         public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            x = 42; // write to x
            flag = true; // write to flag
        });

        Thread t2 = new Thread(() -> {
            if (flag) { // read from flag
                System.out.println(x); // read from x
            }
        });

        t1.start();
        t2.start();
       }
       }
In this example, we have two threads, t1 and t2, that access two variables x and flag. The x variable is not marked as volatile, but we want to ensure that any writes to it by t1 are visible to t2 when it reads from it. 
To achieve this, we mark the flag variable as volatile.

When t1 runs, it first writes the value 42 to x, and then sets the flag variable to true. Because the flag variable is marked as volatile, this write operation establishes a happens-before relationship with any subsequent reads of flag. 
This means that when t2 reads from flag and finds its value to be true, it can be guaranteed that any writes to x that occurred before the write to flag are visible to t2.

Therefore, when t2 runs and reads from flag, it can safely read from x and expect to see the value 42 that was written by t1.

Note that in this example, we only need to mark the flag variable as volatile, and not x. This allows us to optimize the performance of the code while still achieving the desired visibility guarantees. 
However, it's important to note that this technique should be used with care, and only in cases where we are confident that it will work correctly and without introducing any race conditions.
