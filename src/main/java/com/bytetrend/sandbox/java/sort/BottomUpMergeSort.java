package com.bytetrend.sandbox.java.sort;

import java.util.Arrays;

import static java.lang.Math.min;

public class BottomUpMergeSort {

    static public void bottomUpSort(int[] a, int[] b, int n) {

        // Each 1-element run in A is already "sorted".
        // Make successively longer sorted runs of length 2, 4, 8, 16... until whole array is sorted.
        for (int width = 1; width < n; width = 2 * width) {
            for (int i = 0; i < n; i = i + 2 * width) {
                int iLeft = i;
                int iRight = min(i + width, n);
                int iEnd = min(i+2*width, n);
                // Merge two runs: A[i:i+width-1] and A[i+width:i+2*width-1] to B[]
                // or copy A[i:n-1] to B[] ( if(i+width >= n) )
                bottomUpMerge(a, iLeft, iRight, iEnd, b);
            }
            // Now work array B is full of runs of length 2*width.
            // Copy array B to array A for next iteration.
            // A more efficient implementation would swap the roles of A and B.
            System.arraycopy(b, 0, a, 0, n);
            // Now array A is full of runs of length 2*width.
        }
    }

    //  Left run is A[iLeft :iRight-1].
    // Right run is A[iRight:iEnd-1  ].
    public static void bottomUpMerge(int[] a, int iLeft, int iRight, int iEnd, int[] b) {
        int i = iLeft;
        int j = iRight;
        // While there are elements in the left or right runs...
        for (int k = iLeft; k < iEnd; k++) {
            // If left run head exists and is <= existing right run head.
            if (i < iRight && (j >= iEnd || a[i] <= a[j])) {
                b[k] = a[i];
                i = i + 1;
            } else {
                b[k] = a[j];
                j = j + 1;
            }
        }

    }

    public static void main(String[] args) {
        int[] inputArr = {45, 23, 11, 89, 77, 98, 4, 28, 65, 43};
        int[] outputArr =  new int[inputArr.length];
        System.out.println(Arrays.toString(inputArr));
        bottomUpSort(inputArr,outputArr, inputArr.length);
        System.out.println(Arrays.toString(outputArr));
    }
}
