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
            case ID -> result = new String[][]{{"Manager", "12I3", "Jane Smith", "5000", "HR" }};
            case UNIQUE -> result = new String[][]{
                    {"Manager", "1", "Jane Smith", "5000", "HR" },
                    {"Employee", "1", "John Doe", "3000", "1" },
            };
            case SALARY -> result = new String[][]{{"Manager", "1", "Jane Smith", "S000", "HR" }};
            case MINUS -> result = new String[][]{{"Manager", "1", "Jane Smith", "-1000", "HR" }};
            case NAME -> result = new String[][]{{"Manager", "1", "", "-1000", "HR" }};
            case IDENTIFICATION -> result = new String[][]{{"Manager", "1", "Jane Smith", "5000", "" }};
            default -> result = null;
        }
        return result;
    }
}
