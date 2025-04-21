package com.JobAppCom.JobAppCom.Service;


import com.JobAppCom.JobAppCom.Model.UserDetailsModel;
import com.JobAppCom.JobAppCom.Repository.UserDetailsRepo;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserService implements UserDetailsService {

    @Autowired
    public UserDetailsRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsModel user=repo.findByUserName(username);
        if(user==null){
            throw new UsernameNotFoundException("User Not found");
        }

        return new UserPrinciple(user);
    }
}
