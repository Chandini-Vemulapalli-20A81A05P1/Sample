package com.example.demo.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
@org.springframework.stereotype.Service
public class Service {
private JdbcTemplate j;

    @Autowired
    public Service(JdbcTemplate jdbcTemplate) {
        this.j= jdbcTemplate;
    }
public List<Map<String,Object>>SelAll(){
String sql="select * from Student";
return j.queryForList(sql);
}
public boolean insertStudent(Student student) {
    String query = "INSERT INTO Student (id, name, dep) VALUES (?, ?, ?)";
    int rowsAffected = j.update(query, student.getId(), student.getName(), student.getDep());
    return rowsAffected == 1;
}
public boolean updateStudent(Student student) {
    String query = "UPDATE Student SET name = ?, dep = ? WHERE id = ?";
    int rowsAffected = j.update(query, student.getName(), student.getDep(), student.getId());
    return rowsAffected == 1;
}
public boolean deleteStudent(int id) {
    String query = "DELETE FROM Student WHERE id = ?";
    int rowsAffected = j.update(query, id);
    return rowsAffected == 1;
}
}
