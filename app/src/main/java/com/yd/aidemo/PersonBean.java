package com.yd.aidemo;

import java.io.Serializable;

/**
 * @author ZCB 2575490085@qq.com
 * Created on 2019/4/4.
 */
public class PersonBean implements Serializable {

    private int id;

    private String name;

    public PersonBean(){}

    public PersonBean(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "{\nid: " + id + ",\n name: " + name + "\n}";
    }
}
