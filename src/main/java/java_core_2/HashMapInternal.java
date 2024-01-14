package java_core_2;

import java.util.HashMap;
import java.util.Objects;

class HashMapInternal {
    String name;
    public HashMapInternal(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HashMapInternal)) {
            return false;
        }
        HashMapInternal other = (HashMapInternal) obj;
        return Objects.equals(name, other.name);
    }
}

 class HashMapExercise {

    public static void main(String args[]) {
        HashMapInternal emp1 = new HashMapInternal("One");
        HashMapInternal emp2 = new HashMapInternal("One");

        HashMap<HashMapInternal, String> hm = new HashMap<HashMapInternal, String>();

        hm.put(emp1, "One");
        hm.put(emp2, "Two");

        System.out.println("Both Objects are Equal: "+emp1.equals(emp2));
        System.out.println("HashMapInternal 1 Hashcode: "+emp1.hashCode());
        System.out.println("HashMapInternal 2 Hashcode: "+emp2.hashCode());
        hm.forEach((k, v) -> System.out.println("Key is: " + k + " Value is: " + v));
    }

}
