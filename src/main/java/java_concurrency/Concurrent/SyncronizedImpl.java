package java_concurrency.Concurrent;

import java_concurrency.Concurrent.utiltiy.SyncronizedMethods;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Two threads do not attempt to update mutable shared data at the same time for that we use synchronized blocks.
 */
public class SyncronizedImpl {
    public static void main(String[] args) throws InterruptedException {
        SyncronizedImpl.impl();
    }

    private static void impl() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        SyncronizedMethods syncronizedMethods = new SyncronizedMethods();
        IntStream.range(0,1000)
                .forEach(data-> executorService.submit(syncronizedMethods::calculate));

        executorService.awaitTermination(1000,TimeUnit.MILLISECONDS);
        /* sum will never be 1000 because the block is not synchronised */
        System.out.println(syncronizedMethods.getSum());

        /* ------------------------------------------------------------------------- */

        ExecutorService executorServiceSync = Executors.newFixedThreadPool(3);
        IntStream.range(0,1000)
                .forEach(data-> executorServiceSync.submit(syncronizedMethods::syncCalc));

        executorServiceSync.awaitTermination(1000, TimeUnit.MILLISECONDS);
        /* sum will always be 1000 because the block is synchronised */
        System.out.println(syncronizedMethods.getSyncSum());

        /* There are three ways to synchronize the block of code
        * 1. Instance Methods -> explained above
        * 2. Static Methods -> make methods static now they will be part of class. Since only one instance can be created by JVM , hence only one thread can mutate data at a time.
        * 3. Code blocks -->
        *           public void performSynchronisedTask() {
                        synchronized (this) {
                        setCount(getCount()+1);
                    }
                   }
        *
        --------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        Reentrancy-->
        * The lock behind the synchronized methods and blocks is a reentrant.
        * This means the current thread can acquire the same synchronized lock over and over again while holding it:
        * */

        Object lock = new Object();
        synchronized (lock) {
            System.out.println("First time acquiring it");
            synchronized (lock) {
                System.out.println("Entering again");
                synchronized (lock) {
                    System.out.println("And again");
                }
            }
        }

        executorService.shutdown();
        executorServiceSync.shutdown();
    }
}