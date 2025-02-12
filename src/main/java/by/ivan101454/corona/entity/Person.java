package by.ivan101454.corona.entity;

import by.ivan101454.corona.enums.Role;

import java.math.BigDecimal;

public abstract class Person {

    private int id;
    private String name;
    private Role role;
    private BigDecimal salary;

    public abstract String getIdentification();
    public abstract void setIdentification(String identification);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        String stringRole = getRole().toString().toLowerCase();
        String upperCaseLiteral = stringRole.substring(0, 1).toUpperCase();
        String endLiteral = stringRole.substring(1);
        return upperCaseLiteral + endLiteral + ", " + getId() + ", " + getName() + ", " + getSalary() + "\n";
    }
}
