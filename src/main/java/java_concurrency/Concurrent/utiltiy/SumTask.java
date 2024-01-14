package java_concurrency.Concurrent.utiltiy;

import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 2;
    private int[] array;
    private int start;
    private int end;

    public SumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    /**
     * left.fork() starts executing the left sub-task asynchronously in a new thread. This means that the current thread continues executing the rest of the code in the compute() method.
     * right.compute() is called to execute the right sub-task synchronously in the current thread. This means that the current thread is blocked until the right sub-task completes.
     * Once the right sub-task completes, left.join() is called to wait for the left sub-task to complete and obtain its result. This means that the current thread is blocked until the left sub-task completes.
     * Once the left sub-task completes, the results of the left and right sub-tasks are combined by adding them together (leftResult + rightResult), and the final result is returned from the compute() method.
     */
    @Override
    public Long compute() {
        if (end - start <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            SumTask left = new SumTask(array, start, mid);
            SumTask right = new SumTask(array, mid, end);
            left.fork(); // send to separate thread to compute
            long rightResult = right.compute(); // executing asynchronously in current thread
            long leftResult = left.join(); // wait for left sub-task to complete and obtain the result
            return leftResult + rightResult;
        }
    }
}
