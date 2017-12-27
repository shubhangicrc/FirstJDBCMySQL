package com.FirstJDBCMySQL.FirstJDBCMySQL.controller;

import com.FirstJDBCMySQL.FirstJDBCMySQL.impl.StudentImpl;
import com.FirstJDBCMySQL.FirstJDBCMySQL.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

        @Autowired
        StudentImpl studImpl;

        @GetMapping(value = "/getStudentData")
        public List<Student> getStudentData()
        {
            return studImpl.getStudents();
        }

        @GetMapping(value = "/getStudentsData")
        public Map<String,Object> getStudentsData()
        {
            Map<String,Object> data = new HashMap<>();
            List<Student> list = studImpl.getStudents();

            data.put("status","success");
            data.put("result",list);
            return data;
        }


        @GetMapping(value = "/getData/{id}")
        public Student getData(@PathVariable Integer id)
        {
            return studImpl.getData(id);
        }

        @PostMapping(value = "/saveStudentData")
        public String saveStudentData(@RequestBody Student student)
        {
            studImpl.saveData(student);
            return "Inserted Successfully";
        }

        @GetMapping(value = "/returnAllData")
        public List<Map<String,Object>> returnAllData(){
            List<Map<String,Object>> list = studImpl.getAllData();
            return list;
        }

        @GetMapping(value =  "/deleteId/{id}")
        public String deleteId(@PathVariable Integer id)
        {
            studImpl.deleteById(id);
            return "Deleted Successfully";
        }
        @GetMapping(value = "/innerJoinRes")
        public List<Map<String,Object>> innerJoinRes(){
            List<Map<String,Object>> list = studImpl.innerJoin();
            return list;
        }
        @PostMapping(value = "/insertStud")
        public String insertStud(@RequestBody Student student)
        {
            return studImpl.insertStud(student);
        }

    }
