package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<String> readInput(String fileName) {
        File file = new File ("src/main/java/resources" + fileName);
        List<String> input = new ArrayList<>();
        try {
            input = Files.readAllLines(Paths.get(file.toURI()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
}
