package com.JobAppCom.JobAppCom.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Logsinfo {
    @Id
    private int logCounts;
    private String method;
    private double startTime;

    public int getLogCounts() {
        return logCounts;
    }

    public void setLogCounts(int logCounts) {
        this.logCounts = logCounts;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }
}
