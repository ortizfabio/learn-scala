package com.bytetrend.sandbox.java;

/**
 * The result is that finally wins by returning 0.
 */
public class FinallyException {

    public static int test(){
        try{
            throw new RuntimeException("First");
        }catch(Exception e){
            throw new RuntimeException("two");
        }finally {
            return 0;
        }
    }

    public static void main(String[] args){
        try {
            int i = test();
            System.out.println(i);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
