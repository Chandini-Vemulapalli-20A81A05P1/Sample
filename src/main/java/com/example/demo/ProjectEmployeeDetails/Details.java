package com.example.demo.ProjectEmployeeDetails;

import java.time.LocalDate;

public class Details {
    private int pid;
    private int eid;
    private LocalDate assigned_date;
    private int assigned_by;
    private String teamlead;
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public int getEid() {
        return eid;
    }
    public void setEid(int eid) {
        this.eid = eid;
    }
    public LocalDate getAssigned_date() {
        return assigned_date;
    }
    public void setAssigned_date(LocalDate assigned_date) {
        this.assigned_date = assigned_date;
    }
    public int getAssigned_by() {
        return assigned_by;
    }
    public void setAssigned_by(int assigned_by) {
        this.assigned_by = assigned_by;
    }
    public String getTeamlead() {
        return teamlead;
    }
    public void setTeamlead(String teamlead) {
        this.teamlead = teamlead;
    }

}
