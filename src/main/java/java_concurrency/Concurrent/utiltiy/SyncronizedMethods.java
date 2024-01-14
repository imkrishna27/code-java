package java_concurrency.Concurrent.utiltiy;

import lombok.Data;

@Data
public class SyncronizedMethods {
    private int sum=0;
    private int syncSum=0;
    public void calculate() {
        setSum(getSum()+1);
    }

    public synchronized void syncCalc() {
        setSyncSum(getSyncSum()+1);
    }
}
