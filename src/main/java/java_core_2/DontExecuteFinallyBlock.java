package java_core_2;

public class DontExecuteFinallyBlock {
    public static void main(String[] args) throws Exception {
        try {
            throw new Exception("Exception Occurred");
        } catch(Exception e) {
            System.out.println(e);
            System.exit(0);
        } finally {
            System.out.println("Finally in Finally");
        }
    }
}
