package by.ivan101454.corona.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    /**
     * Метода запрашивает путь к файлу с данными в консоль и затем читает его.
     * @return массив строк, прочитанных из файла
     */
    public List<String[]> read() {
        String path;
        System.out.println("!!!Ввести в консоль абсолютный путь к файлу с данными.!!!");
        try (Scanner scanner = new Scanner(System.in)) {
            path = scanner.nextLine().trim().replace("\"", "");
        }
        File file = Paths.get(path).toFile();

        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            return bf.lines().map(String::trim).map(x -> x.split(",")).toList();
        } catch (IOException e) {
            System.err.println("Не удалось прочитать данные. Подробности: " + e.getMessage());
            throw new RuntimeException();
        }
    }

}