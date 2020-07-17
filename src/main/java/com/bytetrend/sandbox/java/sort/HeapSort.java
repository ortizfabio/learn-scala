package com.bytetrend.sandbox.java.sort;

import java.util.Arrays;

public class HeapSort {

    static private int parent(int i) {
        return (i - 1) / 2;
    }

    static private int left(int i) {
        return (2 * i + 1);
    }

    static private int right(int i) {
        return (2 * i + 2);
    }

    private static final void swap(int[] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
        System.out.println(x + " " + y + " " + Arrays.toString(a));
    }

    private static final void heapify(int[] a, int n) {
        int start = parent(n - 1);
        while (start >= 0) {
            siftdown(a, start, n - 1);
            start -= 1;
        }
    }

    private static final void siftdown(int[] a, int start, int end) {
        int root = start;
        while (left(root) <= end) {
            int child = left(root);
            int swap = root;
            if (a[swap] < a[child])
                swap = child;

            if (child + 1 <= end && a[swap] < a[child + 1])
                swap = child + 1;
            if (swap == root)
                return;
            else
                swap(a, root, swap);
            root = swap;
        }
    }

    public static final void heapsort(int[] a, int n) {
        heapify(a, n);
        int end = n - 1;

        while (end > 0) {
            swap(a, end, 0);
            end -= 1;
            siftdown(a, 0, end);
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 7, 12, 19};
        System.out.println(Arrays.toString(arr));
        heapsort(arr, arr.length);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{19, 12, 7, 5, 3};
        System.out.println(Arrays.toString(arr));
        heapsort(arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }

}
