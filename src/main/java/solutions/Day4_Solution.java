package solutions;

import utils.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day4_Solution {
    public static void main(String[] args) {
        List<String> input = Util.readInput("/day04.txt");
        new Day4_Solution().runPartOne(input);
    }

    private void runPartOne(List<String> input) {
        String all = String.join("-", input);
        String[] bounds = all.split("-");
        int lowerBound = Integer.parseInt(bounds[0]);
        int upperBound = Integer.parseInt(bounds[1]);

        // get all of the numbers within the range
        ArrayList<Integer> allNumbersInRange = new ArrayList<>();
        IntStream.rangeClosed(lowerBound, upperBound).forEach(allNumbersInRange::add);

        int differentPasswords = 0;
        for (Integer i : allNumbersInRange) {

            int previousDigit = Integer.MAX_VALUE; // just a default
            boolean doubleDigit = false;
            boolean increases = true; // assumes this is true and will get set to false otherwise
            String numString = String.valueOf(i);
            for (int j = 0; j < numString.length(); j++) {
                char current = numString.charAt(j);
                int currentDigit = current - '0';
                if (j != 0) {
                    if (currentDigit < previousDigit) {
                        increases = false;
                        break;
                    } else if (currentDigit == previousDigit) {
                        doubleDigit = true;
                    }
                }
                previousDigit = currentDigit;
            }
            if (doubleDigit && increases) {
                differentPasswords++;
            }
        }
        System.out.println("Number of passwords: " + differentPasswords);
    }
}
