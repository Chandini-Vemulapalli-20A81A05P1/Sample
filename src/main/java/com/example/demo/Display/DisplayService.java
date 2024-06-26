package com.example.demo.Display;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DisplayService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> DisplayDetails(Display display) {
        int pid = display.getPid();
        HashMap<String, Object> hm = new HashMap<>();        
        
        String sql = "select pname, client_info, duration, hr, pm from Project where pid = ?";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, pid);
    
        if (resultList.isEmpty()) {
            hm.put("error", "No project found with the given pid");
            return hm;
        }
        //Map<String, Object> result = resultList.get(0);
        String pname="";
        String client_info="";
        int duration=0,hr=0,pm=0;
        for(Map<String,Object>result:resultList){
        pname = (String) result.get("pname");
        client_info = (String) result.get("client_info");
        duration = (int) result.get("duration");
        hr = (int) result.get("hr");
        pm = (int) result.get("pm");
        }
        String sql2="select * from Employee where Eid in (select eid from ProjectEmployeeDeta where pid=?)";
        List<Map<String, Object>> rs = jdbcTemplate.queryForList(sql2, pid);
        if(rs.isEmpty()){
            System.out.println("No employee found with given project");
        }else{
            hm.put("Employees",rs);
        }
        hm.put("Projectname", pname);
        hm.put("client_info", client_info);
        hm.put("Duration", duration);
        hm.put("HR Manager", hr);
        hm.put("Project Manager", pm);

        return hm;
    }
}
