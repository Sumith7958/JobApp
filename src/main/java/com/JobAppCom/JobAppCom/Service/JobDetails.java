package com.JobAppCom.JobAppCom.Service;

import com.JobAppCom.JobAppCom.Model.JobDetailsModel;
import com.JobAppCom.JobAppCom.Model.UserDetailsModel;
import com.JobAppCom.JobAppCom.Repository.JobListRepo;
import com.JobAppCom.JobAppCom.Repository.UserDetailsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobDetails {

    @Autowired
    private JobListRepo repo;

    @Autowired
    public UserDetailsRepo userrepo;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    //@Autowired
    //private UserRepository userRepository;

    public List<JobDetailsModel> getAllJobDetails(){
        return repo.findAll();
    }

    public void deletePostById(int postId){
        repo.deleteById(postId);
    }

    public void saveJobDetails(JobDetailsModel jobDetails){
        repo.save(jobDetails);
    }

    public JobDetailsModel getJobDetailsById(int postId){
        /** JobDetailsModel jdm=new JobDetailsModel();
        jdm.setPostId(postId);
        Example<JobDetailsModel> example=Example.of(jdm);
        List<User> results = userRepository.findAll(example);
        return  results;**/
        Optional<JobDetailsModel> o= repo.findById(postId);
        return o.orElse(new JobDetailsModel());

    }

    @Transactional
    public void saveUserDetails(UserDetailsModel user){
        //user.setPassword(encoder.encode(user.getPassword()));
        userrepo.save(user);
    }

}
