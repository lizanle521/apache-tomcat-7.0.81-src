package org.apache.catalina.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lizanle on 2017/9/13.
 */
public class Person {
    private String name;
    private Integer age;
    private Integer height;

    private List<Child> childList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

    public void add(Child child){
        this.childList.add(child);
    }

    @Override
    public String toString() {
        return "Person{" +
                "childList=" + childList.size() +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
