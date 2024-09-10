package com.global.university.subject_type;

import com.global.university.sessionNumber.SessionNumberService;
import com.global.university.typeSbj.TypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class SubjectTypeAspect {
    @Autowired
    private SessionNumberService sessionNumberService;
    @Autowired
    private TypeService typeService;

    @Before("execution(* com.global.university.base.BaseService.save(..)) && args(request)")
    public void beforeSave(SubjectTypeRequest request) {
        sessionNumberService.exist(request.numberSessionId());
        typeService.exist(request.typeId());

    }


}
