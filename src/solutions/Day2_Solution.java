package solutions;

import utils.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2_Solution {
    public static void main(String[] args) {
        List<String> input = Util.readInput("/day02.txt");
        new Day2_Solution().runPartOne(input);
    }

    private void runPartOne(List<String> input) {
        // add the strings separated by commas into the ArrayList
        ArrayList<String> strings = new ArrayList<>();
        for (String line : input) {
            List<String> lineStrings = Arrays.asList(line.split(","));
            strings.addAll(lineStrings);
        }
        ArrayList<Integer> ints = convertArrayListStringToArrayListInteger(strings);

        ints.set(1, 12); // replace position 1
        ints.set(2, 2); // replace position 2

        // Opcode 1: adds the numbers from the two positions specifies by index 1 and 2 and stores the result in the
        // position specified by index 3
        // Opcode 2: multiplies the two inputs
        // Opcode 99: halt

        for (int i = 0; i < ints.size(); i++) {
            int firstOperandIndex = -1, secondOperandIndex = -1, resultIndex = -1;

            if ((ints.get(i) == 1 || ints.get(i) == 2)) {
                if ((i + 1) < ints.size()) {
                    firstOperandIndex = ints.get(i + 1);
//                    System.out.println("i = " + i);
//                    System.out.println("firstOperandIndex = " + firstOperandIndex);
                }
                if ((i + 2) < ints.size()) {
                    secondOperandIndex = ints.get(i + 2);
                }
                if ((i + 3) < ints.size()) {
                    resultIndex = ints.get(i + 3);
                }
            }

            if (ints.get(i) == 1 && (i+1) < ints.size() && (i+2) < ints.size()) {
//                System.out.println("i before sum = " + i);
                int sum = ints.get(firstOperandIndex) + ints.get(secondOperandIndex);
                ints.set(resultIndex, sum);
                i += 3; // 3 because i++ will add another, making i move 4 places in total
            } else if (ints.get(i) == 2 && (i+1) < ints.size() && (i+2) < ints.size()) {
                int product = ints.get(firstOperandIndex) * ints.get(secondOperandIndex);
                ints.set(resultIndex, product);
                i += 3; // 3 because i++ will add another, making i move 4 places in total
            } else if (ints.get(i) == 99) {
                break;
            }
        }

        System.out.println("\nOutput: ");
        for (Integer i : ints) {
            System.out.print(i + " ");
        }

        System.out.println("\n\nposition 0: " + ints.get(0));
    }

    private ArrayList<Integer> convertArrayListStringToArrayListInteger(ArrayList<String> strings) {
        ArrayList<Integer> ints = new ArrayList<>();
        for (String s : strings) {
            ints.add(Integer.parseInt(s));
        }
        return ints;
    }
}
