#### Delay Thread

1. Thread.sleep()
2. TimeUnit.SECONDS.sleep(seconds) --> use Thread.sleep() in behind.
3. ExecutorService based approach --> executorService.schedule(Classname::someTask, delayInSeconds, TimeUnit.SECONDS);

### Stop Execution After Certain Time.

1.    long start = System.currentTimeMillis();
    long end = start + 30 * 1000;
    while (System.currentTimeMillis() < end) {
    // Some expensive operation on the item.
    }

2. Using Thread.interrupt()
3. Using future.get()
4. Using ExecutorService methods --> shutdown(), shutdownNow() and others.