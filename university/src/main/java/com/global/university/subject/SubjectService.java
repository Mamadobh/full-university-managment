package com.global.university.subject;

import com.global.university.base.BaseService;
import com.global.university.coefficient.CoefficientService;
import com.global.university.module.ModuleService;
import com.global.university.test.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class SubjectService extends BaseService<Subject, Integer, SubjectRequest, SubjectResponse> {

    @Autowired
    SubjectRepo subjectRepo;
    @Autowired
    private SubjectMapper mapper;
    @Autowired
    private TestService testService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private CoefficientService coefficientService;


    @Override
    public Integer update(SubjectRequest request, Integer id) {
        exist(id);
        moduleService.exist(request.moduleId());
        coefficientService.exist(request.coefficientId());
        Arrays.asList(request.tests()).forEach(testService::exist);
        return subjectRepo.save(mapper.toEntity(request, true)).getId();
    }


}