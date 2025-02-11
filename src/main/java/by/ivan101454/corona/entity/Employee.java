package by.ivan101454.corona.entity;

import java.util.UUID;

public class Employee extends Person {

    private int identificationManager;

    public Employee(String identificationManager) {
        this.identificationManager = Integer.parseInt(identificationManager);
    }

    private void setIdentificationManager(int identificationManager) {
        this.identificationManager = identificationManager;
    }

    private int getIdentificationManager() {
        return identificationManager;
    }

    @Override
    public String getIdentification() {
        return String.valueOf(getIdentificationManager());
    }

    @Override
    public void setIdentification(String identification) {
        setIdentificationManager(Integer.parseInt(identification));
    }
}
