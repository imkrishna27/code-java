package concurrent;

import stream.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Sum implements Callable {
    private final int start;
    private final int end;
    private int sum = 0;
    Sum(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        for(int i=start;i<=end;i++) {
            this.sum+=i;
        }
        return this.sum;
    }
}
class ExcecuterServiceImpl {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<Integer>> callableList = new ArrayList<>();
        callableList.add(new Sum(1,3));
        callableList.add(new Sum(4,6));
        callableList.add(new Sum(7,10));
        List<Future<Integer>> futures = executorService.invokeAll(callableList);
        Integer ans = 0;
        for(Future<Integer> future: futures) {
            ans += future.get();
        }
        System.out.println("Total sum is: "+ ans);
    }
}
