package jdk.jvm;

/**
 * Created by Administrator on 2017/11/13.
 */
public class FinalizeObject {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("i'm running finalize method");
        super.finalize();
    }
}
