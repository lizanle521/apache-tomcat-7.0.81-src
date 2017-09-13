package org.apache.catalina.util;

import org.apache.tomcat.util.digester.Digester;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;
import java.util.StringJoiner;

/**
 * 学习xml解析
 * spring用的dom解析
 * tomcat用的sax解析
 * 参考 http://blog.csdn.net/qq_24451605/article/details/51289519
 * SAX（Simple API for XML）
 * 只能读，不能修改，只能顺序访问，适合解析大型XML，解析速度快
 * 常应用于处理大量数据的XML，实现异构系统的数据访问，实现跨平台
 * 从文档的开始通过每一节点移动，定位一个特定的节点
 *
 * DOM（Document Object Model）
 * 不仅能读，还能修改，而且能够实现随机访问，缺点是解析速度慢，适合解析小型文档
 * 一般应用与小型的配置XML，方便操作
 * 为载入到内存的文档节点建立类型描述，呈现可横向移动、潜在巨大的树型结构
 * 在内存中生成节点树操作代价昂贵
 * Created by Lizanle on 2017/9/13.
 */
public class TestDigester {

    @Test
    public void testParserXml() throws IOException, SAXException {
        InputStream inputStream = TestDigester.class.getResourceAsStream("Person.xml");
        Digester digester = new Digester();
        // 用Person类来解析Person节点，保存在栈中
        digester.addObjectCreate("Person","org.apache.catalina.util.Person");
        // 根据属性设置Person节点的对应的解析对象Person的属性
        digester.addSetProperties("Person");
        // 保存在栈中
        digester.addObjectCreate("Person/Child","org.apache.catalina.util.Child");
        digester.addSetProperties("Child");
        // 调用栈中第一个对象Person的add方法，将Person/Child节点对应的Child对象 add 到Person中
        digester.addSetNext("Person/Child","add");
        Person parse = (Person)digester.parse(inputStream);

        System.out.println(parse.toString());
    }
}
