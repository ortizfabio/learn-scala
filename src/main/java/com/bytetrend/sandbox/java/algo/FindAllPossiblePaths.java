package com.bytetrend.sandbox.java.algo;


import org.parboiled.common.StringUtils;

import java.util.Arrays;

/**
 * allowed paths are set to 1 and void paths set to 0 in the
 * initial matrix. The only possible moves are horizontal to the left,
 * vertical down or diagonal right-bottom.
 * <p>
 * Find all possible paths from the cell on top left to the cell on
 * the bottom right.
 *
 * Given this matrix there are 2 possible paths to follow:
 * right-right-down-down
 *
 * 1 1 1
 * 0 0 1
 * 0 0 1
 * Using the algorithm below the scratch matrix is setup with
 * these values. 2 is setup in the row 0 column 1 because there
 * are two paths on from the right and one from the right-bottom.
 *
 * 2 2 1
 * 0 0 1
 * 0 0 1
 * ------
 *
 */
public class FindAllPossiblePaths {
    public static int countPaths(int[][] map) {
        if (map == null) {
            throw new NullPointerException();
        }
        int rowDim = map.length;
        int columnDim = map[0].length;
        if (rowDim == 0 || columnDim == 0) {
            throw new IllegalArgumentException();
        }
        printMatrix(map);
        int[][] track = new int[rowDim][columnDim];

        //setup the lower right corner to the same value from m
        track[rowDim - 1][columnDim - 1] = map[rowDim - 1][columnDim - 1];
        //Setup the right most column
        for (int x = rowDim - 2; x > -1; x--) {
            if (map[x][columnDim - 1] == 1) {
                track[x][columnDim - 1] = track[x + 1][columnDim - 1];
            } else {
                track[x][columnDim - 1] = 0;
            }
        }
        //Setup the one before the bottom row
        for (int y = columnDim - 2; y > -1; y--) {
            if (map[rowDim - 1][y] == 1) {
                track[rowDim - 1][y] = track[rowDim - 1][y + 1];
            } else {
                track[rowDim - 1][y] = 0;
            }
        }

        //Now set the cells right and top from the two previous ones.
        for (int x = rowDim - 2; x > -1; x--) {
            for (int y = columnDim - 2; y > -1; y--) {
                if (map[x][y] == 1) {
                    //current cell= right         + bottom          + right/bottom (diagonal)
                    track[x][y] = track[x + 1][y] + track[x][y + 1] + track[x + 1][y + 1];
                } else {
                    track[x][y] = 0;
                }
            }
        }
        printMatrix(track);
        return track[0][0];
    }

    static private void printMatrix(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(repeat('-', 2 * matrix[0].length));
    }

    /**
     * Creates a string consisting of n times the given character.
     *
     * @param c the char
     * @param n the number of times to repeat
     * @return the string
     */
    public static String repeat(char c, int n) {
        char[] array = new char[n];
        Arrays.fill(array, c);
        return String.valueOf(array);
    }

    public static void main(String[] args) {
        int[][] array = {{1, 1, 1}, {0, 0, 1}, {0, 0, 1}};
        System.out.println((new FindAllPossiblePaths()).countPaths(array));
        int[][] array2 = {{1, 0, 0}, {1, 0, 1}, {1, 1, 1}};
        System.out.println((new FindAllPossiblePaths()).countPaths(array2));
        int[][] array3 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        System.out.println((new FindAllPossiblePaths()).countPaths(array3));
        int[][] array4 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 0}};
        System.out.println((new FindAllPossiblePaths()).countPaths(array4));
        int[][] array5 = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        System.out.println((new FindAllPossiblePaths()).countPaths(array5));

    }
}
