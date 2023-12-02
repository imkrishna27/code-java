package stream;

import stream.model.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupEmployeeByTitle {
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>(
                Arrays.asList(
                        new Employee("Hari Krishna","Developer",12000),
                        new Employee("Shobhit Singh","Senior Developer",10000),
                        new Employee("Varun Kumar","Developer",9000),
                        new Employee("Sarthak Singh","Senior Developer",11000)
                ));
        Map<String, List<Employee>> collect = employeeList.stream().collect(Collectors.groupingBy(Employee::getEmpTitle));
        System.out.println(collect.toString());

    }
}
