package jdk.util.test.linkedhashmap;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Lizanle on 2017/10/30.
 */
public class TestLinedHashMap {

    /**
     * linked Hash能够按顺序复制原来的map
     */
    @Test
    public void test1(){
        Map<String,String> map = new HashMap();
        map.put("1","2");
        map.put("3","4");
        map.put("8","4");
        map.put("4","4");
        map.put("6","4");
        map.forEach((k,v)-> System.out.println(k+"---"+v));
        System.out.println("---------------------");
        Map<String, String> linkedHashMap = new LinkedHashMap<>(map);
        linkedHashMap.forEach((k,v)-> System.out.println(k+"---"+v));
    }
}
