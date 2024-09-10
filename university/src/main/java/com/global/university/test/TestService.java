package com.global.university.test;

import com.global.university.base.BaseService;
import com.global.university.coefficient.CoefficientService;
import com.global.university.exception.DataDuplicationException;
import com.global.university.testDuration.TestDurationService;
import com.global.university.testType.TestTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestService extends BaseService<Test, Integer, TestRequest, TestResponse> {

    @Autowired
    private TestTypeService testTypeService;
    @Autowired
    private CoefficientService coefficientService;
    @Autowired
    private TestDurationService testDurationService;
    @Autowired
    private TestRepo testRepo;
    @Autowired
    private TestMapper mapper;

    @Override
    public Integer update(TestRequest request, Integer id) {
        exist(id);
        testDurationService.exist(request.testDuraionId());
        coefficientService.exist(request.coefficientId());
        testTypeService.exist(request.testTypeId());
        if (testRepo.existTestRowWithTypeAndCoefficientAndDuratioon(
                request.testTypeId(),
                request.testDuraionId(),
                request.coefficientId(),
                id)
        ) {
            throw new DataDuplicationException("Test already exist with this information ");
        } else {
            return testRepo.save(mapper.toEntity(request, true)).getId();
        }
    }
}