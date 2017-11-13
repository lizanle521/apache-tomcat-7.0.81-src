package jdk.jvm;

/**
 * 堆栈溢出
 * Created by Administrator on 2017/11/13.
 */
public class TestStackDemo {
    private static int count = 0;
    public static void recursion(){
        long a = 1,b=2,c=3,d=4;
        Runtime.getRuntime().gc();
        count++;
        recursion();
    }
    public static void main(String[] args) {
        try{
            recursion();
        }catch (Throwable e){
            System.out.println(count);
        }
    }
}
