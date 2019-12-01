package solutions;

import utils.Utils;

import java.util.List;

public class Day1_Solution {
    public static void main(String[] args) {
        List<String> input = Utils.readInput("/day01.txt");
        new Day1_Solution().runPart1(input);
        new Day1_Solution().runPart2(input);
    }

    private void runPart1(List<String> input) {
        int runningMass = 0;

        for (String line : input) {
            int currentMass = Integer.parseInt(line);
            runningMass += (currentMass / 3) - 2;
        }
        System.out.println("part 1: " + runningMass);

    }

    private void runPart2(List<String> input) {
        int runningFuel = 0;

        for (String line : input) {
            int initialMass = Integer.parseInt(line);
            int fuelForCurrentModule = 0;

            int mass = initialMass;

            while (mass > 0) {
                if (((mass / 3) - 2) > 0) {
                    fuelForCurrentModule += (mass / 3) - 2;
                    mass = (mass / 3) - 2;
                } else {
                    break;
                }
            }

            runningFuel += fuelForCurrentModule;
        }
        System.out.println("part 2: " + runningFuel);
    }
}
