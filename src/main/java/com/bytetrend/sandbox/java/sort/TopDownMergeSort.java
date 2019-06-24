package com.bytetrend.sandbox.java.sort;

import java.util.Arrays;
import static java.lang.String.format;

/**
 * https://en.wikipedia.org/wiki/Merge_sort#Bottom-up_implementation
 * https://stackoverflow.com/questions/7577825/understanding-merge-sort-optimization-avoiding-copies
 *
 */
public class TopDownMergeSort {

    public static void mergesort(int[]in, int[]tmp, int iBegin, int iEnd,char c){
        System.out.println(String.format("%c iBegin=%d iEnd=%d in=%s tmp=%s",
                c,iBegin,iEnd,Arrays.toString(in),Arrays.toString(tmp)));
        if( iEnd - iBegin < 2)
            return;
        int iMiddle = (iBegin + iEnd) /2;

        mergesort(tmp,in,iBegin,iMiddle,'L');
        mergesort(tmp,in,iMiddle,iEnd,'R');
        sort(in,tmp,iBegin,iMiddle,iEnd);
    }

    public static void sort(int[]dest,int[]source,int iBegin, int iMiddle,int iEnd){
        System.out.println(String.format("in=%s tmp=%s begin=%d mid=%d end=%d",Arrays.toString(dest),Arrays.toString(source),iBegin,iMiddle,iEnd));
        int i = iBegin;
        int j = iMiddle;
        for(int k = iBegin; k < iEnd; k++){
            if(i < iMiddle && (j >= iEnd || source[i] <= source[j])){
                dest[k] = source[i++];
            }else {
                dest[k] = source[j++];
            }
        }
        System.out.println(String.format("in=%s tmp=%s",
                Arrays.toString(dest),Arrays.toString(source)));
    }


    public static void main(String[] args) {
        int[] inputArr = {45, 23, 11, 89, 4, 28, 65,00};
        int[] outputArr =  new int[inputArr.length];
        System.arraycopy(inputArr,0,outputArr,0,inputArr.length);
        outputArr[outputArr.length-1] = 99;
        System.out.println(Arrays.toString(inputArr));
        mergesort(inputArr,outputArr, 0,inputArr.length-1,' ');
        System.out.println(Arrays.toString(inputArr));
        System.out.println("#########################################");
        inputArr =new int[] {9,3,6,1,00};
        outputArr =  new int[inputArr.length];
        System.arraycopy(inputArr,0,outputArr,0,inputArr.length);
        outputArr[outputArr.length-1] = 99;
        System.out.println(Arrays.toString(inputArr));
        mergesort(inputArr,outputArr, 0,inputArr.length-1,' ');
        System.out.println(Arrays.toString(inputArr));

    }
}
