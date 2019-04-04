package com.yd.aidemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCB 2575490085@qq.com
 * Created on 2019/4/4.
 */
public class DataUtil {

    private static List<SubcontractingUnitsBean> subUnits;

    private static List<PersonBean> persons;

    public DataUtil(){
        subUnits = new ArrayList<>();
        persons = new ArrayList<>();
    }

    public  void setSubUnits(String data){
        String[] datas = data.split(",");
        int index = 1011;
        for (String temp : datas)
            subUnits.add(new SubcontractingUnitsBean(index++, temp));
    }

    public void setPersons(String data){
        String[] datas = data.split(",");
        int index = 10;
        for (String temp : datas)
            persons.add(new PersonBean(index++, temp));
    }

    public List<SubcontractingUnitsBean> getSubUnits() {
        return subUnits;
    }

    public List<PersonBean> getPersons() {
        return persons;
    }
}
