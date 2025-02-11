package by.ivan101454.corona.reader;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class FileHandlerTest {

    @Test
    public void shouldReadFile() throws IOException {
        //given
        FileHandler fh = new FileHandler();

        //when


        //then
        assertDoesNotThrow(fh::read);

    }

}