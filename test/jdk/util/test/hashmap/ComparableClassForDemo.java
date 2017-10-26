package jdk.util.test.hashmap;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Lizanle on 2017/10/26.
 */
public class ComparableClassForDemo {
    /**
     * 模仿hashmap里边的ComparableClassFor方法实现，
     * 判断一个类是不是 满足 class C implements Comparable<C>
     *    如果是，返回C,否则返回null
     * @param x
     * @return
     */
    static Class<?> comparableClassFor(Object x){
        if(x instanceof Comparable){
            Class<?> c;
            Type[] ts,as;
            Type t;
            ParameterizedType p;
            if((c = x.getClass()) == String.class){ // 如果是x是String类型的话，就直接通过了
                return c;
            }
            if((ts = c.getGenericInterfaces()) != null){
                for (int i = 0; i < ts.length; i++) {
                    if((t = ts[0]) instanceof ParameterizedType && ( p = ((ParameterizedType)t)).getRawType() == Comparable.class && (as = p.getActualTypeArguments()) != null
                            && as.length == 1 && as[0] == C.class ){
                        return c;
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(comparableClassFor(new C()));
    }

    static class C implements Comparable<C>{
        @Override
        public int compareTo(C o) {
            return 0;
        }
    }
}
