package com.yd.aidemo;

import java.io.Serializable;

/**
 * @author ZCB 2575490085@qq.com
 * Created on 2019/4/4.
 */
public class ProblemBean implements Serializable {

    private int id;

    private String name;

    private double match;

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

    public double getMatch() {
        return match;
    }

    public void setMatch(double match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return "{\nid: " + id + ",\n name: " + name + ",\n match: "+ match + "\n}";
    }
}
