package com.global.university.level;

import com.global.university.exception.DataDuplicationException;
import com.global.university.module.Module;
import com.global.university.module.ModuleMapper;
import com.global.university.module.ModuleRequest;
import com.global.university.semester.Semester;
import com.global.university.semester.SemesterMapper;
import com.global.university.semester.SemesterRepo;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyPlanService {
    private final SemesterMapper semesterMapper;
    private final ModuleMapper moduleMapper;
    private final TestService testService;
    private final TestMapper testMapper;
    private final SubjectMapper subjectMapper;
    private final SubjectTypeMapper subjectTypeMapper;
    private final SemesterRepo semesterRepo;


    @Transactional
    public Integer AddFullStudyPlan(StudyPlanRequest request) {
        if (Semester.checkDuplicationSemester(request.semesters())) {
            throw new DataDuplicationException("Can not have duplication of semester !!");
        }
        request.semesters().forEach((semesterReq) -> {
            Semester semester = semesterMapper.toEntity(semesterReq);
            List<Module> modules = this.getModule(semesterReq.modules(), semester);
            semester.setModules(new HashSet<>(modules));
            semesterRepo.save(semester);
        });
        return 1;
    }

    private List<Module> getModule(List<ModuleRequest> modulesRequest, Semester semester) {
        if (Module.checkDuplicationModule(modulesRequest)) {
            throw new DataDuplicationException("Can not have duplication of module !!");
        }
        return modulesRequest.stream().map((moduleReq) -> {
            Module module = moduleMapper.toEntity(moduleReq, semester);
            List<Subject> subjects = this.getSubjects(moduleReq.subjects(), module);
            module.setSubjects(new HashSet<>(subjects));
            return module;
        }).collect(Collectors.toList());
    }

    private List<Subject> getSubjects(List<SubjectStudyPlanRequest> subjectsRequest,
                                      Module module) {

        if (Subject.checkDuplicationSubject(subjectsRequest)) {
            throw new DataDuplicationException("Can not have duplication of subject !!");
        }
        return subjectsRequest.stream().map((subjectReq) -> {
            Subject subject = subjectMapper.toEntity(subjectReq, module);
            List<SubjectType> subjectTypes = this.getSubjectTypes(subjectReq.subjectTypes(), subject);
            List<Test> tests = this.getTest(subjectReq.tests(), subject);
            subject.setTests(new HashSet<>(tests));
            subject.setSubjectTypes(new HashSet<>(subjectTypes));
            return subject;
        }).collect(Collectors.toList());
    }

    private List<Test> getTest(List<TestRequest> testsRequest,
                               Subject subject) {
        return testsRequest.stream().map((testReq) -> {
                    Test test = testService.findTestByDuraionAndCOefficientAndType(testReq)
                            .orElseGet(() -> testMapper.toEntity(testReq));
                    test.getSubjects().add(subject);
                    return test;
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