package java_core;

public class VariableArguments {

    private void arrayOfCountries(String... countries) {
        for (String country:countries) {
            System.out.println(country);
        }
    }

    public static void main(String[] args) {
        VariableArguments variableArguments = new VariableArguments();
        variableArguments.arrayOfCountries(new String[]{"England","Newzeland","Bangladesh","India"});
    }
}
