package solutions;

import utils.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5_Solution {
    public static void main(String[] args) {
        List<String> input = Util.readInput("/day05.txt");
//        new Day2_Solution().runPartOne(input, 12, 2);
//        new Day2_Solution().runPartTwo(input);

//        int i = 1002;
//        int i = 4;
//        System.out.println("i/100 = " + (i/100));
//        System.out.println("i%100 = " + (i%100));

    }

    private void runPartOne(List<String> program, int position1, int position2, int input) {
        // add the strings separated by commas into the ArrayList
        ArrayList<String> strings = new ArrayList<>();
        for (String line : program) {
            List<String> lineStrings = Arrays.asList(line.split(","));
            strings.addAll(lineStrings);
        }
        ArrayList<Integer> ints = convertArrayListStringToArrayListInteger(strings);

        ints.set(1, position1); // replace position 1
        ints.set(2, position2); // replace position 2

        // Opcode 1: adds the numbers from the two positions specifies by index 1 and 2 and stores the result in the
        // position specified by index 3
        // Opcode 2: multiplies the two inputs
        // Opcode 99: halt

        for (int i = 0; i < ints.size(); i++) {
            int firstOperandIndex = -1, secondOperandIndex = -1, resultIndex = -1;

            ints.set(i, ints.get(i) % 100); // get last two digits

            if ((ints.get(i) == 1 || ints.get(i) == 2)) {
                if ((i + 1) < ints.size()) {
                    firstOperandIndex = ints.get(i + 1);
                }
                if ((i + 2) < ints.size()) {
                    secondOperandIndex = ints.get(i + 2);
                }
                if ((i + 3) < ints.size()) {
                    resultIndex = ints.get(i + 3);
                }
            }

            if (ints.get(i) == 1 && (i + 1) < ints.size() && (i + 2) < ints.size()) {
//                System.out.println("i before sum = " + i);
                int sum = ints.get(firstOperandIndex) + ints.get(secondOperandIndex);
                ints.set(resultIndex, sum);
                i += 3; // 3 because i++ will add another, making i move 4 places in total
            } else if (ints.get(i) == 2 && (i + 1) < ints.size() && (i + 2) < ints.size()) {
                int product = ints.get(firstOperandIndex) * ints.get(secondOperandIndex);
                ints.set(resultIndex, product);
                i += 3; // 3 because i++ will add another, making i move 4 places in total
            } else if (ints.get(i) == 3) {
                // save instruction
                if ((i + 1) < ints.size()) {
                    int savePosition = ints.get(i + 1);
                    if (savePosition < ints.size()) {
                        ints.set(savePosition, input);
                    }
                    i++;
                }
            } else if (ints.get(i) == 4) {
                // display instruction
                System.out.println("(opcode 4) input: " + input);
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

    private void runPartTwo(List<String> input) {
        // add the strings separated by commas into the ArrayList
        ArrayList<String> strings = new ArrayList<>();
        for (String line : input) {
            List<String> lineStrings = Arrays.asList(line.split(","));
            strings.addAll(lineStrings);
        }

        ArrayList<Integer> ints = convertArrayListStringToArrayListInteger(strings);
        ArrayList<Integer> intsCopy = new ArrayList<>(ints);
        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j <= 99; j++) {
                ints.set(1, i); // replace position 1
                ints.set(2, j); // replace position 2

                // Opcode 1: adds the numbers from the two positions specifies by index 1 and 2 and stores the result in the
                // position specified by index 3
                // Opcode 2: multiplies the two inputs
                // Opcode 99: halt

                for (int k = 0; k < ints.size(); k++) {
                    int firstOperandIndex = -1, secondOperandIndex = -1, resultIndex = -1;

                    if ((ints.get(k) == 1 || ints.get(k) == 2)) {
                        if ((k + 1) < ints.size()) {
                            firstOperandIndex = ints.get(k + 1);
                        }
                        if ((k + 2) < ints.size()) {
                            secondOperandIndex = ints.get(k + 2);
                        }
                        if ((k + 3) < ints.size()) {
                            resultIndex = ints.get(k + 3);
                        }
                    }

                    if (ints.get(k) == 1 && (k + 1) < ints.size() && (k + 2) < ints.size() &&
                            firstOperandIndex < ints.size() && resultIndex < ints.size() && secondOperandIndex < ints.size()) {
                        int sum = ints.get(firstOperandIndex) + ints.get(secondOperandIndex);
                        ints.set(resultIndex, sum);
                        k += 3; // 3 because k++ will add another, making k move 4 places in total
                    } else if (ints.get(k) == 2 && (k + 1) < ints.size() && (k + 2) < ints.size() &&
                            resultIndex < ints.size() && firstOperandIndex < ints.size() && secondOperandIndex < ints.size()) {
                        int product = ints.get(firstOperandIndex) * ints.get(secondOperandIndex);
                        ints.set(resultIndex, product);
                        k += 3; // 3 because i++ will add another, making i move 4 places in total
                    } else if (ints.get(k) == 99) {
                        break;
                    }
                }

                System.out.println("\nOutput: ");
                for (Integer o : ints) {
                    System.out.print(o + " ");
                }

                System.out.println("\n\nposition 0: " + ints.get(0));

                if (ints.get(0) == 19690720) {
                    int noun = ints.get(1);
                    int verb = ints.get(2);
                    System.out.println("100 * noun + verb = " + ((100 * noun) + verb));
                    return;
                } else {
                    ints.clear();
                    ints.addAll(intsCopy);
                }
            }
        }
    }

    private ArrayList<Integer> convertArrayListStringToArrayListInteger(ArrayList<String> strings) {
        ArrayList<Integer> ints = new ArrayList<>();
        for (String s : strings) {
            if (s != "") {
                ints.add(Integer.parseInt(s));
            }
        }
        return ints;
    }
}

