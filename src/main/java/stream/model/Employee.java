package stream.model;

import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class Employee implements Serializable {
    private String empName;
    private String empTitle;

    private int salary;

    public Employee(String empName, String empTitle, int salary) {
        this.empName = empName;
        this.empTitle = empTitle;
        this.salary = salary;
    }

    public Employee(Employee obj) {
        this(new String(obj.empName),new String(obj.empTitle),new Integer(obj.salary));
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpTitle() {
        return empTitle;
    }

    public void setEmpTitle(String empTitle) {
        this.empTitle = empTitle;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empName='" + empName + '\'' +
                ", empTitle='" + empTitle + '\'' +
                ", salary=" + salary +
                '}';
    }
}
