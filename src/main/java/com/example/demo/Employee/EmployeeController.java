package com.example.demo.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Employee.Employeeservice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;
@RestController
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    private Employeeservice custom;
    private Employee e;
    @PostMapping("/addEmployee")
    public String addEmployee(@RequestBody Employee request,
    @RequestParam("adminWorkmail") String adminWorkmail,
    @RequestParam("adminPassword") String adminPassword,
    @RequestParam("roleName") String roleName,
    @RequestParam("roleid") int roleid) {
if (custom.isAdmin(adminWorkmail, adminPassword)) {
int roleId = custom.getRoleIdByName(roleName);
request.setRoleId(roleId);
int assignedby=custom.getAssignedBy(adminWorkmail, adminPassword);
request.setAssignedby(assignedby);
int Empid=custom.getEmpIdbyRoleId(roleid);
request.setEid(Empid);
custom.insertEmployee(request.getFname(), request.getLname(), request.getDep(),
request.getPhone(), request.getEmail(), request.getWorkloc(),
request.getPincode(), request.getSalary(), request.getRoleId(),request.getAssignedby());
return "Employee added successfully!";
} else {
return "Access denied: Only admins can add employees.";
}
}
    @PostMapping("/sel")
    public List<Map<String, Object>> SelectAll(@RequestBody Employee s) 
    {
        return custom.selAll(s);
    }


}
