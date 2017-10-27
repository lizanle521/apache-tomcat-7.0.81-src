package jdk.util.test.hashmap;

/**
 * Created by Lizanle on 2017/10/26.
 */
public class TableSizeDemo {
    static final int  tableSizeFor(int cap ){
        System.out.println(Integer.toBinaryString(cap));
        int n = cap -1;
        System.out.println(Integer.toBinaryString(n));
        System.out.println("----");
        n |= n >>> 1;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 2;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 4;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 8;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 16;
        System.out.println(Integer.toBinaryString(n));
        return n > 1<<30 - 1 ? 1<< 30: n + 1;
    }

    public static void main(String[] args) {
        System.out.println(tableSizeFor(67));
    }
}
