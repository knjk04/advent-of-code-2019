package aoc2019.solutions2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day1_2019 {
    public static void main(String[] args) {
        int runningMass = 0;
        try {
            String filePath = "/home/karan/git/advent-of-code/src/aoc2019/input2019/2019-day1-input";
            List<String> input = Files.readAllLines(Paths.get(filePath));

            for (String line : input) {
                int currentMass = Integer.parseInt(line);
                runningMass += (currentMass/3) - 2;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("part 1: " + runningMass);
    }
}
