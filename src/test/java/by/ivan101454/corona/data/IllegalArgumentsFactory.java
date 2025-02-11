package by.ivan101454.corona.data;

public class IllegalArgumentsFactory {


    public String[][] createIllegalArguments(KindArgument kind) {
        String[][] result;
        switch(kind) {
            case ROLE -> result = new String[][]{{"Not such role", "1", "Jane Smith", "5000", "HR" }};
            case ALL -> result = new String[][]{{"Manager", "1", "Jane Smith", "5000", "HR" }};
            case TWO -> result = new String[][]{
                    {"Manager", "1", "Jane Smith", "5000", "HR" },
                    {"Employee", "101", "John Doe", "3000", "1" },
            };
            default -> result = null;
        }
        return result;
    }
}
