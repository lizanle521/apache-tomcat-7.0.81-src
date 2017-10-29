package jdk.util.test.collections;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * java 集合框架学习
 * Created by Administrator on 2017/10/29.
 */
public class CollectionsDemo {
    @Test
    public void test1(){
        List emptyList = Collections.EMPTY_LIST;
        // emptyList.add(3); UnsupportedOperationException
        emptyList.iterator();
        ArrayList arrayList = new ArrayList();
        System.out.println(arrayList.equals(emptyList));
        //Collections.addAll();

        // 创建一个长度为4的整形数组
        int[] x = {4};
        int[] o = (int[])Array.newInstance(int.class, x);
        System.out.println(o.length);

        int[] o2 = (int[])Array.newInstance(int.class, 4);
        System.out.println(o2.length);

    }

    @Test
    public void test2(){
        // vm options -ea -verbose:gc -XX:+PrintGCDetails -Xms10m -Xmx10m -Xmn4m -XX:+PrintGCTimeStamps
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
        long l = System.currentTimeMillis();
        System.out.println(l);
        Collections.binarySearch(list,499);
        System.out.println(System.currentTimeMillis() - l);
    }
}
