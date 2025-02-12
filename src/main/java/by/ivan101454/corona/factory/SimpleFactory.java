package by.ivan101454.corona.factory;

import by.ivan101454.corona.entity.Employee;
import by.ivan101454.corona.entity.Manager;
import by.ivan101454.corona.entity.Person;
import by.ivan101454.corona.enums.Role;

public class SimpleFactory {

    /**
     * Простая фабрик, создает работника по его роли
     * @param role менеджер или работник
     * @param identification название отдела для менеджера и идентификатор своего менеджера для работника
     * @return сущность Person
     */
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
