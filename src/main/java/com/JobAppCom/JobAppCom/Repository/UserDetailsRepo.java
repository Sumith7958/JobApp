package com.JobAppCom.JobAppCom.Repository;

import com.JobAppCom.JobAppCom.Model.UserDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetailsModel,Integer> {
    UserDetailsModel findByUserName(String username);
}
