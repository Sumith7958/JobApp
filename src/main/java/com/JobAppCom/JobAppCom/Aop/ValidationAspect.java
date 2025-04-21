package com.JobAppCom.JobAppCom.Aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    public static final Logger LOGGER2= LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.JobAppCom.JobAppCom.Controler.JobDetailsController.getJobDetailsById(..)) && args(id)")
    public Object validationOfInput(ProceedingJoinPoint jp,int id) throws Throwable {
        if(id<0){
            LOGGER2.info("The index input is negetive!");
            id=-id;
            LOGGER2.info("ID is made positive");
        }
        Object obj;
        obj = jp.proceed(new Object[]{id});
        return obj;
    }

}
