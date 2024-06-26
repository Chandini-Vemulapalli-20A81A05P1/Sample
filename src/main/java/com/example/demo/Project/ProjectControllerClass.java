package com.example.demo.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/pro")
public class ProjectControllerClass {

    @Autowired
    private ProjectServiceClass projectService;

    @PostMapping("/addProject")
    public String addProject(@RequestBody Project request,
                             @RequestParam("adminWorkmail") String adminWorkmail,
                             @RequestParam("adminPassword") String adminPassword,
                             @RequestParam("hr") int hr,
                             @RequestParam("pm") int pm) {
        if (!projectService.isAdmin(adminWorkmail, adminPassword)) {
            return "Access denied: Only admins can add projects.";
        }

        if (!projectService.isHr(hr, pm)) {
            return "Access denied: Only HR and project managers can add projects.";
        }

        int rowsAffected = projectService.addProject(request);
        if (rowsAffected > 0) {
            return "Project added successfully!";
        } else {
            return "Failed to add project.";
        }
    }
}
