package com.global.university.track;

import com.global.university.department.DepartmentRequest;
import com.global.university.department.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class DepartmentAspect{
    private final TrackService trackService;
    @Around("execution(* com.global.university.base.BaseService.update(..)) && args(request, id)")
    public Object aroundUpdate(ProceedingJoinPoint joinPoint, TrackRequest request, Integer id) throws Throwable {
        Object result = joinPoint.proceed();

       
        return  result;
    }
    }
