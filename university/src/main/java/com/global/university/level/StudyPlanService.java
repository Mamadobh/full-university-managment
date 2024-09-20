package com.global.university.level;

import com.global.university.exception.DataDuplicationException;
import com.global.university.module.Module;
import com.global.university.module.ModuleMapper;
import com.global.university.module.ModuleRequest;
import com.global.university.module.ModuleStudyPlanResponse;
import com.global.university.response.Response;
import com.global.university.semester.*;
import com.global.university.subject.Subject;
import com.global.university.subject.SubjectMapper;
import com.global.university.subject.SubjectStudyPlanRequest;
import com.global.university.subject_type.SubjectType;
import com.global.university.subject_type.SubjectTypeMapper;
import com.global.university.subject_type.SubjectTypeStudyPlanRequest;
import com.global.university.test.Test;
import com.global.university.test.TestMapper;
import com.global.university.test.TestRequest;
import com.global.university.test.TestService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudyPlanService {
    private final SemesterMapper semesterMapper;
    private final ModuleMapper moduleMapper;
    private final TestService testService;
    private final TestMapper testMapper;
    private final SubjectMapper subjectMapper;
    private final SubjectTypeMapper subjectTypeMapper;
    private final SemesterRepo semesterRepo;
    private final LevelRepo levelRepo;

    public StudyPlanResponse findStudyPlan(Integer levelId) {

        Level level = levelRepo.findById(levelId)
                .orElseThrow(() ->
                        new EntityNotFoundException(" Data not Found with id " + levelId + " Please verify !!"));

        return StudyPlanResponse.builder()
                .semesters(
                        level
                                .getSemesters()
                                .stream()
                                .map(semesterMapper::toResponseStudyPlan)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Transactional
    public Integer AddFullStudyPlan(StudyPlanRequest request) {
        Map<String, Test> testCache = new HashMap<>();

        if (Semester.checkDuplicationSemester(request.semesters())) {
            throw new DataDuplicationException("Can not have duplication of semester !!");
        }
        request.semesters().forEach((semesterReq) -> {
            Semester semester = semesterMapper.toEntity(semesterReq);
            List<Module> modules = this.getModule(semesterReq.modules(), semester, testCache);
            semester.setModules(new HashSet<>(modules));
            semesterRepo.save(semester);
        });
        return 1;
    }


    private List<Module> getModule(List<ModuleRequest> modulesRequest, Semester semester, Map<String, Test> testCache) {
        if (Module.checkDuplicationModule(modulesRequest)) {
            throw new DataDuplicationException("Can not have duplication of module !!");
        }
        return modulesRequest.stream().map((moduleReq) -> {
            Module module = moduleMapper.toEntity(moduleReq, semester);
            List<Subject> subjects = this.getSubjects(moduleReq.subjects(), module, testCache);
            module.setSubjects(new HashSet<>(subjects));
            return module;
        }).collect(Collectors.toList());
    }

    private List<Subject> getSubjects(List<SubjectStudyPlanRequest> subjectsRequest,
                                      Module module, Map<String, Test> testCache) {

        if (Subject.checkDuplicationSubject(subjectsRequest)) {
            throw new DataDuplicationException("Can not have duplication of subject !!");
        }
        return subjectsRequest.stream().map((subjectReq) -> {
            Subject subject = subjectMapper.toEntity(subjectReq, module);
            List<SubjectType> subjectTypes = this.getSubjectTypes(subjectReq.subjectTypes(), subject);
            List<Test> tests = this.getTest(subjectReq.tests(), subject, testCache);
            subject.setTests(new HashSet<>(tests));
            subject.setSubjectTypes(new HashSet<>(subjectTypes));
            return subject;
        }).collect(Collectors.toList());
    }

    private List<Test> getTest(List<TestRequest> testsRequest,
                               Subject subject,
                               Map<String, Test> testCache) {
        return testsRequest.stream().map((testReq) -> {
                    log.error("the find request ", testsRequest);
                    log.error(" the find result is ", testService.findTestByDuraionAndCOefficientAndType(testReq));
                    String testKey = testReq.testDuraionId() + "_" + testReq.coefficientId() + "_" + testReq.testTypeId();

                    return testCache.computeIfAbsent(testKey, key ->
                            testService.findTestByDuraionAndCOefficientAndType(testReq)
                                    .orElseGet(() -> testMapper.toEntity(testReq))
                    );

                })
                .collect(Collectors.toList());
    }

    private List<SubjectType> getSubjectTypes(List<SubjectTypeStudyPlanRequest> subjectTypesRequest,
                                              Subject subject) {
        if (Subject.checkDuplicateSubjectType(subjectTypesRequest)) {
            throw new DataDuplicationException("Can not have duplication of type for a subject !!");
        }
        return subjectTypesRequest.stream().map((subjectTypeReq) ->
                subjectTypeMapper.toEntity(subjectTypeReq, subject)
        ).collect(Collectors.toList());
    }


}