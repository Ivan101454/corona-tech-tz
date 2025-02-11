package by.ivan101454.corona.reader;


import by.ivan101454.corona.data.ArgumentsData;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class InputArgumentsHandlerTest {

    @Test
    public void shouldReceiveSortArguments() {
        //given
        InputArgumentsHandler handler = new InputArgumentsHandler();
        ArgumentsData data = new ArgumentsData();
        String[] args = data.getArrayOfAllArguments();
        String sortExpect = args[0].split("=")[1].trim();
        String orderExpect = args[1].split("=")[1].trim();
        String outputExpect = args[2].split("=")[1].trim();
        String pathExpect = args[3].split("=")[1].trim();

        //when
        Map<String, String> map = handler.handler(args);
        String sortActual = map.get("sort");
        String orderActual = map.get("order");
        String outputActual = map.get("output");
        String pathActual = map.get("path");

        //then
        assertEquals(sortExpect, sortActual);
        assertEquals(orderExpect, orderActual);
        assertEquals(outputExpect, outputActual);
        assertEquals(pathExpect, pathActual);
    }

    @Test
    public void shouldNotThrowExceptionWithNotCompletePath() {
        //given
        InputArgumentsHandler handler = new InputArgumentsHandler();
        ArgumentsData data = new ArgumentsData();
        String[] args = data.getArrayOfWithNotCompletePath();

        //when


        //then
        assertDoesNotThrow(() -> handler.handler(args));
    }

}