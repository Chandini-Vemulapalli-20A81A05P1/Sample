package com.example.demo.Display;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/dm")

public class DisplayController {
    @Autowired
    DisplayService ds;
    @PostMapping("/dd")
    
    public Map<String,Object>DisplayDetails(@RequestBody Display D){
        return ds.DisplayDetails(D);
    }
}
