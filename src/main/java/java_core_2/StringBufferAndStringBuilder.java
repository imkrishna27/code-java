package java_core_2;

public class StringBufferAndStringBuilder {
    // both string buffer and string builder is mutable
    public static void main(String[] args) {
        // string buffer is synchronized and thread safe
        StringBuffer stringBuffer = new StringBuffer("hello");
        stringBuffer.append(" krishna -> string buffer");
        System.out.println(stringBuffer);
        // string builder is not thread safe
        StringBuilder stringBuilder = new StringBuilder("hello");
        stringBuffer.append(" krishna -> string builder");
        System.out.println(stringBuilder);
        // new String is immutable but stored in heap
        String string = new String("Hello There");
        System.out.println(string);
    }
}
