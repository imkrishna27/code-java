package comparator_and_comparable;

import stream.model.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ComparableImpl implements Comparable<Employee> {
    @Override
    public int compareTo(Employee o) {
        return Integer.compare(o.getSalary(),10000);
    }

    public static void main(String[] args) {
        List<Employee> employeeList = Arrays.asList(
                new Employee("hari","dev",1000),
                new Employee("krishna","test",8000),
                new Employee("singh","automation",71000)
        );

        List<Employee> collect = employeeList.stream().filter((employee) -> new ComparableImpl().compareTo(employee) < 0).collect(Collectors.toList());
        System.out.println(collect);

    }
}
