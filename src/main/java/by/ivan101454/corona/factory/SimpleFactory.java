package by.ivan101454.corona.factory;

import by.ivan101454.corona.entity.Employee;
import by.ivan101454.corona.entity.Manager;
import by.ivan101454.corona.entity.Person;
import by.ivan101454.corona.enums.Role;

import java.util.UUID;

public class SimpleFactory {

    public Person createPerson(Role role, String identification) {
        Person person = null;
        if (role == Role.MANAGER) {
            person = new Manager(identification);
        }
        if (role == Role.EMPLOYEE) {
            person = new Employee(identification);
        }
        return person;
    }
}
