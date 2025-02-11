package by.ivan101454.corona.reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class WriterResultHandler {

    public void write(String path, String result) {
        File file = Paths.get(path).toFile();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
