package com.JobAppCom.JobAppCom.Aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceMonitoring {

    private static final Logger LOGGER1= LoggerFactory.getLogger(PerformanceMonitoring.class);

    @Around("execution(* com.JobAppCom.JobAppCom.Controler.JobDetailsController.*(..))")
    public Object timeOfExecution(ProceedingJoinPoint jp) throws Throwable {
        double start=System.currentTimeMillis();
        Object obj=jp.proceed();
        double end=System.currentTimeMillis();
        LOGGER1.info("Time of execution of method : "+jp.getSignature().getName()+" Time Taken : "+(end-start));
        return obj;
    }
}
