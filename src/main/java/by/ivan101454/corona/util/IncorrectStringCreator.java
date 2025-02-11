package by.ivan101454.corona.util;

public class IncorrectStringCreator {

    public String createResultString(String[] mas) {
        StringBuilder sb = new StringBuilder();
        for (String field : mas) {
            sb.append(field);
        }
        return sb + "\n";
    }

}
