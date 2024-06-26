package com.example.demo.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class Employeeservice {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    Employee e;

    public boolean isAdmin(String workmail, String password) {
        String sql = "SELECT 1 FROM Employee WHERE workmail=? AND password=? AND roleId=1";
        try {
            Integer count = jdbcTemplate.queryForObject(sql, new Object[]{workmail, password}, Integer.class);
            return count != null && count >= 1;
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
    }

    public int getAssignedBy(String workmail, String password) {
        String sql = "SELECT Eid FROM Employee WHERE workmail=? AND password=?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{workmail, password}, Integer.class);
        } catch (EmptyResultDataAccessException ex) {
            return -1; // Return a default value or handle appropriately
        }
    }

    public int getRoleIdByName(String roleName) {
        String sql = "SELECT roleId FROM Roles WHERE roleName=?";
        int id=jdbcTemplate.queryForObject(sql, new Object[]{roleName}, Integer.class);
        System.out.println(id);
        try {
            return id;
        } catch (EmptyResultDataAccessException ex) {
            return -1; // Return a default value or handle appropriately
        }
    }

    public int getEmpIdbyRoleId(int roleId) {
        String sql = "SELECT Eid FROM Employee WHERE roleId=?";
        List<Integer> empIds = jdbcTemplate.queryForList(sql, Integer.class, roleId);
        if (empIds.isEmpty()) {
            return -1; // Handle the case where no employee ID is found
        } else {
            return empIds.get(0); // Return the first employee ID found
        }
    }

    public void insertEmployee(String fname, String lname, String dep, int phone, String email, String workloc, int pincode, int salary, int roleId, int assignedby) {
        // Set the join date to current date and time
        LocalDate joinDate = LocalDate.now();
        // Generate work mail
        String workmail = fname.substring(0, 1).toLowerCase() + lname.toLowerCase() + "@ms.com";
        // Generate password
        String password = fname.substring(0, 2) + "@"
                + lname.substring(0, 2) + "@"
                + new SimpleDateFormat("HHmm").format(new Date());
        // SQL query to insert values into the Employee table
        String sql = "INSERT INTO Employee (fname, lname, dep, phone, joindate, email, workmail, password, workloc, pincode, salary, roleId, assignedby) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Execute the SQL query
        jdbcTemplate.update(sql, fname, lname, dep, phone, joinDate, email, workmail, password, workloc, pincode, salary, roleId, assignedby);
    }

    public List<Map<String, Object>> selAll(Employee e) {
        StringBuilder sql = new StringBuilder("SELECT * FROM Employee WHERE 1=1");
        if (e.getFname() != null) {
            sql.append(" AND fname='").append(e.getFname()).append("'");
        }
        if (e.getLname() != null) {
            sql.append(" AND lname='").append(e.getLname()).append("'");
        }
        if (e.getSalary() != 0) {
            sql.append(" AND salary=").append(e.getSalary());
        }
        if (e.getEmail() != null) {
            sql.append(" AND email='").append(e.getEmail()).append("'");
        }
        return jdbcTemplate.queryForList(sql.toString());
    }
}
