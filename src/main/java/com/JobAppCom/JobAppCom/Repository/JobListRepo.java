package com.JobAppCom.JobAppCom.Repository;
import com.JobAppCom.JobAppCom.Model.JobDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobListRepo extends JpaRepository<JobDetailsModel,Integer> {
}
