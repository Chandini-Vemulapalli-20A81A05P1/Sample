package com.example.demo.ProjectEmployeeDetails;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;
    @RequestMapping("/ps")
    @RestController
    public class DetailsController {
    
        @Autowired
        private DetailsService detailsService;
    
        @PostMapping("/addProjectEmployeeDetails")
        public int addProjectEmployeeDetails(
                @RequestParam String workmail,
                @RequestParam String password,
                @RequestParam int pid,
                @RequestBody Details details) {
    
            boolean isHR = detailsService.isHR(workmail, password, pid);
            if (isHR) {
                System.out.println("HRTRUE");
                return detailsService.addDetails(details);
            } else {
                return 0; // Unauthorized or other appropriate error code
            }
        }
    }
