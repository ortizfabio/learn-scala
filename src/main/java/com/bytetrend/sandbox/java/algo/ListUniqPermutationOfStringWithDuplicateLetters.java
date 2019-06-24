package com.bytetrend.sandbox.java.algo;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * https://stackoverflow.com/questions/40752319/algorithm-to-list-unique-permutations-of-string-with-duplicate-letters
 */
public class ListUniqPermutationOfStringWithDuplicateLetters {

    /**
     * The method goes back to Narayana Pandita in 14th century India, and has been frequently rediscovered ever since.[50]
     * <p>
     * The following algorithm generates the next permutation lexicographically after a given permutation. It changes the given permutation in-place.
     * <p>
     * Find the largest index k such that a[k] < a[k + 1]. If no such index exists, the permutation is the last permutation.
     * Find the largest index l greater than k such that a[k] < a[l].
     * Swap the value of a[k] with that of a[l].
     * Reverse the sequence from a[k + 1] up to and including the final element a[n].
     * For example, given the sequence [1, 2, 3, 4] (which is in increasing order), and given that the index is zero-based, the steps are as follows:
     * <p>
     * Index k = 2, because 3 is placed at an index that satisfies condition of being the largest index that is still less than a[k + 1] which is 4.
     * Index l = 3, because 4 is the only value in the sequence that is greater than 3 in order to satisfy the condition a[k] < a[l].
     * The values of a[2] and a[3] are swapped to form the new sequence [1,2,4,3].
     * The sequence after k-index a[2] to the final element is reversed. Because only one value lies after this index (the 3), the sequence remains unchanged in this instance. Thus the lexicographic successor of the initial state is permuted: [1,2,4,3].
     * Following this algorithm, the next lexicographic permutation will be [1,3,2,4], and the 24th permutation will be [4,3,2,1] at which point a[k] < a[k + 1] does not exist, indicating that this is the last permutation.
     * <p>
     * This method uses about 3 comparisons and 1.5 swaps per permutation, amortized over the whole sequence, not counting the initial mergesort.[51]
     *
     * @param s
     * @param consumer
     */
    static void generateUniquePermutations(String s, Consumer<String> consumer) {
        char[] array = s.toCharArray();
        Arrays.sort(array);
        for (; ; ) {
            consumer.accept(String.valueOf(array));
            //1) Find the largest index k such that a[k] < a[k + 1]. If no such index exists,
            // the permutation is the last permutation.
            int changePos = array.length - 2;
            while (changePos >= 0 && array[changePos] >= array[changePos + 1])
                --changePos;

            if (changePos < 0)
                break; //all done
            //2) Find the largest index l greater than k such that a[k] < a[l].
            int swapPos = changePos + 1;
            while (swapPos + 1 < array.length && array[swapPos + 1] > array[changePos])
                ++swapPos;
            //3) Swap the value of a[k] with that of a[l].
            char t = array[changePos];
            array[changePos] = array[swapPos];
            array[swapPos] = t;
            //4) Reverse the sequence from a[k + 1] up to and including the final element a[n].
            for (int i = changePos + 1, j = array.length - 1; i < j; ++i, --j) {
                t = array[i];
                array[i] = array[j];
                array[j] = t;
            }
        }
    }

    public static void main(String[] args) throws java.lang.Exception {
        StringBuilder line = new StringBuilder();
        generateUniquePermutations("1234", s -> {
            if (line.length() > 0) {
                if (line.length() + s.length() >= 75) {
                    System.out.println(line.toString());
                    line.setLength(0);
                } else
                    line.append(" ");
            }
            line.append(s);
        });
        System.out.println(line);
    }
}