package java_concurrency.Concurrent;
import java_concurrency.Concurrent.utiltiy.ContextUtil;

import java.lang.*;

public class ThreadLocalImpl {
    public static void main(String[] args) {
        ThreadLocalImpl.impl();
    }

    /**
     * The TheadLocal construct allows us to store data that will be accessible only by a specific thread.
     * Let's say that we want to have an Integer value that will be bundled with the specific thread:
     * ThreadLocal<Integer> threadLocalValue = new ThreadLocal<>();
     * Next, when we want to use this value from a thread, we only need to call a get() or set() method.
     * Simply put, we can imagine that ThreadLocal stores data inside of a map with the thread as the key.
     */
    private static void impl() {
        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        stringThreadLocal.set("Hari Krishna");
        System.out.println(stringThreadLocal.get());
        ThreadLocal<ContextUtil> stringThreadLocal1 = new ThreadLocal<>();
        stringThreadLocal1.set(new ContextUtil("Virat",123));
        ContextUtil contextUtil = stringThreadLocal1.get();
        System.out.println(contextUtil);

    }
}
