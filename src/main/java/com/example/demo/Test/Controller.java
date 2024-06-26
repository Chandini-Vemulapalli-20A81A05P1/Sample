package com.example.demo.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RequestMapping("/path")
@RestController
public class Controller {
    @Autowired
    Service s;
    @GetMapping("/s")
    public List<Map<String,Object>>SelAll(){
        return s.SelAll();
    }
    @PostMapping("/i")
    
    public boolean insertStudent(@RequestBody Student t){
        return s.insertStudent(t);
    }
    @PutMapping("/P")
    public boolean updateStudent(@RequestBody Student t){
        return s.updateStudent(t);
    }
    @DeleteMapping("/d")
    public boolean deleteStudent(@RequestBody Student t){
        return s.deleteStudent(t.getId());
    }
}
