package serialization_desrialization;

import stream.model.Employee;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Serialize {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Employee employee1 = new Employee("Mohit Singh", "Developer", 1000);
        Employee employee2 = new Employee("Varyy Singh", "Sen. Developer", 2000);
        // serialization
        List<Employee> employeeList = Arrays.asList(employee2, employee1);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("serialization.obj"));
        objectOutputStream.writeObject(employeeList);
        // deserialization
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("serialization.obj"));
        Object o = objectInputStream.readObject();
        System.out.println(o.toString());

    }
}
