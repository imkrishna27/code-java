package print_numbers_thread;

public class PrintNumbers {
    private int curr_num = 1;
    private int max_num = 20;
    public synchronized void printNumbers(int index) throws InterruptedException {
        while ( curr_num != max_num-1) {
            while (curr_num % 5 != index) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Thread : " + Thread.currentThread().getName() + " value : " + curr_num + " index : "+ index);
            curr_num++;
            notifyAll();
        }
    }
}

class PrintNumbersRunnable implements Runnable {
    private final int index;
    private final PrintNumbers printNumbers;

    PrintNumbersRunnable(int index, PrintNumbers printNumbers) {
        this.index = index;
        this.printNumbers = printNumbers;
    }

    @Override
    public void run() {
        try {
            this.printNumbers.printNumbers(this.index);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class ThreadMain {
    public static void main(String[] args) {
        PrintNumbers printNumbers = new PrintNumbers();
        Thread thread1= new Thread(new PrintNumbersRunnable(1,printNumbers));
        Thread thread2= new Thread(new PrintNumbersRunnable(2,printNumbers));
        Thread thread3= new Thread(new PrintNumbersRunnable(3,printNumbers));
        Thread thread4= new Thread(new PrintNumbersRunnable(4,printNumbers));
        Thread thread5= new Thread(new PrintNumbersRunnable(0,printNumbers));


        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

    }
}


