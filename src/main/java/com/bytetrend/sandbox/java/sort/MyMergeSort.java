package com.bytetrend.sandbox.java.sort;


import java.util.Arrays;

public class MyMergeSort {

    private int[] array;
    private int length;

    public static void main(String a[]){

        int[] inputArr = {45,23,11,89,77,98,4,28,65,43};
        MyMergeSort mms = new MyMergeSort();
        System.out.println(Arrays.toString(inputArr));
        mms.sort(inputArr);
        System.out.println(Arrays.toString(inputArr));
    }

    public void sort(int inputArr[]) {
        this.array = inputArr;
        this.length = inputArr.length;
        doMergeSort(0, length - 1,'S');
    }

    private void doMergeSort(int lowerIndex, int higherIndex, char c) {
        if (lowerIndex < higherIndex) {
            System.out.println(String.format("doMergeSort+ %s lower %d high %d %s",c, lowerIndex,higherIndex,Arrays.toString(array)));
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle,'L');
            // Below step sorts the right side of the array

            doMergeSort(middle + 1, higherIndex,'R');
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
            System.out.println(String.format("doMergeSort- %s lower %d high %d %s",c, lowerIndex,higherIndex,Arrays.toString(array)));
        }
    }

    private void mergeParts(int lowerIndex, int middle, int higherIndex) {
        System.out.println(String.format("merge+ lower %d mid %d high %d %s", lowerIndex,middle,higherIndex,Arrays.toString(array)));
        int[] tempMergArr = new int[length];
        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                System.out.print(String.format("1)[%d] [%d]=%d <= [%d]=%d ",k,i,tempMergArr[i],j,tempMergArr[j]));
                array[k] = tempMergArr[i];
                i++;
                System.out.println(Arrays.toString(array)+ " "+Arrays.toString(tempMergArr));
            } else {
                System.out.print(String.format("2)[%d] [%d]=%d > [%d]=%d ",k,i,tempMergArr[i],j,tempMergArr[j]));
                array[k] = tempMergArr[j];
                j++;
                System.out.println(Arrays.toString(array)+ " "+Arrays.toString(tempMergArr));
            }
            k++;
        }
        while (i <= middle) {
            System.out.print(String.format("3) [%d]=%d <= [%d]=%d ",k,array[k],i,tempMergArr[i]));
            array[k] = tempMergArr[i];
            System.out.println(Arrays.toString(array)+ " "+Arrays.toString(tempMergArr));
            k++;
            i++;
        }

    }
}
