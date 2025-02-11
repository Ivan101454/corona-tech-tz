package by.ivan101454.corona.result;

import by.ivan101454.corona.entity.Person;
import by.ivan101454.corona.enums.OrderSort;
import by.ivan101454.corona.enums.OutputParameter;
import by.ivan101454.corona.enums.Role;
import by.ivan101454.corona.enums.SortParameter;
import by.ivan101454.corona.factory.PersonCreator;
import by.ivan101454.corona.reader.InputArgumentsHandler;
import by.ivan101454.corona.reader.WriterResultHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ResultHandler {

    private final PersonCreator personCreator;
    private final Map<String, String> handler;

    public ResultHandler(PersonCreator personCreator, InputArgumentsHandler inputArgumentsHandler) {
        this.personCreator = personCreator;
        this.handler = inputArgumentsHandler.handler();
    }

    public void getResult() {
        List<Person> people = sortIfRequire();
        List<String> incorrectEntity = personCreator.getListOfIncorrectEntity();
        if (!handler.containsKey("output") || OutputParameter.valueOf(handler.get("output").toUpperCase()).equals(OutputParameter.CONSOLE)) {
            people.forEach(System.out::println);
            incorrectEntity.forEach(System.out::println);
        } else {
            if (OutputParameter.valueOf(handler.get("output").toUpperCase()).equals(OutputParameter.FILE)) {
                StringBuilder sb = new StringBuilder();
                people.forEach(note -> sb.append(note).append("\n"));
                sb.append("Некорректные данные: \n");
                incorrectEntity.forEach(note -> sb.append(note).append("\n"));
                String result = sb.toString();
                String path = handler.get("path");
                WriterResultHandler wh = new WriterResultHandler();
                wh.write(path, result);
            }
        }
    }

    private List<Person> sortIfRequire() {
        if (handler.containsKey("sort")) {
            return sortEmployeesByParameter(handler.get("sort"), handler.get("order"));
        } else return personCreator.getListOfCorrectEntity();
    }

    private List<Person> sortEmployeesByParameter(String sort, String order) {
        List<Person> sortedEmployees = new ArrayList<>();
        SortParameter sortParameter = SortParameter.valueOf(sort.toUpperCase());
        OrderSort orderSort = OrderSort.valueOf(order);

        ArrayList<Person> entities = personCreator.getListOfCorrectEntity();
        List<Person> listOfEmployees = entities.stream().filter(empl -> empl.getRole() == Role.EMPLOYEE).toList();
        List<Person> listOfManagers = entities.stream().filter(empl -> empl.getRole() == Role.MANAGER).toList();
        List<Person> eployees = new ArrayList<>();
        switch (orderSort) {
            case DESC -> {
                switch (sortParameter) {
                    case SALARY -> {
                        eployees = listOfEmployees.stream().sorted(Comparator.comparing(Person::getSalary).reversed()).toList();
                    }
                    case NAME -> {
                        eployees = listOfEmployees.stream().sorted(Comparator.comparing(Person::getName).reversed()).toList();
                    }
                }
            }
            case ASC -> {
                switch (sortParameter) {
                    case SALARY -> {
                        eployees = listOfEmployees.stream().sorted(Comparator.comparing(Person::getSalary)).toList();
                    }
                    case NAME -> {
                        eployees = listOfEmployees.stream().sorted(Comparator.comparing(Person::getName)).toList();
                    }
                }
            }
        }
        sortedEmployees.addAll(listOfManagers);
        sortedEmployees.addAll(eployees);
        return sortedEmployees;
    }
}
