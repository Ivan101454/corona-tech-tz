package by.ivan101454.corona.factory;

import by.ivan101454.corona.entity.Person;
import by.ivan101454.corona.enums.Role;
import by.ivan101454.corona.exception.NotCorrectArgumentException;
import by.ivan101454.corona.util.IncorrectStringCreator;
import by.ivan101454.corona.util.ValidatorUniqueId;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PersonCreator {

    private final SimpleFactory simpleFactory;
    private final List<String[]> list;
    private final ArrayList<Person> listOfCorrectEntity = new ArrayList<>();
    private final ArrayList<String> listOfIncorrectEntity = new ArrayList<>();
    private final IncorrectStringCreator incorrect = new IncorrectStringCreator();
    private final ValidatorUniqueId validatorUniqueId = new ValidatorUniqueId();


    public ArrayList<Person> getListOfCorrectEntity() {
        return listOfCorrectEntity;
    }

    public ArrayList<String> getListOfIncorrectEntity() {
        return listOfIncorrectEntity;
    }

    public PersonCreator(SimpleFactory simpleFactory, List<String[]> list) {
        this.simpleFactory = simpleFactory;
        this.list = list;
    }

    public void createListPerson() {
        listOfIncorrectEntity.add("Некорректные данные: ");
        list.forEach(mas -> {
            Role role;
            int id;
            String name;
            BigDecimal salary;
            String identification;
            String resultString = incorrect.createResultString(mas);
            if (mas.length != 5) {
                listOfIncorrectEntity.add(resultString);
                return;
            }
            if (mas[0] != null && !mas[0].isEmpty()) {
                try {
                    role = Role.valueOf(mas[0].trim().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.err.println("Ошибка: некорректное значение роли - " + mas[0] + ". Подробности: " + e.getMessage());
                    listOfIncorrectEntity.add(resultString);
                    return;
                }
            } else {
                try {
                    throw new NotCorrectArgumentException("Не передано значение роли");
                } catch (NotCorrectArgumentException e) {
                    System.err.println("Ошибка: некорректное значение роли - " + mas[0] + ". Подробности: " + e.getMessage());
                    listOfIncorrectEntity.add(resultString);
                    return;
                }
            }
            if (mas[1] != null && !mas[1].isEmpty()) {
                try {
                    id = Integer.parseInt(mas[1]);
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка: это не число" + ". Подробности: " + e.getMessage());
                    listOfIncorrectEntity.add(resultString);
                    return;
                }
                if (validatorUniqueId.checkUnique(id)) {
                    id = Integer.parseInt(mas[1]);
                } else {
                    try {
                        throw new NotCorrectArgumentException("Уже есть такое id");
                    } catch (NotCorrectArgumentException e) {
                        System.err.println("Ошибка: уже есть такое id - " + mas[1] + ". Подробности: " + e.getMessage());
                        listOfIncorrectEntity.add(resultString);
                        return;
                    }
                }
            } else {
                try {
                    throw new NotCorrectArgumentException("Не передано значение id");
                } catch (NotCorrectArgumentException e) {
                    System.err.println("Ошибка: некорректное значение id - " + mas[1] + ". Подробности: " + e.getMessage());
                    listOfIncorrectEntity.add(resultString);
                    return;
                }
            }

            if (mas[2] != null && !mas[2].isEmpty()) {
                name = mas[2];
            } else {
                try {
                    throw new NotCorrectArgumentException("Не передано значение имени");
                } catch (NotCorrectArgumentException e) {
                    System.err.println("Ошибка: некорректное значение имени - " + mas[2] + ". Подробности: " + e.getMessage());
                    listOfIncorrectEntity.add(resultString);
                    return;
                }
            }

            if (mas[3] != null && !mas[3].isEmpty()) {
                try {
                    salary = new BigDecimal(mas[3]).setScale(2, RoundingMode.HALF_UP);
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка: это не число" + ". Подробности: " + e.getMessage());
                    listOfIncorrectEntity.add(resultString);
                    return;
                }
                if (salary.signum() == -1) {
                    try {
                        throw new NotCorrectArgumentException("Отрицательное значение оклада");
                    } catch (NotCorrectArgumentException e) {
                        System.err.println("Ошибка: некорректное значение оклада - " + mas[3] + ". Подробности: " + e.getMessage());
                        listOfIncorrectEntity.add(resultString);
                        return;
                    }
                }
            } else {
                try {
                    throw new NotCorrectArgumentException("Не передано значение оклада");
                } catch (NotCorrectArgumentException e) {
                    System.err.println("Ошибка: некорректное значение зарплаты - " + mas[3] + ". Подробности: " + e.getMessage());
                    listOfIncorrectEntity.add(resultString);
                    return;
                }
            }

            if (mas[4] != null && !mas[4].isEmpty()) {
                identification = mas[4];
            } else {
                try {
                    throw new NotCorrectArgumentException("Не передано значение идентификатора");
                } catch (NotCorrectArgumentException e) {
                    System.err.println("Ошибка: некорректное значение идентификатора - " + mas[4] + ". Подробности: " + e.getMessage());
                    listOfIncorrectEntity.add(resultString);
                    return;
                }
            }

            Person person = simpleFactory.createPerson(role, mas[4]);
            person.setId(id);
            person.setName(name);
            person.setSalary(salary);
            person.setRole(role);
            listOfCorrectEntity.add(person);
        });
    }


}
