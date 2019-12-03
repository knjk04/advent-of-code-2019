package solutions;

import utils.Util;

import java.util.*;

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

        ArrayList<Coordinates> path1Coords = updatePathCoords(directions1);
        ArrayList<Coordinates> path2Coords = updatePathCoords(directions2);

        System.out.println("path1coords size = " + path1Coords.size());
        System.out.println("path2coords size = " + path2Coords.size());
        // check for overlapping paths
        for (int i = 0; i < path1Coords.size(); i++) {
            System.out.println("\ni = " + i);
            System.out.println("Path 1 coordinates = (" + path1Coords.get(i).x + ", " + path1Coords.get(i).y + ")");
            if (i < path2Coords.size()) {
                System.out.println("Path 2 coordinates = (" + path2Coords.get(i).x + ", " + path2Coords.get(i).y + ")");
            }
        }

        int minX = 0, maxX = 0;
        int minY = 0, maxY = 0;

        int maxSize = Math.max(path1Coords.size(), path2Coords.size());
        for (int i = 0; i < maxSize; i++) {
            if (path1Coords.size() > i) {
                int currentX = path1Coords.get(i).x, currentY = path1Coords.get(i).y;
                if (currentX < minX) {
                    minX = currentX;
                } else if (currentX > maxX) {
                    maxX = currentX;
                }

                if (currentY < minY) {
                    minY = currentY;
                } else if (currentY > maxY) {
                    maxY = currentY;
                }
            }

            if (path2Coords.size() > i) {
                int currentX = path2Coords.get(i).x, currentY = path2Coords.get(i).y;
                if (currentX < minX) {
                    minX = currentX;
                } else if (currentX > maxX) {
                    maxX = currentX;
                }
            }
        }

        System.out.println("minX = " + minX);
        System.out.println("minY = " + minY);
        System.out.println("maxX = " + maxX);
        System.out.println("maxY = " + maxY);


//        Vector<Vector<Coordinates>> matrix = new Vector<>();
        boolean[][] matrix = new boolean[maxX - minX][maxY - minY];

        path1Coords.sort((c1, c2) -> Math.min(c1.x, c2.x)); // sort by x (ascending order)
        path2Coords.sort((c1, c2) -> Math.min(c1.x, c2.x)); // sort by x (ascending order)

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

            }
        }


    }

    private ArrayList<Coordinates> updatePathCoords(ArrayList<String> directions) {
        int currentCoordX = 0, currentCoordY = 0;
        ArrayList<Coordinates> pathCoords = new ArrayList<>();
        for (String direction : directions) {
            Coordinates coordinates = new Coordinates();
            int moveBy = Integer.parseInt(direction.substring(1));
            char moveDirection = direction.charAt(0);

            if (moveDirection == 'L') {
                coordinates.x = (currentCoordX - moveBy);
                coordinates.y = currentCoordY;
            } else if (moveDirection == 'R') {
                coordinates.x = (currentCoordX + moveBy);
                coordinates.y = currentCoordY;
            } else if (moveDirection == 'U') {
                coordinates.x = currentCoordX;
                coordinates.y = (currentCoordY + moveBy);
            } else if (moveDirection == 'D') {
                coordinates.x = currentCoordX;
                coordinates.y = (currentCoordY - moveBy);
            }
            pathCoords.add(coordinates);
        }
        return pathCoords;
    }

    private static class Coordinates {
        private int x = 0, y = 0;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
