package by.ivan101454.corona;

import by.ivan101454.corona.factory.PersonCreator;
import by.ivan101454.corona.factory.SimpleFactory;
import by.ivan101454.corona.reader.FileHandler;
import by.ivan101454.corona.reader.InputArgumentsHandler;
import by.ivan101454.corona.result.ResultHandler;

public class Main {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        PersonCreator personCreator = new PersonCreator(new SimpleFactory(), fileHandler.read());
        personCreator.createListPerson();
        ResultHandler resultHandler = new ResultHandler(personCreator, new InputArgumentsHandler(args));
        resultHandler.getResult();
    }
}
