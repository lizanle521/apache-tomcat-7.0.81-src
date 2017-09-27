package jdk.io.test;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Lizanle on 2017/9/27.
 */
public class SerializableTest implements Serializable {
    /**
     * 一个简单的类用于测试序列化
     */
    public class Data implements Serializable{

        private static final long serialVersionUID = -9124666011507804566L;
        public int n;

        public Data(int n) {
            this.n = n;
        }

        @Override
        public String toString() {
            return Integer.toString(n);
        }
    }

    /**
     * 用于测试序列化，每个对象的worm都worm中的下一段连接，同时又属于不同类的对象引用数组连接
     */
    public class Worm implements Serializable{

        private static final long serialVersionUID = 8975583853795120560L;

        private Random random = new Random(47);
        private Data[] d = {
                new Data(random.nextInt(10)),
                new Data(random.nextInt(10)),
                new Data(random.nextInt(10))
        };
        private Worm next;
        private char c;

        public Worm(int i, char c) {
            System.out.println("worm constructor :" + i);
            this.c = c;
            if(--i > 0 ){
                next = new Worm(i,(char)(c+1));
            }
        }

        public Worm(){
            System.out.println("default constructor");
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(":");
            sb.append(c);
            sb.append("(");
            for(Data data : d) {
                sb.append(data);
            }
            sb.append(")");
            if(next!=null) {
                sb.append(next);
            }
            return sb.toString();
        }
    }

    @Test
    public void serializableTest() throws Exception {
        Worm worm = new Worm(6,'c');
        System.out.println("before serializable");
        System.out.println("worm="+worm);

        // 序列化操作1
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("worm.out"));
        outputStream.writeObject(worm); // java.io.NotSerializableException: jdk.io.test.SerializableTest
        outputStream.close();

        // 反序列化操作
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("worm.out"));
        Object o = inputStream.readObject();
        System.out.println("after deserializable--"+o);
        inputStream.close();

        // 序列化操作二
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream1 = new ObjectOutputStream(stream);
        outputStream1.writeObject(worm);
        outputStream1.flush();
        // 反序列化操作二
        ByteArrayInputStream inputStream1 = new ByteArrayInputStream(stream.toByteArray());
        ObjectInputStream inputStream2 = new ObjectInputStream(inputStream1);
        //String readObject = (String)inputStream2.readObject();
        Worm worm1 = (Worm)inputStream2.readObject();
        //System.out.println("string:"+readObject);
        System.out.println("worm:"+worm1);

        inputStream2.close();
    }
}
