package stream;

import stream.model.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class CalculateAverageSalary {
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>(
                Arrays.asList(
                        new Employee("Hari Krishna","Developer",12000),
                        new Employee("Shobhit Singh","Senior Developer",10000),
                        new Employee("Varun Kumar","Developer",9000),
                        new Employee("Sarthak Singh","Senior Developer",11000)
                ));

        double ans = employeeList.stream().mapToInt(Employee::getSalary).average().getAsDouble();
        System.out.println(ans);
    }
}
