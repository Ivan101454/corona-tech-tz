package by.ivan101454.corona.entity;

public class Manager extends Person {

    private String deportment;

    public Manager(String deportment) {
        super();
        this.deportment = deportment;
    }

    private String getDeportment() {
        return deportment;
    }

    private void setDeportment(String deportment) {
        this.deportment = deportment;
    }

    @Override
    public String getIdentification() {
        return getDeportment();
    }

    @Override
    public void setIdentification(String identification) {
        setDeportment(identification);
    }
}
