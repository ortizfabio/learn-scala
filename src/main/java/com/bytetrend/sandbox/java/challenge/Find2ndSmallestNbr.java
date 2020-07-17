package com.bytetrend.sandbox.java.challenge;

import java.util.Optional;

public class Find2ndSmallestNbr {
    public static Optional<Integer> find2ndSmallestNbr(int[] arr){
        if(arr == null || arr.length < 2)
            return Optional.empty();
        else{
            int frst = arr[0] < arr[1]?arr[0]:arr[1];
            int scnd = arr[0] > arr[1]?arr[0]:arr[1];
            for(int i = 2; i < arr.length; i++){
                if(arr[i] < scnd){
                    if(arr[i]<frst) {
                        scnd = frst;
                        frst = arr[i];
                    }else{
                        scnd = arr[i];
                    }
                }
            }
            return Optional.of(scnd);
        }
    }
    public static void result(Optional<Integer> scnd){
        if(scnd.isPresent())
            System.out.println("second smallest is: "+scnd.get());
        else
            System.out.println("Array did not contain 2 or more numbers");
    }
    public static void main(String[] args){
        int[] arr = new int[]{9, 3, 0, 4, 5, 8, 7, 6, 1, 2};
        result(find2ndSmallestNbr(arr));
        result(find2ndSmallestNbr(null));
    }
}
