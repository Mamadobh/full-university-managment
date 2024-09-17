package com.global.university.subject;

import com.global.university.coefficient.CoefficientService;
import com.global.university.module.ModuleService;
import com.global.university.sessionNumber.SessionNumberService;
import com.global.university.test.TestService;
import com.global.university.typeSbj.TypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class SubjectAspect {

    private final TestService testService;
    private final ModuleService moduleService;
    private final CoefficientService coefficientService;

    @Before("execution(* com.global.university.base.BaseService.save(..)) && args(request)")
    public void beforeSave(SubjectRequest request) {
        moduleService.exist(request.moduleId());
        coefficientService.exist(request.coefficientId());
       request.tests().forEach(testService::exist);

    }


}
