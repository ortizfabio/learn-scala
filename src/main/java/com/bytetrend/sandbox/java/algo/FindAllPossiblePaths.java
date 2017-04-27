package com.bytetrend.sandbox.java.algo;


public class FindAllPossiblePaths {
    public static int countPaths(int[][] map) {
        if (map == null) {
            throw new NullPointerException();
        }
        int xDim = map.length;
        int yDim = map[0].length;
        if (xDim == 0 || yDim == 0) {
            throw new IllegalArgumentException();
        }

        int[][] track = new int[xDim][yDim];

        //setup the lower right corner to the same value from m
        track[xDim - 1][yDim - 1] = map[xDim - 1][yDim - 1];
        //Setup the right most column
        for (int x = xDim - 2; x > -1; x--) {
            if (map[x][yDim - 1] == 1) {
                track[x][yDim - 1] = track[x + 1][yDim - 1];
            } else {
                track[x][yDim - 1] = 0;
            }
        }
        //Setup the one before the bottom row
        for (int y = yDim - 2; y > -1; y--) {
            if (map[xDim - 1][y] == 1) {
                track[xDim - 1][y] = track[xDim - 1][y + 1];
            } else {
                track[xDim - 1][y] = 0;
            }
        }

        //Now set the cells right and top from the two previous ones.
        for (int x = xDim - 2; x > -1; x--) {
            for (int y = yDim - 2; y > -1; y--) {
                if (map[x][y] == 1) {
                    //current cell= right         + bottom          + right/bottom (diagonal)
                    track[x][y] = track[x + 1][y] + track[x][y + 1] + track[x + 1][y + 1];
                } else {
                    track[x][y] = 0;
                }
            }
        }
        return track[0][0];
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
