package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component   // ❗非常重要：没有它，AOP 永远不生效
public class LogAspect {

    @Around("execution(* com.example.service..*(..))")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println(">>> AOP BEFORE："
                + pjp.getSignature().toShortString());

        Object result = pjp.proceed();

        System.out.println("<<< AOP AFTER："
                + pjp.getSignature().toShortString());

        return result;
    }
}
