package com.example.demo.Project;

import java.time.LocalDate;

public class Project {
public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public String getPname() {
        return pname;
    }
    public void setPname(String pname) {
        this.pname = pname;
    }
    public String getClient_info() {
        return client_info;
    }
    public void setClient_info(String client_info) {
        this.client_info = client_info;
    }
    public int getAssigned_by() {
        return assigned_by;
    }
    public void setAssigned_by(int assigned_by) {
        this.assigned_by = assigned_by;
    }
    public LocalDate getStart_date() {
        return start_date;
    }
    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }
    public LocalDate getEnd_date() {
        return end_date;
    }
    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public int getHr() {
        return hr;
    }
    public void setHr(int hr) {
        this.hr = hr;
    }
    public int getPm() {
        return pm;
    }
    public void setPm(int pm) {
        this.pm = pm;
    }
private  int pid;
private String pname;
private String client_info;
private int assigned_by;
private LocalDate start_date;
private LocalDate end_date;
private int duration;
private int hr;
private int pm;
}
