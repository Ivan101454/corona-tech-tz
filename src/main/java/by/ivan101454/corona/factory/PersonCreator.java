package by.ivan101454.corona.factory;

import by.ivan101454.corona.entity.Person;
import by.ivan101454.corona.enums.Role;
import by.ivan101454.corona.util.IncorrectStringCreator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PersonCreator {

    private final SimpleFactory simpleFactory;
    private final Stream<String[]> stream;
    private final ArrayList<Person> listOfCorrectEntity = new ArrayList<>();
    private final ArrayList<String> listOfIncorrectEntity = new ArrayList<>();
    private final IncorrectStringCreator incorrect = new IncorrectStringCreator();


    public ArrayList<Person> getListOfCorrectEntity() {
        return listOfCorrectEntity;
    }

    public ArrayList<String> getListOfIncorrectEntity() {
        return listOfIncorrectEntity;
    }

    public PersonCreator(SimpleFactory simpleFactory, Stream<String[]> stream) {
        this.simpleFactory = simpleFactory;
        this.stream = stream;
    }

    public void createListPerson() {

        stream.forEach(mas -> {
            Role role = null;
            String resultString = incorrect.createResultString(mas);
            if (mas.length != 5) {
                listOfIncorrectEntity.add(resultString);
                return;
            }
            if (mas[0] != null) {
                try {
                    role = Role.valueOf(mas[0].trim().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.err.println("Ошибка: некорректное значение роли - " + mas[0] + ". Подробности: " + e.getMessage());
                    listOfIncorrectEntity.add(resultString);
                    return;
                }
            } else {
                listOfIncorrectEntity.add(resultString);
                return;
            }
            if (mas[1] != null) {

            } else {
                listOfIncorrectEntity.add(resultString);
                return;
            }
            int id = Integer.parseInt(mas[1]);
            String name = mas[2];
            BigDecimal salary = new BigDecimal(mas[3]);
            Person person = simpleFactory.createPerson(role, mas[4]);
            person.setId(id);
            person.setName(name);
            person.setSalary(salary);
            listOfCorrectEntity.add(person);
        });
    }


}
