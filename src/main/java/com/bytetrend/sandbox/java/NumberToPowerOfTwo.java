package com.bytetrend.sandbox.java;

public class NumberToPowerOfTwo {
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    private static String zeroPad = "00000000000000000000000000000000";

    static public String toBinary(int n){
        StringBuilder sb = new StringBuilder(""+(n & 1));
        while(( n >>= 1) != 0){
            sb.append(n&1);
        }
        return sb.reverse().toString();
    }

    /**
     * Returns a power of two table size for the given desired capacity.
     * See Hackers Delight, sec 3.2
     * http://ridiculousfish.com/blog/posts/labor-of-division-episode-i.html
     */
    static final int tableSizeFor(int c) {
        System.out.println("c= "+toBinary(c)+" "+c);
        int n = c - 1;
        System.out.println("n= "+toBinary(n)+" "+n);
        n |= n >>> 1;
        System.out.println(" 1) "+toBinary(n)+" "+n);
        n |= n >>> 2;
        System.out.println(" 2) "+toBinary(n)+" "+n);
        n |= n >>> 4;
        System.out.println(" 4) "+toBinary(n)+" "+n);
        n |= n >>> 8;
        System.out.println(" 8) "+toBinary(n)+" "+n);
        n |= n >>> 16;
        System.out.println("16) "+toBinary(n)+" "+n);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
    public static void main(String[] args){
        int c =  0x10001000;
        System.out.println(toBinary(MAXIMUM_CAPACITY));
        int i = tableSizeFor(c);
        String b = toBinary(i);
        System.out.println(zeroPad.substring(0,zeroPad.length() - b.length())+b);
        System.out.println(c +" -> "+i);
    }
}
