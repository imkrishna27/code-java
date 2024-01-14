package normal_tests;

import stream.model.Employee;

public class TestUtil {
    public static void main(String[] args) {
        Employee employee = new Employee("hk","rty",12);
        Employee employee2 = employee;
        employee2.setEmpName("yellow");
        employee2 = new Employee("tyrer",":ere",3);
        employee2.setEmpName("rahul");
        System.out.println(employee2 + " "+ employee);
    }
}
