package com.JobAppCom.JobAppCom.Repository;

import com.JobAppCom.JobAppCom.Model.Logsinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface LogsInfoRepo extends JpaRepository<Logsinfo,Long> {

    @Query("SELECT COALESCE(MAX(l.id), 0) FROM Logsinfo l")
    Integer findMaxId();
}
