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
import java.util.stream.Collectors;

public class ResultHandler {

    private final PersonCreator personCreator;
    private final Map<String, String> handler;

    public ResultHandler(PersonCreator personCreator, InputArgumentsHandler inputArgumentsHandler) {
        this.personCreator = personCreator;
        this.handler = inputArgumentsHandler.handler();
    }

    /**
     * Метода производит формирование вывода данных о рабониках в нужном формате,
     * определяет вывод в файл или на консоль, высчитывет среднею заплату по отделу
     */
    public void getResult() {
        List<Person> people = sortIfRequire();
        Map<String, Double> averageSalary = people.stream()
                .collect(Collectors.groupingBy(Person::getIdentification, Collectors.averagingDouble(person -> person.getSalary().doubleValue())));
        Map<String, List<Person>> groupPersonList = sortIfRequire().stream().collect(Collectors.groupingBy(Person::getIdentification));
        List<String> incorrectEntity = personCreator.getListOfIncorrectEntity();
        if (!handler.containsKey("output") || OutputParameter.valueOf(handler.get("output").toUpperCase()).equals(OutputParameter.CONSOLE)) {
            groupPersonList.forEach((identity, persons) -> {
                String personInString = persons.stream().map(Person::toString).collect(Collectors.joining("\n"));
                System.out.println(identity + ", " + averageSalary.get(identity) + "\n" + personInString);
            });
            incorrectEntity.forEach(System.out::println);
            System.out.println("Работа завершена");
        } else {
            if (OutputParameter.valueOf(handler.get("output").toUpperCase()).equals(OutputParameter.FILE)) {
                StringBuilder sb = new StringBuilder();
                groupPersonList.forEach((identity, persons) -> {
                    String personInString = persons.stream().map(Person::toString).collect(Collectors.joining("\n"));
                            sb.append(identity).append(", ").append(averageSalary.get(identity)).append("\n").append(personInString).append("\n");
                        }
                );
                incorrectEntity.forEach(note -> sb.append(note).append("\n"));
                String result = sb.toString();
                String path = handler.get("path");
                WriterResultHandler wh = new WriterResultHandler();
                wh.write(path, result);
                System.out.println("Работа завершена");
            }
        }
    }

    /**
     * Метод проверяет наличие параметров для сортировки и если они есть, вызывает sortEmployeesByParameter(String sort, String order)
     * @return возвращает либо отсортировный список, либо нет, если запроса на сортировку не было
     */
    private List<Person> sortIfRequire() {
        if (handler.containsKey("sort")) {
            return sortEmployeesByParameter(handler.get("sort"), handler.get("order"));
        } else return personCreator.getListOfCorrectEntity();
    }

    /**
     * Метод производит сортировку списка работников, если в аргументах присутствует параметры --sort и --order
     * @param sort --sort=name - сортировка по имени --sort=salary - сортировка по окладу
     * @param order --order=asc - порядок сортировки по возрастающей --order=desc - порядок сортировки по убывающей
     * @return возвращает список отсортированных работников с менеджарами, которые не сортируются
     */
    private List<Person> sortEmployeesByParameter(String sort, String order) {
        List<Person> sortedEmployees = new ArrayList<>();
        SortParameter sortParameter = SortParameter.valueOf(sort.toUpperCase());
        OrderSort orderSort = OrderSort.valueOf(order.toUpperCase());

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
