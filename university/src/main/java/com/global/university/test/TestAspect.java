package com.global.university.module;

import com.global.university.moduleType.ModuleTypeService;
import com.global.university.semester.SemesterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class ModuleAspect {
    private final SemesterService semesterService;
    private final ModuleTypeService moduleTypeService;

    @Before("execution(* com.global.university.base.BaseService.save(..)) && args(request)")
    public void beforeSave(ModuleRequest request) {
        semesterService.exist(request.semesterId());
        moduleTypeService.exist(request.moduleTypeId());
    }


}
