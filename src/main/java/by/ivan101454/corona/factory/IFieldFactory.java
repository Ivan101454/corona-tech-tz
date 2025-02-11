package by.ivan101454.corona.factory;

import by.ivan101454.corona.enums.Role;

import java.math.BigDecimal;
import java.util.UUID;

public interface IFieldFactory {

    public UUID createUUID(String uuid);
    public String createName(String name);
    public Role createRole(String role);
    public BigDecimal createSalary(String salary);
    public String createIdentity(String identity);
}
