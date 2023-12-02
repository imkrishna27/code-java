package exception_handling;

public class CustomExceptionHandler extends Exception {
    CustomExceptionHandler(String exceptionMessage) {
        super(exceptionMessage);
    }
}

class TestException {
    public static void main(String[] args) throws CustomExceptionHandler {
        throw new CustomExceptionHandler("Some Exception Occured");
    }
}
