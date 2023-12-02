package normal_tests;

import normal_tests.utils.SumFunctionalInterface;

public class FuncInterfaceTest {
    public static void main(String[] args) {
        System.out.println(average(2, (a,b)->a+b));
    }

    public static int average(int c, SumFunctionalInterface sumFunc) {
        return sumFunc.sum(2,4)/2;
    }
}
