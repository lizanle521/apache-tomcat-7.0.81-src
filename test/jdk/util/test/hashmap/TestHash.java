package jdk.util.test.hashmap;

import org.junit.Test;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lizanle on 2017/10/28.
 */
public class TestHash {
    @Test
    public void testHashMap() throws Exception {
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbageCollectorMXBean : garbageCollectorMXBeans) {
            System.out.println(garbageCollectorMXBean.getName());
        }
        HashMap<String, String> m = new HashMap<String, String>();
        for (int i = 0; i < 18; i++) {
            m.put((char) (i + 65) + (char) (i + 66) + (char) (i + 67) + "", i + ">>>");
        }
    }
}
