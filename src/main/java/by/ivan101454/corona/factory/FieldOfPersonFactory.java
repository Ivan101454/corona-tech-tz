package by.ivan101454.corona.factory;

import by.ivan101454.corona.enums.Role;

import java.math.BigDecimal;
import java.util.UUID;

public class FieldOfPersonFactory implements IFieldFactory {

    private UUID id;
    private String name;
    private Role role;
    private BigDecimal salary;
    private String identification;


    @Override
    public UUID createUUID(String uuid) {
        return UUID.fromString(uuid);
    }

    @Override
    public String createName(String name) {
        return name;
    }

    @Override
    public Role createRole(String role) {
        return Role.valueOf(role);
    }

    @Override
    public BigDecimal createSalary(String salary) {
        return new BigDecimal(salary);
    }

    @Override
    public String createIdentity(String identity) {
        return identity;
    }
}
