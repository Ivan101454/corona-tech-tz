package by.ivan101454.corona.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileHandler {

    public Stream<String[]> read() {
        File file = Paths.get("src", "main", "resources", "office.txt").toFile();

        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            return bf.lines().peek(String::trim).map(x -> x.split(","));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}