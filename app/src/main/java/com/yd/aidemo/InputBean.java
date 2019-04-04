package com.yd.aidemo;

import java.util.List;

/**
 * @author ZCB 2575490085@qq.com
 * Created on 2019/4/4.
 */
public class InputBean {

    private String voiceText;

    private List<PersonBean> projectPersonnels;

    private List<SubcontractingUnitsBean> subcontractingUnits;

    public String getVoiceText() {
        return voiceText;
    }

    public void setVoiceText(String voiceText) {
        this.voiceText = voiceText;
    }

    public List<PersonBean> getProjectPersonnels() {
        return projectPersonnels;
    }

    public void setProjectPersonnels(List<PersonBean> projectPersonnels) {
        this.projectPersonnels = projectPersonnels;
    }

    public List<SubcontractingUnitsBean> getSubcontractingUnits() {
        return subcontractingUnits;
    }

    public void setSubcontractingUnits(List<SubcontractingUnitsBean> subcontractingUnits) {
        this.subcontractingUnits = subcontractingUnits;
    }
}
