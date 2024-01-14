package static_keyword.other;

import lombok.Data;

@Data
public class StaticVariablesAndMethods {
    private int normalVar;
    public static int staticVar; // can be accessed by classname only
    public static int getStaticVarVal() { // can be accessed by classname only
        return staticVar;
    }
}

class StaticVariablesUtil {
    public static void main(String[] args) {
        StaticVariablesAndMethods staticVariables = new StaticVariablesAndMethods();
        staticVariables.setNormalVar(1);
        System.out.println(staticVariables.getNormalVar());
        // static variables
        StaticVariablesAndMethods.staticVar = 2;
        System.out.println(StaticVariablesAndMethods.staticVar);
        System.out.println(StaticVariablesAndMethods.getStaticVarVal());

    }

}
