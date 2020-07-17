package com.bytetrend.sandbox.java.challenge;

/**
 * https://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array/
 */
public class FindKElementInUnorderedArray {


    /*
        int parent(int i) { return (i-1)/2; }
    int left(int i) { return (2*i + 1); }
    int right(int i) { return (2*i + 2); }

     */
    public static int extractMax(int k, int[] arr) {

        int root = arr[0];
        if (k > 1) {
            arr[0] = arr[k - 1];
            maxHeapify(arr, 0, k);
        }
        k -= 1;
        return root;
    }

    public static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void maxHeapify(int[] arr, int i, int k) {
        int l = (2 * i + 1);
        int r = (2 * i + 2);
        int largest = i;
        if (l < k && arr[l] > arr[i])
            largest = l;
        if (r < k && arr[r] > arr[largest])
            largest = r;
        if (largest != i) {
            swap(arr, i, largest);
            maxHeapify(arr, largest, k);
        }
    }

    public static void replaceMac(int[] arr, int x, int k) {
        arr[0] = x;
        maxHeapify(arr, 0, k);
    }

    // Function to return k'th largest element in a given array
    public static int kthSmallest(int arr[], int n, int k) {
        // Build a heap of first k elements: O(k) time

        for (int i = k; i < arr.length; i++) {
            if (arr[i] < arr[0]) {
                replaceMac(arr, i, k);
            }
        }
        return arr[0];
    }

    // Driver program to test above methods
    static public void main(String[] args) {
        int arr[] = {12, 3, 5, 7, 19};
        int n = arr.length;
        int k = 4;
        int i = (k - 1) / 2;
        while (i >= 0) {
            maxHeapify(arr, i, k);
            i--;
        }
        System.out.println("K'th smallest element is " + kthSmallest(arr, n, k));
    }

}
