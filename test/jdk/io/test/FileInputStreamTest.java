package jdk.io.test;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Lizanle on 2017/9/27.
 */
public class FileInputStreamTest {

    /**
     * 从控制台读取字符打印
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int data = 0;
        while ((data = System.in.read()) != -1){
            System.out.println(data);
        }
    }

    @Test
    public void fileInputStreamTest() throws Exception{
        // 当前类所在的路径下找，如果以/开头，就会在target/classes下边找
        InputStream stream = FileInputStreamTest.class.getResourceAsStream("a.txt");
        // classLoader会去target/classes下边去找
        InputStream resourceAsStream = FileInputStreamTest.class.getClassLoader().getResourceAsStream("a.txt");
        byte[] buffer = new byte[1024];
        int c = 0;
        StringBuffer sb = new StringBuffer();
        while ((c = resourceAsStream.read(buffer)) != -1){
            sb.append(new String(buffer));
        }
        System.out.println(sb.toString());
    }



}