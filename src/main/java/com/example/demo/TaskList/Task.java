package com.example.demo.TaskList;

import java.time.LocalDate;

public class Task {
    private String auser ;
    private String puser ;
    private String taskname;
    private int taskcreatedby;
    private int taskassignedto;
    public String getAuser() {
        return auser;
    }
    public void setAuser(String auser) {
        this.auser = auser;
    }
    public String getPuser() {
        return puser;
    }
    public void setPuser(String puser) {
        this.puser = puser;
    }
    public String getTaskname() {
        return taskname;
    }
    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }
    public int getTaskcreatedby() {
        return taskcreatedby;
    }
    public void setTaskcreatedby(int taskcreatedby) {
        this.taskcreatedby = taskcreatedby;
    }
    public int getTaskassignedto() {
        return taskassignedto;
    }
    public void setTaskassignedto(int taskassignedto) {
        this.taskassignedto = taskassignedto;
    }
    public LocalDate getStartdate() {
        return startdate;
    }
    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getTaskdescription() {
        return taskdescription;
    }
    public void setTaskdescription(String taskdescription) {
        this.taskdescription = taskdescription;
    }
    private LocalDate startdate;
    private int duration;
    private String taskdescription;
}
