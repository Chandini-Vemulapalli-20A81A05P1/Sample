package com.example.demo.ProjectEmployeeDetails;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private int pid;
    private int validProj;
    private int prolead;

    public boolean isHR(String workmail, String password, int pid) {
        String sql = "select Eid, roleId from Employee where workmail = ? and password = ?";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, workmail, password);

        if (result.isEmpty()) {
            return false;
        }

        this.pid = pid;
        Map<String, Object> firstResult = result.get(0);
        int roleId = (int) firstResult.get("roleId");
        int Eid = (int) firstResult.get("Eid");
        System.out.println("Eid: " + Eid);

        String roleNameSql = "select roleName from Roles where roleId = ?";
        String roleName = jdbcTemplate.queryForObject(roleNameSql, String.class, roleId); // Corrected parameter usage
        System.out.println("Role Name: " + roleName);

        String projectSql = "select pm, hr from Project where pid = ?";
        List<Map<String, Object>> projectResult = jdbcTemplate.queryForList(projectSql, pid);

        if (!projectResult.isEmpty()) {
            int pmcheck = (int) projectResult.get(0).get("pm");
            System.out.println("PM Check: " + pmcheck);
            int hrcheck = (int) projectResult.get(0).get("hr");
            try {
                if ((roleName.equals("HR") && hrcheck == Eid) || (roleName.equals("projectmanager") && pmcheck == Eid)) {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Not HR or Project Manager");
                return false;
            }
        }

        return false;
    }

    private boolean isDuplicateProjectDetails(Details details) {
        if (details == null) {
            System.out.println("Details object is null");
            return false;
        }
        
        int eid = details.getEid();
        int pid = details.getPid();
        System.out.println("Eid: " + eid + ", Pid: " + pid);
        
        try {
            String dup = "SELECT pid FROM ProjectEmployeeDeta WHERE eid = ? AND pid = ?";
            List<Map<String, Object>> results = jdbcTemplate.queryForList(dup, eid, pid);
            return !results.isEmpty();
        } catch (Exception e) {
            System.out.println("Error checking duplicate employee details: " + e.getMessage());
            return false;
        }
    }

    private boolean isValidProject(int pid) {
        String validPro = "select pm from Project where pid = ?";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(validPro, pid);
        
        if (result.isEmpty()) {
            return false;
        }
        
        Map<String, Object> firstResult = result.get(0); // Get the first (and only) result
        validProj = (int) firstResult.get("pm");
        System.out.println("Valid Project Manager: " + validProj);
        
        return true;
    }

    private boolean isLead(Details details) {
        String lead = "select roleId from Employee where Eid = " + details.getEid();
        prolead = jdbcTemplate.queryForObject(lead, Integer.class);
        
        String roleLead = "select rolename from Roles where roleId=" + prolead;
        String roleName = jdbcTemplate.queryForObject(roleLead, String.class);
        
        return roleName.equals("teamlead");
    }

    public int addDetails(Details details) {
        int id = details.getPid();
        System.out.println("id: " + id);
        System.out.println("pid: " + pid);
        
        String as = "select assigned_by from Project where pid=" + pid;
        List<Map<String, Object>> a = jdbcTemplate.queryForList(as);
        Map<String, Object> fs = a.get(0);
        int k = (int) fs.get("assigned_by");
        
        int kid = details.getEid();
        System.out.println("Eid: " + kid);
        
        if (!isDuplicateProjectDetails(details)) {
            System.out.println("No duplicate project details found");
            if (isValidProject(pid)) {
                if (isLead(details)) {
                    String insertQuery = "insert into ProjectEmployeeDeta (pid, eid, assigned_date, teamlead, assigned_by) values (?, ?, ?, ?, ?)";
                    return jdbcTemplate.update(insertQuery, id, kid, LocalDate.now(), validProj, k);
                } else {
                    String insertQuery = "insert into ProjectEmployeeDeta (pid, eid, assigned_date, teamlead, assigned_by) values (?, ?, ?, ?, ?)";
                    return jdbcTemplate.update(insertQuery, id, kid, LocalDate.now(), prolead, k);
                }
            } else {
                System.out.println("Invalid project.");
            }
        } else {
            System.out.println("Duplicate project details found.");
        }

        return 0; // Default return if conditions fail
    }
}
