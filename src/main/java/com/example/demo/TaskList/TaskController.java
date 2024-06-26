package com.example.demo.TaskList;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/ta")
public class TaskController {
@Autowired
private TaskService ts;
@PostMapping("/task")

public int addTask(@RequestBody Task t){
    return ts.addTask(t);
}
}
