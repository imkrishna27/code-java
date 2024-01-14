package java_concurrency.Concurrent.utiltiy;

import java.util.concurrent.ThreadFactory;

public class BaledungThreadFactory implements ThreadFactory {

    private int threadId;
    private String name;

    public BaledungThreadFactory(String name) {
        this.name = name;
        threadId = 1;
    }

    /**
     * Constructs a new unstarted {@code Thread} to run the given runnable.
     *
     * @param r a runnable to be executed by new thread instance
     * @return constructed thread, or {@code null} if the request to
     * create a thread is rejected
     * @see <a href="../../lang/Thread.html#inheritance">Inheritance when
     * creating threads</a>
     */
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, name + "-Thread_" + threadId);
        System.out.println(thread.getName() + "wit id : " + threadId);
        threadId++;
        return thread;
    }
}
