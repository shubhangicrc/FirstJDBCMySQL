package com.FirstJDBCMySQL.FirstJDBCMySQL.impl;

import com.FirstJDBCMySQL.FirstJDBCMySQL.model.Dept;
import com.FirstJDBCMySQL.FirstJDBCMySQL.model.Student;
import com.FirstJDBCMySQL.FirstJDBCMySQL.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 15/11/2017.
 */
@Repository
public class StudentImpl implements StudentRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Student> getStudents() {
        String sql = "select * from student";
        List<Student> list = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Student.class));
        return list;
    }


    @Override
    public Student getData(Integer id) {
        String sql = "select * from student where id=?";
        Student st = jdbcTemplate.queryForObject(sql,
                new Object[]{id}, new BeanPropertyRowMapper<>(Student.class));
        return st;
    }

    @Override
    public void saveData(Student student) {
        String sql = "insert into student values(?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{student.getId(),
                student.getName(), student.getCity(), student.getDept_id()});
    }

    @Override
    public List<Map<String, Object>> getAllData() {
        String sql = "select s.id,s.name,s.city,d.deptName from" +
                " student s, dept d where s.dept_id=d.id";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }


    @Override
    public void deleteById(Integer id) {
        String sql = "delete from student where id=?";
        jdbcTemplate.update(sql,new Object[]{id},new int[]{Types.INTEGER});
    }

    @Override
    public List<Map<String, Object>> innerJoin() {
        String sql= "select Student.name,Student.city,Dept.deptName from Student inner join Dept where Student.dept_id=Dept.id";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @Override
    public String insertStud(Student student) {
        Dept dObj = student.getDept();
        Integer d_id = dObj.getDept_id();
        Dept dObj1 = getDept(d_id);
        String msg = "";
        if(dObj1!=null)
        {
            if(dObj.getDept_id().equals(dObj1.getDept_id())){
                msg = insertStud1(student);
            }
        }
        else {
            msg = insertDept(dObj);
            insertStud1(student);
        }
        return  msg;
    }

    @Override
    public Map<String, Object> getStudentsData() {
        return null;
    }

    public Dept getDept(Integer dept_id) {
        Dept dept1;
        try {
            String sql = "select * from dept where id=?";
            dept1 = jdbcTemplate.queryForObject(sql,
                    new Object[]{dept_id},
                    new int[]{Types.INTEGER},
                    new BeanPropertyRowMapper<>(Dept.class));
        }
        catch (EmptyResultDataAccessException e)
        { return  null; }
        return dept1;
    }

    public String insertStud1(Student student)
    {
        String sql = "insert into student values(?,?,?,?);";
        jdbcTemplate.update(sql,
                new Object[]{student.getId(), student.getName(), student.getCity(),student.getDept_id()},
                new int[]{Types.INTEGER, Types.VARCHAR, Types.VARCHAR,Types.INTEGER});
        return  "Student data inserted successfully";
    }

    public String insertDept(Dept dept)
    {
        String sql= "insert into dept values(?,?);";
        jdbcTemplate.update(sql,
                new Object[]{dept.getDept_id(),dept.getDeptName()},
                new int[]{Types.INTEGER, Types.VARCHAR});
        return "Student and dept data inserted successfully";
    }
}

