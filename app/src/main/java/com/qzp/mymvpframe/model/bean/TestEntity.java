package com.qzp.mymvpframe.model.bean;

/**
 * Created by qizepu on 2019/4/29.
 */

public class TestEntity {

    private String name;
    private int num;

    public TestEntity(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "[name=" + name  + " num=" + num + "]";
    }
}
