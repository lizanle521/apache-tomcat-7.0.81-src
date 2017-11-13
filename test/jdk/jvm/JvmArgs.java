package jdk.jvm;

import java.io.*;

import static java.lang.Runtime.*;

/**
 * Created by Administrator on 2017/11/13.
 */
public class JvmArgs {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < args.length; i++) {
            System.out.println("参数"+i+"的值是"+args[i]);
        }
        System.out.println(getRuntime().maxMemory()/1000/1000+"M");
        System.out.println("可用核心数"+ getRuntime().availableProcessors());

        Process jps = getRuntime().exec("jps");
        InputStream inputStream = jps.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }
        System.out.println("freememory"+ getRuntime().freeMemory()/1000/1000+"M");

        System.out.println("totalMemory"+ getRuntime().totalMemory()/1000/1000+"M");

        getRuntime().traceMethodCalls(true);
        testMethodCall(1);

        FinalizeObject finalizeObject = new FinalizeObject();
        for (int i = 0; i < 1000000000; i++) {
            getRuntime().runFinalization();
            Object object = new Object();
        }

        getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("i'm done");
            }
        });
        // halt 会让jvm不会调用shutDownHook
        getRuntime().halt(0);
    }

    public static void testMethodCall(int a){

    }
}
