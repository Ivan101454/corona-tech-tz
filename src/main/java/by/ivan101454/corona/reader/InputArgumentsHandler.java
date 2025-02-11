package by.ivan101454.corona.reader;

import by.ivan101454.corona.enums.SortParameter;
import by.ivan101454.corona.exception.NotCorrectArgumentException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputArgumentsHandler {

    private final String[] args;

    public InputArgumentsHandler(String[] args) {
        this.args = args;
    }

    public Map<String, String> handler() {
        Map<String, String> mapOfArgs = new HashMap<>();
        Pattern patternSort = Pattern.compile("--sort=\\S+");
        Pattern patternOrder = Pattern.compile("--order=\\S+");
        Pattern patternOutput = Pattern.compile("--output=\\S+");
        Pattern patternPath = Pattern.compile("--path=\\S+");

        for (String arg : args) {
            Matcher matcherSort = patternSort.matcher(arg);
            if (matcherSort.matches()) {
                String sort = arg.split("=")[1].trim();
                try {
                    SortParameter sortParameter = SortParameter.valueOf(sort.toUpperCase());
                    mapOfArgs.put("sort", sort);
                } catch (IllegalArgumentException e) {
                    System.err.println("Ошибка: некорректное значение параметра сортировки" + ". Подробности: " + e.getMessage());
                }
            }
            Matcher matcherOrder = patternOrder.matcher(arg);
            if (matcherOrder.matches()) {
                try {
                    String order = arg.split("=")[1].trim();
                    SortParameter sortParameter = SortParameter.valueOf(order.toUpperCase());
                    mapOfArgs.put("order", order);
                } catch (IllegalArgumentException e) {
                    System.err.println("Ошибка: некорректное значение порядка сортировки" + ". Подробности: " + e.getMessage());
                }
                Matcher matcherOutput = patternOutput.matcher(arg);
                if (matcherOutput.matches()) {
                    String output = arg.split("=")[1].trim();
                    mapOfArgs.put("output", output);
                }
                Matcher matcherPath = patternPath.matcher(arg);
                if (matcherPath.matches()) {
                    String path = arg.split("=")[1].trim();
                    mapOfArgs.put("path", path);
                }
            }
            if ((mapOfArgs.containsKey("output") && !mapOfArgs.containsKey("path")) || (!mapOfArgs.containsKey("output") && mapOfArgs.containsKey("path"))) {
                try {
                    throw new NotCorrectArgumentException("Вы должны указать И --output И --path аргументы");
                } catch (NotCorrectArgumentException e) {
                    System.err.println("Ошибка: некорректное значение аргументов" + ". Подробности: " + e.getMessage());
                }
            }
            if ((mapOfArgs.containsKey("order") && !mapOfArgs.containsKey("sort"))) {
                try {
                    throw new NotCorrectArgumentException("Вы должны указать аргументы --order без --sort");
                } catch (NotCorrectArgumentException e) {
                    System.err.println("Ошибка: некорректное значение аргументов сортировки" + ". Подробности: " + e.getMessage());
                }
            }
        }
        return mapOfArgs;
    }
}
