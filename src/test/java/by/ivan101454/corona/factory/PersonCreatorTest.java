package by.ivan101454.corona.factory;


import by.ivan101454.corona.data.IllegalArgumentsFactory;
import by.ivan101454.corona.data.KindArgument;
import by.ivan101454.corona.entity.Person;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


class PersonCreatorTest {

    private final IllegalArgumentsFactory factory = new IllegalArgumentsFactory();

    @Test
    public void shouldNotThrowExceptionIfRoleNotExist() {
        //given
        String[][] arraysPerson = factory.createIllegalArguments(KindArgument.ROLE);
        String[][] arraysPersonTrue = factory.createIllegalArguments(KindArgument.ALL);
        PersonCreator personCreator = new PersonCreator(new SimpleFactory(), Arrays.stream(arraysPerson));
        PersonCreator personCreatorTrue = new PersonCreator(new SimpleFactory(), Arrays.stream(arraysPersonTrue));

        //when

        //then
        assertDoesNotThrow(personCreator::createListPerson);
        assertDoesNotThrow(personCreatorTrue::createListPerson);
    }

    @Test
    public void shouldCreateTwoEntity() {
        //given
        String[][] mas = factory.createIllegalArguments(KindArgument.TWO);
        String[] firstPerson = mas[0];
        String[] secondPerson = mas[1];
        String expectId = firstPerson[1];
        String expectIdSecond = secondPerson[1];
        String identificationExpect = firstPerson[4];
        String expectIdentSecondPerson = secondPerson[4];
        PersonCreator personCreator = new PersonCreator(new SimpleFactory(), Arrays.stream(mas));

        //when
        personCreator.createListPerson();
        Person first = personCreator.getListOfCorrectEntity().getFirst();
        Person second = personCreator.getListOfCorrectEntity().getLast();
        int idActual = first.getId();
        String identificationActual = first.getIdentification();
        int idActualSecond = second.getId();
        String identificationActualSecond = second.getIdentification();

        //then
        assertEquals(Integer.parseInt(expectId), idActual);
        assertEquals(Integer.parseInt(expectIdSecond), idActualSecond);
        assertEquals(identificationExpect, identificationActual);
        assertEquals(expectIdentSecondPerson, identificationActualSecond);
    }

    @Test
    public void shouldNotThrowExceptionIfIdNotValid() {
        //given
        String[][] mas = factory.createIllegalArguments(KindArgument.ID);
        PersonCreator personCreator = new PersonCreator(new SimpleFactory(), Arrays.stream(mas));

        //when

        //then
        assertDoesNotThrow(personCreator::createListPerson);
    }

    @Test
    public void shouldNotThrowExceptionIfNameIsEmpty() {
        //given
        String[][] mas = factory.createIllegalArguments(KindArgument.NAME);
        PersonCreator personCreator = new PersonCreator(new SimpleFactory(), Arrays.stream(mas));

        //when

        //then
        assertDoesNotThrow(personCreator::createListPerson);
    }

    @Test
    public void shouldNotThrowNotCorrectArgumentException() {
        //given
        String[][] mas = factory.createIllegalArguments(KindArgument.UNIQUE);
        PersonCreator personCreator = new PersonCreator(new SimpleFactory(), Arrays.stream(mas));

        //when


        //then
        assertDoesNotThrow(personCreator::createListPerson);

    }

    @Test
    public void shouldNotThrowNotCorrectArgumentSalary() {
        //given
        String[][] mas = factory.createIllegalArguments(KindArgument.SALARY);
        PersonCreator personCreator = new PersonCreator(new SimpleFactory(), Arrays.stream(mas));

        //when


        //then
        assertDoesNotThrow(personCreator::createListPerson);

    }

    @Test
    public void shouldNotThrowIfSalaryNegative() {
        //given
        String[][] mas = factory.createIllegalArguments(KindArgument.MINUS);
        PersonCreator personCreator = new PersonCreator(new SimpleFactory(), Arrays.stream(mas));

        //when

        //then
        assertDoesNotThrow(personCreator::createListPerson);

    }

    @Test
    public void shouldNotThrowIfIdentificationEmpty() {
        //given
        String[][] mas = factory.createIllegalArguments(KindArgument.IDENTIFICATION);
        PersonCreator personCreator = new PersonCreator(new SimpleFactory(), Arrays.stream(mas));

        //when

        //then
        assertDoesNotThrow(personCreator::createListPerson);

    }


}