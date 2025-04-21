package com.JobAppCom.JobAppCom.Controler;

import com.JobAppCom.JobAppCom.Config.SecurityConfig;
import com.JobAppCom.JobAppCom.Model.JobDetailsModel;
import com.JobAppCom.JobAppCom.Model.UserDetailsModel;
import com.JobAppCom.JobAppCom.Service.JobDetails;
import com.JobAppCom.JobAppCom.Service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobDetailsController {

    @Autowired
    private JobDetails service;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/posts")
    public List<JobDetailsModel> getAllJobDetails(){
        return service.getAllJobDetails();
    }

    @DeleteMapping("/posts/{id}")
    public void deletePostById(@PathVariable("id")int postId){
        service.deletePostById(postId);
        System.out.println(postId +"has been deleted!");
    }

    @PostMapping(path={"/jobPost"})
    public void saveJobDetails(@RequestBody JobDetailsModel jobDetails){
        service.saveJobDetails(jobDetails);
    }

    @GetMapping("/jobPost/{id}")
    public JobDetailsModel getJobDetailsById(@PathVariable("id") int postId) {
        return service.getJobDetailsById(postId);
    }

    @GetMapping("csrf-token")
    public CsrfToken getcsrftoken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/users")
    public void users(@RequestBody UserDetailsModel user){
        service.saveUserDetails(user);
    }


    @PostMapping("/login")
    public String login(@RequestBody UserDetailsModel user){
        Authentication authentication= authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));

        if(authentication!=null){
            return jwtService.generateToken(user.getUserName());
        }else{
            return "failed";
        }
    }


}
