package comparator_and_comparable;

import stream.model.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparatorImpl implements Comparator<Employee> {

    @Override
    public int compare(Employee e1, Employee e2) {
        if(e1.getSalary()>e2.getSalary()) return 1;
        else if(e1.getSalary()<e2.getSalary()) return -1;
        else return 0; // can be replaced with Integer.compare(e1.val,e2.val)
    }

    public static void main(String[] args) {
        List<Employee> employeeList = Arrays.asList(
                new Employee("hari","dev",1000),
                new Employee("krishna","test",8000),
                new Employee("singh","automation",71000)
                );
        employeeList.sort((e1,e2)->new ComparatorImpl().compare(e1,e2));
        System.out.println(employeeList);
    }
}
