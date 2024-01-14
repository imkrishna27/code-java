package java_core_2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

class ImplementSingleton {
    private static ImplementSingleton instance= null;

    public String s;

    private ImplementSingleton(String s) {
        if(instance!=null) throw new RuntimeException("Can't create another object of singleton class");
        this.s = s;
    }

    public static synchronized ImplementSingleton getInstance() {
        if(Objects.isNull(instance)) {
            instance = new ImplementSingleton("hello my name is krishna");
        }
        return instance;
    }
}

class TestSingletonClas {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        //having same hashcode different instances
        ImplementSingleton instance2 = ImplementSingleton.getInstance();
        ImplementSingleton instance3 = ImplementSingleton.getInstance();
        System.out.println(instance3.equals(instance2));
        System.out.println(instance3.hashCode() + "-----" + instance2.hashCode());
        // trying to break singleton using reflection
        String str = "break class";
        Constructor<ImplementSingleton> singletonConstructor = ImplementSingleton.class.getDeclaredConstructor(String.class);
        singletonConstructor.setAccessible(true);
        ImplementSingleton implementSingleton = singletonConstructor.newInstance("tring to break class");
        System.out.println(implementSingleton.hashCode());

    }
}
