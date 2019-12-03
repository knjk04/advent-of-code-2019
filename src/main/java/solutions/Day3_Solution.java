package solutions;

import utils.Util;
import java.util.*;
import java.util.stream.IntStream;

public class Day3_Solution {
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

        shortestIntersectionManhattan(intersectionPoints);
    }

    private void shortestIntersectionManhattan(ArrayList<Coordinates> intersectionPoints) {
        final int originX = 0, originY = 0;
        int minManhattan = Integer.MAX_VALUE; // just a default
        for (Coordinates c : intersectionPoints) {
            // Manhattan: |a - c| + |b - d|
            int manhattan = Math.abs(originX - c.x) + Math.abs(originY - c.y);
            if (manhattan < minManhattan && (c.x != originX) && (c.y != originY)) {
                minManhattan = manhattan;
                System.out.println("x = " + c.x);
                System.out.println("y = " + c.y);
            }
        }
        System.out.println("intersectionPoints size = " + intersectionPoints.size());
        System.out.println("Minimum manhattan = " + minManhattan);
    }

    private ArrayList<Coordinates> getIntersectionPoints(ArrayList<Coordinates> allPointsPath1,
                                                         ArrayList<Coordinates> allPointsPath2) {
        ArrayList<Coordinates> intersectionPoints = new ArrayList<>();
        for (Coordinates c1 : allPointsPath1) {
            for (Coordinates c2 : allPointsPath2) {
                if (c1.x == c2.x && c1.y == c2.y) {
                    intersectionPoints.add(c1);
//                    System.out.println("c1 intersection x = " + c1.x);
//                    System.out.println("c1 intersection y = " + c1.y);
//                    System.out.println("c2 intersection x = " + c2.x);
//                    System.out.println("c2 intersection y = " + c2.y);
                    break;
                }
            }
        }
        return intersectionPoints;
    }

    private ArrayList<Coordinates> calculateAllPoints(ArrayList<String> directions1) {
        int currentX = 0, currentY = 0;
        ArrayList<Coordinates> allPointsPath = new ArrayList<>();
        for (String s : directions1) {
            char moveDirection = s.charAt(0);
            int moveBy = Integer.parseInt(s.substring(1));

            int finalCurrentX = currentX;
            int finalCurrentY = currentY;
            if (moveDirection == 'L') {
                IntStream.rangeClosed(currentX, (currentX - moveBy)).forEach(n -> {
                    Coordinates coordinates = new Coordinates();
                    coordinates.x = n;
                    coordinates.y = finalCurrentY;
                    allPointsPath.add(coordinates);
                });
                currentX -= moveBy;
            } else if (moveDirection == 'R') {
                IntStream.rangeClosed(currentX, (currentX + moveBy)).forEach(n -> {
                    Coordinates coordinates = new Coordinates();
                    coordinates.x = n;
                    coordinates.y = finalCurrentY;
                    allPointsPath.add(coordinates);
                });
                currentX += moveBy;
            } else if (moveDirection == 'U') {
                IntStream.rangeClosed(currentY, (currentY + moveBy)).forEach(n -> {
                    Coordinates coordinates = new Coordinates();
                    coordinates.x = finalCurrentX;
                    coordinates.y = n;
                    allPointsPath.add(coordinates);
                });
                currentY += moveBy;
            } else if (moveDirection == 'D') {
                IntStream.rangeClosed(currentY, (currentY - moveBy)).forEach(n -> {
                    Coordinates coordinates = new Coordinates();
                    coordinates.x = finalCurrentX;
                    coordinates.y = n;
                    allPointsPath.add(coordinates);
                });
                currentY -= moveBy;
            }
        }
        return allPointsPath;
    }

    private static class Coordinates {
        private int x = 0, y = 0;
    }
}