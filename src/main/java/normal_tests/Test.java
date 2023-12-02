package normal_tests;

import normal_tests.utils.Employee;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Test implements Serializable,Cloneable {


    public static void main(String[] args) throws CloneNotSupportedException {
         List<Integer> arr = Arrays.asList(1,21,12,113,132,45,67);
        List<String> collect = arr.stream().map(ele -> String.valueOf(ele)).filter(ele -> ele.startsWith("1")).collect(Collectors.toList());
        System.out.println(Arrays.toString(collect.toArray()));
        /// group by gender and count

        List<Employee> employees = Arrays.asList(
                new Employee("hari","male",2000L),
                new Employee("seema","female",10000L),
                new Employee("ram","male",2000L)
        );
        Map<String, Double> collect1 = employees.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingLong(Employee::getSalary)));
        // sort on basis of salary
        List<Employee> collect2 = employees.stream().sorted((emp1, emp2) -> emp1.getSalary().compareTo(emp2.getSalary())).collect(Collectors.toList());
        System.out.println(collect1);
        System.out.println(collect2);

        StringBuilder stringBuilder = new StringBuilder("hello");
        String s2 = new String("hello");
        String s3 = new String("hello");
        System.out.println(s3.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s2.equals(s3));
        int a=10;
        int b=10;
        Employee employee = new Employee();
        Class<? extends Employee> aClass = employee.getClass();
        System.out.println(aClass);

        System.out.println(a==b);
//        HashMap<Employee,Employee> hashMap = new HashMap<>();
//        Employee employee1 = new Employee("ram", "male", 100L);
//        hashMap.put(employee1,new Employee("seeta","female",200L));
//        hashMap.put(new Employee("jay","male",300L),new Employee("jaya","female",400L));
//        employee1.setName("ra");
//        Employee employee = hashMap.get(new Employee("ram", "male", 100L));
//        System.out.println(employee);
//        HashMap<Integer,Integer> hm = new HashMap<>();
//        hm.put(11,11);
//        hm.put(null,12);
//        hm.put(null,13);
//
//        Double i = new Double(12111111);
//        Double i1 = new Double(12111111);
//        System.out.println(Objects.equals(i,i1));
//
//
//        Hashtable<Integer,Integer> hashtable = new Hashtable<>();
//        hashtable.put(11,2);
//
//        System.out.println(hm.get(null));

    }

    @Override
    public Test clone() {
        try {
            return (Test) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
