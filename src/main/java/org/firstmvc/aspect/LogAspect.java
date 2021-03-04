package org.firstmvc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    @Before("execution(* org.firstmvc.service.*.*(..))")
    public void beforeServiceMethodInvocation(JoinPoint joinPoint) {
        System.out.println("Before invocation of method:" + joinPoint.getSignature().toString());
    }

    @After("execution(* org.firstmvc.service.*.*(..))")
    public void afterServiceMethodInvocation(JoinPoint joinPoint) {
        System.out.println("After invocation of method:" + joinPoint.getSignature().toString());
    }

    @Around("execution(* org.firstmvc.service.*.*(..))")
    public Object aroundServiceMethodInvocation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object res = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("Time of executing " + proceedingJoinPoint.getSignature().toString() + ": "
                + (end - start) + "ms.");
        return res;
    }
}
