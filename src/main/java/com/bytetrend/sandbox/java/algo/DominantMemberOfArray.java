package com.bytetrend.sandbox.java.algo;


import java.util.Arrays;
import java.util.Scanner;
import static java.lang.System.out;
/**
 * The problem is as follows: A dominant member in the array is one that occupies
 * over half the positions in the array, for example:
 *
 * {3, 67, 23, 67, 67}
 *
 * 67 is a dominant member because it appears in the array in 3/5 (>50%) positions.
 *
 * Now, you are expected to provide a method that takes in an array and returns
 * an index of the dominant member if one exists and -1 if there is none.
 *
 * Easy, right? Well, I could have solved the problem handily if it were not
 * for the following constraints:
 *
 *     Expected time complexity is O(n)
 *     Expected space complexity is O(1)
 *
 * I can see how you could solve this for O(n) time with O(n) space complexities
 * as well as O(n^2) time with O(1) space complexities, but not one that meets
 * both O(n) time and O(1) space.
 *
 * his algorithm finds x. If there is a dominant element, x is guaranteed to be
 * set to it at the end of the loop. Since x occurs more than n/2 times,
 * you increment the count more than decrementing it, so it can't end up at 0
 * In other words if there is no dominant -1 will be returned because it checks
 * pairs.
 *{ 3, 67, 23, 67, 67, 1, 1, 1, 1, 2, 2, 2, 2, 67,67 }
 */
public class DominantMemberOfArray {
    public static int solution(int[] array) {

        int candidate=0;
        int counter = 0;

        // Find candidate for leader
        for(int i=0; i<array.length; i++){
            if(counter == 0) candidate = i;
            if(array[i] == array[candidate]){
                counter++;
            }else {
                counter--;
            }
        }
        // Count candidate occurrences in array
        counter = 0;
        for(int i=0; i<array.length; i++){
            if(array[i] == array[candidate]) counter++;
        }

        // Check that candidate occurs more than array.lenght/2
        return counter>array.length/2 ? candidate : -1;
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int a_i = 0; a_i < n; a_i++) {
            a[a_i] = in.nextInt();
        }
        out.println(Arrays.toString(a));
        out.println(solution(a));

    }

}
