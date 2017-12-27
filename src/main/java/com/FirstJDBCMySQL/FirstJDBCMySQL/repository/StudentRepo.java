package com.FirstJDBCMySQL.FirstJDBCMySQL.repository;

import com.FirstJDBCMySQL.FirstJDBCMySQL.model.Dept;
import com.FirstJDBCMySQL.FirstJDBCMySQL.model.Student;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 15/11/2017.
 */
public interface StudentRepo {

    List<Student> getStudents();
    Student getData(Integer id);
    void saveData(Student student);
    List<Map<String,Object>> getAllData();
    //void saveAllData(Student student, Dept dept);
    void deleteById(Integer id);
    List<Map<String,Object>> innerJoin();
    String insertStud(Student student);

    Map<String,Object> getStudentsData();
}
