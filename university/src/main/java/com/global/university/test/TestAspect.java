package com.global.university.test;

import com.global.university.coefficient.CoefficientService;
import com.global.university.exception.DataDuplicationException;
import com.global.university.module.ModuleRequest;
import com.global.university.moduleType.ModuleTypeService;
import com.global.university.semester.SemesterService;
import com.global.university.testDuration.TestDurationService;
import com.global.university.testType.TestTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class TestAspect {
    private final TestTypeService testTypeService;
    private final CoefficientService coefficientService;
    private final TestDurationService testDurationService;
    private final TestRepo testRepo;


    @Before("execution(* com.global.university.base.BaseService.save(..)) && args(request)")
    public void beforeSave(TestRequest request) {
        testDurationService.exist(request.testDuraionId());
        coefficientService.exist(request.coefficientId());
        testTypeService.exist(request.testTypeId());
        if (testRepo.existTestRowWithTypeAndCoefficientAndDuratioon(
                request.testTypeId(),
                request.testDuraionId(),
                request.coefficientId(),
                0
        )
        ) {
            throw new DataDuplicationException("Test already exist with this information ");
        }
    }


}
