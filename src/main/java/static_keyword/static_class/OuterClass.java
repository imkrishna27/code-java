package static_keyword.static_class;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OuterClass {
    private String outerClassMember;
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class InnerClass {
        private String innerClassMember;
    }
}
class StaticUtil {
    public static void main(String[] args) {
        // creating inner class obj
        OuterClass.InnerClass innerClass = new OuterClass.InnerClass();
        innerClass.setInnerClassMember("InnerClassMember");
        System.out.println(innerClass.getInnerClassMember());
        // creating outer class obj
        OuterClass outerClass = new OuterClass();
        outerClass.setOuterClassMember("OuterClassMember");
        System.out.println(outerClass.getOuterClassMember());
    }
}
