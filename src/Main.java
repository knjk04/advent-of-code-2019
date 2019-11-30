import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        int resultingFrequency = 0;

        try {
            String filePath = "/home/karan/git/advent-of-code/src/input/2018-day1";

            for (String line : Files.readAllLines(Paths.get(filePath))) {
                char sign = line.charAt(0);
                int frequency = Integer.parseInt(line.substring(1));
                if (sign == '+') {
                    resultingFrequency += frequency;
                } else {
                    resultingFrequency -= frequency;
                }

            }
            System.out.println(resultingFrequency);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println("test");
    }
}

