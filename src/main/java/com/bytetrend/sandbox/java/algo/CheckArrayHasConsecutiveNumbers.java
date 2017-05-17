package com.bytetrend.sandbox.java.algo;

import java.util.IntSummaryStatistics;

/**
 * Check if array elements are consecutive
 * Given an unsorted array of numbers, write a function that returns true if array consists of consecutive numbers.
 * <p>
 * Method 1 (Use Sorting)
 * 1) Sort all the elements.
 * 2) Do a linear scan of the sorted array. If the difference between current element and next element is anything other than 1, then return false. If all differences are 1, then return true.
 * <p>
 * Time Complexity: O(nLogn)
 * <p>
 * Method 2 (Use visited array)
 * The idea is to check for following two conditions. If following
 * two conditions are true, then return true.
 * 1) max – min + 1 = n where max is the maximum element in array,
 * min is minimum element in array and n is the number of elements
 * in array.
 * 2) All elements are distinct.
 * <p>
 * To check if all elements are distinct, we can create a visited[]
 * array of size n. We can map the ith element of input array arr[]
 * to visited array by using arr[i] – min as index in visited[].
 * <p>
 * http://www.geeksforgeeks.org/check-if-array-elements-are-consecutive/
 * http://algorithms.tutorialhorizon.com/check-if-array-is-consecutive-integers/
 */
public class CheckArrayHasConsecutiveNumbers {

    /**
     * Method 3 (Mark visited array elements as negative)
     * This method is O(n) time complexity and O(1) extra space, but
     * it changes the original array and it works only if all numbers
     * are positive.
     * <p>
     * We can get the original array by adding an extra
     * step though. It is an extension of method 2 and it has the same two steps.
     * 1) max – min + 1 = n where max is the maximum element in array, min
     * is minimum element in array and n is the number of elements in array.
     * 2) All elements are distinct.
     * <p>
     * In this method, the implementation we modify the input array arr[] to keep track of visited elements. The idea is to traverse the array and for each index i (where 0 <= i < n), make arr[arr[i] – min]] as a negative value. If we see a negative value again then there is repetition.
     * This method modifies the original array by replacing
     * numbers with the indexes according to the distance from
     * the minimum number starting with 1.
     * <p>
     * Then it check in a loop that the abs value store in
     * the arrays is greater than 0
     *
     * @param arrA
     * @return
     */
    static public Boolean withoutAuxArray(int[] arrA) {
        //this method with work if numbers are non negative
        IntSummaryStatistics statistics = findStatistics(arrA);

        //This will trigger for arrays with non-duplicates that
        //are not sequential
        if (arrA.length != statistics.getMax() - statistics.getMin() + 1)
            return false;
        for (int i = 0; i < arrA.length; i++) {
            int index;
            if (arrA[i] < 0)
                index = -arrA[i] - statistics.getMin();
            else
                index = arrA[i] - statistics.getMin();

            if (index < arrA.length && arrA[index] > 0) {
                arrA[index] = -arrA[index];
            } else {
                return false;
            }
        }
        return true;
    }

    static public Boolean withAuxArray(int[] arrA) {
        // this method works even if numbers are negative
        boolean[] visited = new boolean[arrA.length];
        IntSummaryStatistics statistics = findStatistics(arrA);

        if (arrA.length != statistics.getMax() - statistics.getMin() + 1)
            return false;

        //This will trigger for arrays with non-duplicates that
        //are not sequential
        for (int i = 0; i < arrA.length; i++) {
            int index = arrA[i] - statistics.getMin();
            //If one of the numbers is not in sequence and the range of max-min+1 == array.length
            //is not done it will throw an IndexOutBoundException here.
            if (index >= arrA.length || visited[index] != false)
                return false;
            visited[index] = true;
        }
        // If we have reached till here means , we satisfied all the requirements
        return true;
    }

    /**
     * Find minimum and maximum values in array.
     *
     * @param arrA
     * @return
     */
    static private IntSummaryStatistics findStatistics(int[] arrA) {
        // find minimum and maximum in array
        IntSummaryStatistics summary = new IntSummaryStatistics();
        for (int i = 0; i < arrA.length; i++) {
            summary.accept(arrA[i]);
        }
        return summary;
    }

    public static void main(String[] args) throws java.lang.Exception {
        int[] arrA = {21, 24, 22, 26, 23, 25};
        int[] arrB = {21, 24, 22, 26, 28, 25};
        int[] arrC = {11, 10, 12, 14, 13};
        int[] arrD = {11, 10, 14, 13};

        int[] arrE = {-21, -24, -22, -26, -23, -25};
        int[] arrF = {-21, -24, -22, -26, -28, -25};
        int[] arrG = {-11, -10, -12, -14, -13};
        int[] arrH = {-11, -10, -14, -13};

        int[] arrI = {-4, -2, -1, -3, 0, 1};
        int[] arrJ = {-3, -2, 0, 1, 3, 2};
        int[] arrK = {-2, -1, 1, 2, 0};
        int[] arrL = {-3, -1, -1, -2};
        //Duplicate test
        int[] arrM = {21, 24, 22, 26, 23, 24};
        int[] arrN = {-21, -24, -22, -26, -23, -24};
        int[] arrO = {21, 21, 21};
        int[] arrP = {-21, -21, -21};


        System.out.println("Aux Array should be true " + withAuxArray(arrA));
        System.out.println("Aux Array should be false " + withAuxArray(arrB));
        System.out.println("Aux Array should be true " + withAuxArray(arrE));
        System.out.println("Aux Array should be false " + withAuxArray(arrF));
        System.out.println("Aux Array should be true " + withAuxArray(arrI));
        System.out.println("Aux Array should be false " + withAuxArray(arrJ));
        System.out.println("Aux Array should be false " + withAuxArray(arrM));
        System.out.println("Aux Array should be false " + withAuxArray(arrN));


        System.out.println("In place should be true " + withoutAuxArray(arrC));
        System.out.println("In place should be false " + withoutAuxArray(arrD));
 //         System.out.println("In place should be true " + withoutAuxArray(arrG));
 //         System.out.println("In place should be false " + withoutAuxArray(arrH));
 //       System.out.println("In place should be true " + withoutAuxArray(arrK));
        System.out.println("In place should be false " + withoutAuxArray(arrL));
        System.out.println("In place should be false " + withoutAuxArray(arrO));
//         System.out.println("In place should be false " + withoutAuxArray(arrP));
    }

}
