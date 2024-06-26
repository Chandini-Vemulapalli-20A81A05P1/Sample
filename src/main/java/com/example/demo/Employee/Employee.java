package com.example.demo.Employee;

public class Employee {
    private String fname;
    private String lname;
    private String dep;
    private int phone;
    private String email;
    private String workloc;
    private int assignedby;
    private int Eid;
    public int getEid() {
        return Eid;
    }
    public void setEid(int eid) {
        Eid = eid;
    }
    public String getWorkmail() {
        return workmail;
    }
    public void setWorkmail(String workmail) {
        this.workmail = workmail;
    }
    private String workmail;
    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    private int roleId;
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getDep() {
        return dep;
    }
    public void setDep(String dep) {
        this.dep = dep;
    }
    public int getPhone() {
        return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getWorkloc() {
        return workloc;
    }
    public void setWorkloc(String workloc) {
        this.workloc = workloc;
    }
    public int getPincode() {
        return pincode;
    }
    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    private int pincode;
    private int salary;

    public int getAssignedby() {
        return assignedby;
    }
    public void setAssignedby(int assignedby) {
        this.assignedby = assignedby;
    }
}
