package java_core_2;

import java.util.Date;

/*
 Abstract classes can have abstract as well as non-abstract methods.
 Multiple inheritance not supported
 */
public abstract class AbstractClasses {
    private String getName() {
        return "My Name is Hari Krishna";
    }
    public abstract Date getMyDOB();
}
