package java_concurrency.Concurrent.utiltiy;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public class Invoker implements Executor {

    /**
     * Executes the given command at some time in the future.  The command
     * may execute in a new thread, in a pooled thread, or in the calling
     * thread, at the discretion of the {@code Executor} implementation.
     *
     * @param command the runnable task
     * @throws RejectedExecutionException if this task cannot be
     *                                    accepted for execution
     * @throws NullPointerException       if command is null
     */
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
