package com.example.demo.TaskList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
@Service
public class TaskService {
@Autowired
private JdbcTemplate j;
private final int days=8;
public int addTask(Task t){
    String auser=t.getAuser();
    String puser=t.getPuser();
    System.out.println(auser);
    System.out.println(puser);
    String sql="select Eid,roleId from Employee where workmail=? and password=?";
    List<Map<String,Object>>a=j.queryForList(sql,auser,puser);
    if (a.isEmpty()) {
        System.out.println("No user found with the provided email and password.");
        return 500;
    }
    int roleId=(int)a.get(0).get("roleId");
    int id=(int)a.get(0).get("Eid");
    String sql2="select rolename from Roles where roleId=?";
    List<Map<String,Object>>b=j.queryForList(sql2,roleId);
    String rolename=(String)b.get(0).get("rolename");

    if(rolename.equals("teamlead")){
        String taskname=t.getTaskname();
        int taskassignedto=t.getTaskassignedto();
        int duration=t.getDuration();
        LocalDate taskassigneddate=LocalDate.now();
        LocalDate startDate=t.getStartdate();
        String taskdescription=t.getTaskdescription();
        int fullWorkingDays = duration /days;
        LocalDate endDate = startDate.plusDays(fullWorkingDays);
        String s2="select roleId from Employee where Eid=?";
        List<Map<String,Object>>k=j.queryForList(s2,taskassignedto);
        int checkteamlead=(int)k.get(0).get("roleId");
        System.out.println(checkteamlead);
        String s1="select pid from ProjectEmployeeDeta where Eid=?";
        List<Map<String,Object>>s=j.queryForList(s1,taskassignedto);
        
        if((checkteamlead!=4)){
        if(!s.isEmpty()){
        if(startDate.isAfter(taskassigneddate)){
            String sql8="insert into TaskList(taskname,taskassignedto,startDate,taskdescription,endDate,taskcreatedby,taskassigneddate,duration)"+"values(?,?,?,?,?,?,?,?)";
            int i=j.update(sql8,taskname,taskassignedto,startDate,taskdescription,endDate,id,taskassigneddate,duration);
            return i;
         }else{
            System.out.println("startdate is before the assigneddate");
            return 500;
        }
    }
        
    else{
        System.out.println("we cannot insertdata");
        return 500;
    }
}else{
    System.out.println("teamleadcannotassigndata to teamlead\"");
    return 500;
}}else{
    System.out.println("teamlead only can assign the task");
    return 500;
}
}

}
