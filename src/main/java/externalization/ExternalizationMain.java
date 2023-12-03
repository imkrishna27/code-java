package externalization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;

/**
 * Externalization gives you more control over serialization process
 * In Externalization Programmers decides how serialization is done.
 * In case of serialization JVM takes full responsibility for serializing the class instance.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExternalizationMain implements Externalizable {
    private Integer enrollmentId;
    private String employeeName;
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(enrollmentId);
        out.writeObject(employeeName);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        enrollmentId = (Integer) in.readObject();
        employeeName = (String)in.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream outputStreamWriter = new ObjectOutputStream(new FileOutputStream("extern.obj"));
        outputStreamWriter.writeObject(new ExternalizationMain(1,"Hari"));
        outputStreamWriter.close();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("extern.obj"));
        Object o = inputStream.readObject();
        System.out.println(o.toString());

    }
}
