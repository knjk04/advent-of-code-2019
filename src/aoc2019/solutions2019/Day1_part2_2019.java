package aoc2019.solutions2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day1_part2_2019 {
    public static void main(String[] args) {
        int runningFuel = 0;
        try {
            String filePath = "/home/karan/git/advent-of-code/src/aoc2019/input2019/2019-day1-input";
            List<String> input = Files.readAllLines(Paths.get(filePath));

            for (String line : input) {
                int initialMass = Integer.parseInt(line);
                int fuelForCurrentModule = 0;

                int mass = initialMass;

                while (mass > 0) {
                    if (((mass/3) - 2) > 0) {
                        fuelForCurrentModule += (mass/3) - 2;
                        mass = (mass/3) - 2;
                    } else {
                        break;
                    }
                }

                runningFuel += fuelForCurrentModule;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("part 2: " + runningFuel);
    }
}
