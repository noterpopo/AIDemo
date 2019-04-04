package com.yd.aidemo;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZCB 2575490085@qq.com
 * Created on 2019/4/4.
 */
public class ResultBean implements Serializable {

    private List<String> problemPosition;

    private List<ProblemBean> problem;

    private List<PersonBean> projectPersonnel;

    private List<SubcontractingUnitsBean> subcontractingUnit;

    public List<String> getProblemPosition() {
        return problemPosition;
    }

    public void setProblemPosition(List<String> problemPosition) {
        this.problemPosition = problemPosition;
    }

    public List<ProblemBean> getProblem() {
        return problem;
    }

    public void setProblem(List<ProblemBean> problem) {
        this.problem = problem;
    }

    public List<PersonBean> getProjectPersonnel() {
        return projectPersonnel;
    }

    public void setProjectPersonnel(List<PersonBean> projectPersonnel) {
        this.projectPersonnel = projectPersonnel;
    }

    public List<SubcontractingUnitsBean> getSubcontractingUnit() {
        return subcontractingUnit;
    }

    public void setSubcontractingUnit(List<SubcontractingUnitsBean> subcontractingUnit) {
        this.subcontractingUnit = subcontractingUnit;
    }

    @Override
    public String toString() {
        String result = "{\nproblemPosition: ";
        int size = problemPosition.size();
        for (int i = 0; i < size; ++i)
            result += problemPosition.get(i);
        result += ",\nproblem: [\n";
        for (int i = 0; i < problem.size(); ++i)
            result += problem.get(i) + ",\n";
        result += "],\n projectPersonnel: [\n";
        for (int i = 0; i < projectPersonnel.size(); ++i)
            result += projectPersonnel.get(i) + ",\n";
        result += "],\n subcontractingUnit: [\n";
        for (int i = 0; i < subcontractingUnit.size(); ++i)
            result += subcontractingUnit.get(i) + ",\n";
        result += "]\n}";
        return result;
    }
}
