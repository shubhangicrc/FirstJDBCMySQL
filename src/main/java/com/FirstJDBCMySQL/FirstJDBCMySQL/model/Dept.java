package com.FirstJDBCMySQL.FirstJDBCMySQL.model;

/**
 * Created by Admin on 16/11/2017.
 */
public class Dept {
    Integer dept_id;
    String deptName;

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
