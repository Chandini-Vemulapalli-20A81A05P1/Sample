package com.example.demo.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.Employee.Employee;

import java.time.LocalDate;
import java.util.*;
@Service
public class ProjectServiceClass {
@Autowired
 private JdbcTemplate jdbcTemplate;
    Project p;
    int Eid;
    public boolean isAdmin(String workmail, String password) {
        String sql = "select roleId from Employee where workmail=? and password=?";
        List<Map<String, Object>>a= jdbcTemplate.queryForList(sql, workmail, password);
        
        if (a.isEmpty()) {
            return false;
        }

      
    Map<String, Object> firstResult = a.get(0); // Get the first (and only) result
    int roleId = (int) firstResult.get("roleId");
    String sql1 = "select Eid from Employee where workmail=? and password=?";
        List<Map<String, Object>>b= jdbcTemplate.queryForList(sql1, workmail, password);
        
        if (b.isEmpty()) {
            return false;
        }

      
    Map<String, Object> fs = b.get(0); // Get the first (and only) result
    Eid= (int) fs.get("Eid");
        
        String roleNameSql = "select roleName from Roles where roleId=?";
        String roleName = jdbcTemplate.queryForObject(roleNameSql, String.class, roleId);
        
        return roleName != null && (roleName.equals("admin1") || roleName.equals("HR"));
    }
    
    int hrRoleId;
    int pmRoleId;
    int hr1;
    int pm1;
    public boolean isHr(int hr,int pm){
          try {
        // Query for HR roleId
        String sql1 = "select roleId from Employee where Eid=?";
        hrRoleId = jdbcTemplate.queryForObject(sql1, Integer.class, hr);
        hr1=hr;
        // Query for PM roleId
        String sql2 = "select roleId from Employee where Eid=?";
        pmRoleId = jdbcTemplate.queryForObject(sql2, Integer.class, pm);
        pm1=pm;
        // Query for roleName based on HR roleId
        String sql3 = "select roleName from Roles where roleId=?";
        String hrRoleName = jdbcTemplate.queryForObject(sql3, String.class, hrRoleId);
        
        // Query for roleName based on PM roleId
        String sql4 = "select roleName from Roles where roleId=?";
        String pmRoleName = jdbcTemplate.queryForObject(sql4, String.class, pmRoleId);
        System.out.println(hrRoleName);
        System.out.println(pmRoleName);
        // Check if HR role is "HR" and PM role is "projectmanager"
        if("HR".equals(hrRoleName) && "projectmanager".equals(pmRoleName))
        {
            return true;
        }
        else{
            return false;
        }
        
    } catch (EmptyResultDataAccessException e) {
        return false; // Handle case where no result found
    } catch (IncorrectResultSizeDataAccessException e) {
        // Log the exception or handle it appropriately
        // This occurs when queryForObject expects exactly one result but gets more than one
        return false;
    }
    
    }
    public int addProject(Project I){
        System.out.println(Eid);
                        String pname = I.getPname();
                String client_info = I.getClient_info();
                int duration = I.getDuration();
                LocalDate start_date = LocalDate.now();
                LocalDate end_date = start_date.plusMonths(duration);
                String finalsql = "insert into Project(pname, client_info ,assigned_by,start_date,end_date, duration, hr, pm) values(?,?,?,?,?,?,?,?)";
                int i = jdbcTemplate.update(finalsql,pname,client_info,Eid,start_date,end_date,duration,hr1,pm1);
                return i;
    }
}
