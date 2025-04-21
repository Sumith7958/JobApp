package com.JobAppCom.JobAppCom.Aop;


import com.JobAppCom.JobAppCom.Repository.JobListRepo;
import com.JobAppCom.JobAppCom.Service.LogsInfoService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Autowired
    private LogsInfoService lis;

    @Before("execution(* com.JobAppCom.JobAppCom.Controler.JobDetailsController.*(..))")
    public void noteLogStart(JoinPoint jp){
        String method=jp.getSignature().getName();
        double start=System.currentTimeMillis();
        lis.saveLogsData(method,start);
    }

    @AfterThrowing(pointcut = "execution(* com.JobAppCom.JobAppCom.Controler.JobDetailsController.*(..))",throwing = "ex")
    public void noteTheErrorLogs(JoinPoint jp,Throwable ex){
        String method=jp.getSignature().getName();
        String exception=ex.getMessage();
        lis.saveLogsExceptionData(method,exception);
    }

}
