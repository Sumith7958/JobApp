package com.JobAppCom.JobAppCom.Service;

import com.JobAppCom.JobAppCom.Model.LogExceptionInfoModel;
import com.JobAppCom.JobAppCom.Model.Logsinfo;
import com.JobAppCom.JobAppCom.Repository.LogExceptionRepo;
import com.JobAppCom.JobAppCom.Repository.LogsInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogsInfoService {

    @Autowired
    private LogsInfoRepo repo;

    @Autowired
    private LogExceptionRepo repoex;

    public void saveLogsData(String method,double start) {
        Integer maxId;
        try {
            maxId = repo.findMaxId();
        } catch (Exception e) {
            System.err.println("⚠️ Table not found or query failed: " + e.getMessage());
            maxId = 0;
        }
        Integer newId = maxId + 1;
        Logsinfo obj = new Logsinfo();
        obj.setLogCounts(newId);
        obj.setMethod(method);
        obj.setStartTime(start);
        repo.save(obj);
    }

    public void saveLogsExceptionData(String method,String Exception){
        Integer maxId;
        try {
            maxId = repo.findMaxId();
        } catch (Exception e) {
            System.err.println("⚠️ Table not found or query failed: " + e.getMessage());
            maxId = 0;
        }
        Integer newId = maxId + 1;
        LogExceptionInfoModel obj=new LogExceptionInfoModel();
        obj.setMethod(method);
        obj.setExceptionReason(Exception);
        obj.setId(newId);
        repoex.save(obj);
    }
}
