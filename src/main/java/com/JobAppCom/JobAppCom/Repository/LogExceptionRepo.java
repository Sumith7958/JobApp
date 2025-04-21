package com.JobAppCom.JobAppCom.Repository;

import com.JobAppCom.JobAppCom.Model.LogExceptionInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LogExceptionRepo extends JpaRepository<LogExceptionInfoModel,Integer> {

    @Query("SELECT COALESCE(MAX(l.id), 0) FROM LogExceptionInfoModel l")
    Integer findMaxId();

}
