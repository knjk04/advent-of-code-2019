package solutions;

import utils.Util;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Day3_Solution {

//    private int tempSteps = 0; // I don't think this is a good idea

    public static void main(String[] args) {
        List<String> input = Util.readInput("/day03.txt");
        new Day3_Solution().runPartOne(input);
    }

    private void runPartOne(List<String> input) {
        ArrayList<String> directions1 = new ArrayList<>();
        ArrayList<String> directions2 = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            List<String> lineStrings = Arrays.asList(input.get(i).split(","));
            if (i == 0) {
                directions1.addAll(lineStrings);
            } else {
                directions2.addAll(lineStrings);
            }
        }

        ArrayList<Coordinates> allPointsPath1 = calculateAllPoints(directions1);
        ArrayList<Coordinates> allPointsPath2 = calculateAllPoints(directions2);
        ArrayList<Coordinates> intersectionPoints = getIntersectionPoints(allPointsPath1, allPointsPath2);
//        shortestIntersectionManhattan(intersectionPoints);


        runPartTwo(intersectionPoints);
    }

    private void runPartTwo(ArrayList<Coordinates> intersectionPoints) {
        int minSteps = Integer.MAX_VALUE;

        for (Coordinates c : intersectionPoints) {
//            if (c.steps < minSteps) {
//                minSteps = c.steps;
//            }
            minSteps = Math.min(minSteps, c.steps);
        }

        // 9394: too high
        System.out.println("Min steps = " + minSteps);

    }

    private boolean noMatch(HashSet<Coordinates> hCoords, Coordinates c1) {
        return hCoords.stream().noneMatch(c -> (c.x == c1.x && c.y == c1.y));
    }

    private void shortestIntersectionManhattan(ArrayList<Coordinates> intersectionPoints) {
        int minManhattan = Integer.MAX_VALUE; // just a default
        for (Coordinates c : intersectionPoints) {
            // Manhattan: |x1 - x2| + |y1 - y2|
            int originX = 0;
            int originY = 0;
            int manhattan = Math.abs(originX - c.x) + Math.abs(originY - c.y);
            if (manhattan < minManhattan) {
                minManhattan = manhattan;
            }
//            minManhattan = Math.min(manhattan, minManhattan);
        }
        System.out.println("Minimum manhattan = " + minManhattan);
    }

    private ArrayList<Coordinates> getIntersectionPoints(ArrayList<Coordinates> allPointsPath1,
                                                         ArrayList<Coordinates> allPointsPath2) {
        ArrayList<Coordinates> intersectionPoints = new ArrayList<>();

        System.out.println("\nIntersection points:");
        for (Coordinates c1 : allPointsPath1) {
            for (Coordinates c2 : allPointsPath2) {
                if (c1.x == c2.x && c1.y == c2.y) {
                    Coordinates coordinates = new Coordinates();
                    coordinates.x = c1.x;
                    coordinates.y = c1.y;
                    coordinates.steps = c1.steps + c2.steps;
                    System.out.println("\nIntersection at (" + c1.x + ", " + c1.y + "). c1 steps = " +
                            c1.steps + ". c2.steps = " + c2.steps + "\n");
                    intersectionPoints.add(coordinates);
                    break;
                }
            }
        }
        return intersectionPoints;
    }

    private ArrayList<Coordinates> calculateAllPoints(ArrayList<String> directions1) {
        directions1.removeIf(Predicate.isEqual(""));
        int currentX = 0, currentY = 0;
        ArrayList<Coordinates> allPointsPath = new ArrayList<>();
        HashSet<Coordinates> visitedCoordinates = new HashSet<>();

        int steps = 0;
        System.out.println("\ncalculateAllPoints()");
        for (String s : directions1) {
            char moveDirection = s.charAt(0);
            int moveBy = Integer.parseInt(s.substring(1));

            System.out.println("\nmove = " + s);
            System.out.println("steps after move = " + (steps + moveBy));

//            tempSteps = steps;

            int finalSteps = steps;
            int finalCurrentX = currentX;
            int finalCurrentY = currentY;
            if (moveDirection == 'L') {
                IntStream.rangeClosed((currentX - moveBy), currentX).forEach(n -> {
                    Coordinates coordinates = new Coordinates();
                    coordinates.x = n;
                    coordinates.y = finalCurrentY;
//                    coordinates.steps = finalSteps;
                    coordinates.steps = finalSteps + Math.abs(n);
                    if ((!(coordinates.x == 0 && coordinates.y == 0)) && (noMatch(visitedCoordinates, coordinates))) {
                        allPointsPath.add(coordinates);
                        visitedCoordinates.add(coordinates);
                    } else if (!(coordinates.x == 0 && coordinates.y == 0)) {
//                        coordinates.steps = visitedCoordinates.
//                        System.out.println("match 1");
                        for (Coordinates c : visitedCoordinates) {
                            System.out.println("L match entered");
                            if (c.x == coordinates.x && c.y == coordinates.y) {
                                System.out.println("L match");
                                coordinates.steps = c.steps; // overwrite
                                allPointsPath.add(coordinates);
                                break;
                            }
                        }
                    }
                });
//                allPointsPath.add(coordinates);
                currentX -= moveBy;
            } else if (moveDirection == 'R') {
                IntStream.rangeClosed(currentX, (currentX + moveBy)).forEach(n -> {
                    Coordinates coordinates = new Coordinates();
                    coordinates.x = n;
                    coordinates.y = finalCurrentY;
//                    coordinates.steps = finalSteps;
                    coordinates.steps = finalSteps + Math.abs(n);

//                    System.out.println("\n" + s + " x = " + coordinates.x);
//                    System.out.println(s + " y = " + coordinates.y);
//                    System.out.println(s + " steps = " + coordinates.steps);

                    if ((!(coordinates.x == 0 && coordinates.y == 0)) && (noMatch(visitedCoordinates, coordinates))) {
                        visitedCoordinates.add(coordinates);
                        allPointsPath.add(coordinates);
                    } else if (!(coordinates.x == 0 && coordinates.y == 0)) {
//                        coordinates.steps = visitedCoordinates.
                        for (Coordinates c : visitedCoordinates) {
                            System.out.println("R match entered");
                            if (c.x == coordinates.x && c.y == coordinates.y) {
                                System.out.println("match");
                                coordinates.steps = c.steps; // overwrite
                                allPointsPath.add(coordinates);
                                break;
                            }
                        }
                    }
//                    allPointsPath.add(coordinates);
                });
                currentX += moveBy;
            } else if (moveDirection == 'U') {
                IntStream.rangeClosed(currentY, (currentY + moveBy)).forEach(n -> {
                    Coordinates coordinates = new Coordinates();
                    coordinates.x = finalCurrentX;
                    coordinates.y = n;
//                    coordinates.steps = finalSteps;
                    coordinates.steps = finalSteps + Math.abs(n);
//                    System.out.println("\n" + s + " x = " + coordinates.x);
//                    System.out.println(s + " y = " + coordinates.y);
//                    System.out.println(s + " steps = " + coordinates.steps);
                    if ((!(coordinates.x == 0 && coordinates.y == 0)) && (noMatch(visitedCoordinates, coordinates))) {
                        allPointsPath.add(coordinates);
                        visitedCoordinates.add(coordinates);
                    } else if (!(coordinates.x == 0 && coordinates.y == 0)) {
//                        coordinates.steps = visitedCoordinates.
                        for (Coordinates c : visitedCoordinates) {
                            System.out.println("U match entered");
                            if (c.x == coordinates.x && c.y == coordinates.y) {
                                System.out.println("match");
                                coordinates.steps = c.steps; // overwrite
                                allPointsPath.add(coordinates);
                                break;
                            }
                        }
                    }
//                    allPointsPath.add(coordinates);
                });
                currentY += moveBy;
            } else if (moveDirection == 'D') {
                IntStream.rangeClosed((currentY - moveBy), currentY).forEach(n -> {
                    Coordinates coordinates = new Coordinates();
                    coordinates.x = finalCurrentX;
                    coordinates.y = n;
//                    coordinates.steps = finalSteps;
                    coordinates.steps = finalSteps + Math.abs(n);
//                    System.out.println("\n" + s + " x = " + coordinates.x);
//                    System.out.println(s + " y = " + coordinates.y);
//                    System.out.println(s + " steps = " + coordinates.steps);
                    if ((!(coordinates.x == 0 && coordinates.y == 0)) && (noMatch(visitedCoordinates, coordinates))) {
                        visitedCoordinates.add(coordinates);
                        allPointsPath.add(coordinates);
                    } else if (!(coordinates.x == 0 && coordinates.y == 0)) {
//                        coordinates.steps = visitedCoordinates.
                        for (Coordinates c : visitedCoordinates) {
                            System.out.println("D match entered");
                            if (c.x == coordinates.x && c.y == coordinates.y) {
                                System.out.println("match");
                                coordinates.steps = c.steps; // overwrite
                                allPointsPath.add(coordinates);
                                break;
                            }
                        }
                    }
//                    allPointsPath.add(coordinates);
                });
                currentY -= moveBy;
            }
            steps += Math.abs(moveBy);
            System.out.println("\n" + s + ": updated steps = " + steps);
        }

        for (Coordinates c : visitedCoordinates) {
            System.out.println("\nvisited:");
            System.out.println("x = " + c.x);
            System.out.println("visited:");
            System.out.println("y = " + c.y);
            System.out.println("visited. steps = " + c.steps);
        }

        return allPointsPath;
    }


    private static class Coordinates {
        int x = 0, y = 0;
        private int steps = 0; // for part 2
    }
}