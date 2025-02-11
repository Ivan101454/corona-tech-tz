package by.ivan101454.corona.reader;

import by.ivan101454.corona.exception.NotCorrectArgumentException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputArgumentsHandler {

    public Map<String, String> handler(String[] args) {
        Map<String, String> mapOfArgs = new HashMap<>();
        Pattern patternSort = Pattern.compile("--sort=\\S+");
        Pattern patternOrder = Pattern.compile("--order=\\S+");
        Pattern patternOutput = Pattern.compile("--output=\\S+");
        Pattern patternPath = Pattern.compile("--path=\\S+");

        for (String arg : args) {
            Matcher matcherSort = patternSort.matcher(arg);
            if (matcherSort.matches()) {
                String sort = arg.split("=")[1].trim();
                mapOfArgs.put("sort", sort);
            }
            Matcher matcherOrder = patternOrder.matcher(arg);
            if (matcherOrder.matches()) {
                String order = arg.split("=")[1].trim();
                mapOfArgs.put("order", order);
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
        return mapOfArgs;
    }
}
