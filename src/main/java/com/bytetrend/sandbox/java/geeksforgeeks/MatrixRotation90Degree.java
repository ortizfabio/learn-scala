package com.bytetrend.sandbox.java.geeksforgeeks;

// Java program to rotate a matrix by 90 degrees
import java.io.*;

class MatrixRotation90Degree
{
    // An Inplace function to rotate a N x N matrix
    // by 90 degrees in anti-clockwise direction
    static void rotateMatrix(int N, int mat[][])
    {
        int i = 0;
        int j = 0;
        int last = N -1;
        // Consider all squares one by one
        for (int x = 0; x < N / 2; x++)
        {
            // Consider elements in group of 4 in
            // current square
            for (int y = x; y <last - x; y++)
            {
                // store current cell in temp variable
                int temp = mat[x][y];

                // move values from right to top
                mat[x][y] = mat[y][last-x];

                // move values from bottom to right
                mat[y][last-x] = mat[last-x][last-y];

                // move values from left to bottom
                mat[last- x][last-y] = mat[last-y][x];

                // assign temp to left
                mat[last-y][x] = temp;
                displayMatrix(mat.length,mat);
            }
        }
    }

    // Function to print the matrix
    static void displayMatrix(int N, int mat[][])
    {
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.print(" " + mat[i][j]);

            System.out.print("\n");
        }
        System.out.print("\n");
    }

    /* Driver program to test above functions */
    public static void main (String[] args)
    {
/*
        // Test Case 1
        int mat[][] =
                {
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 16}
                };
*/

        // Tese Case 2
         int mat[][] = {
                            {1, 2, 3},
                            {4, 5, 6},
                            {7, 8, 9}
                        };


        // Tese Case 3
        /*int mat[][] = {
                        {1, 2},
                        {4, 5}
                    };*/

        // displayMatrix(mat);

        rotateMatrix(mat.length,mat);

        // Print rotated matrix
        displayMatrix(mat.length,mat);
    }
}

