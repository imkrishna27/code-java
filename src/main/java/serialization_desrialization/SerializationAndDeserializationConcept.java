package serialization_desrialization;

import java.io.*;

public class SerializationAndDeserializationConcept implements Serializable {

    SerializationAndDeserializationConcept() {
        this.a = 5;
    }
    private transient final Integer a;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // serializing objects
        SerializationAndDeserializationConcept serializationAndDeserializationConcept = new SerializationAndDeserializationConcept();
        ObjectOutputStream objectOutputStream  = new ObjectOutputStream(new FileOutputStream("abc.obj"));
        objectOutputStream.writeObject(serializationAndDeserializationConcept);
        // deserializing objects
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("abc.obj"));
        SerializationAndDeserializationConcept object = (SerializationAndDeserializationConcept) objectInputStream.readObject();
        System.out.println(object.a);
    }
}
