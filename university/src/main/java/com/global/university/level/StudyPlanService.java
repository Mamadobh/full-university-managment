package com.global.university.level;

import com.global.university.base.BaseEntity;
import com.global.university.exception.DataDuplicationException;
import com.global.university.exception.OperationNotPermittedException;
import com.global.university.file.StudyPlanFileService;
import com.global.university.module.Module;
import com.global.university.module.ModuleMapper;
import com.global.university.module.ModuleRequest;
import com.global.university.semester.Semester;
import com.global.university.semester.SemesterMapper;
import com.global.university.semester.SemesterRepo;
import com.global.university.semester.SemesterService;
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
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
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
    private final SemesterService semesterService;
    private final LevelRepo levelRepo;
    private final StudyPlanFileService studyPlanFileService;
    @PersistenceContext
    private EntityManager entityManager;

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
    public Integer addFullStudyPlan(StudyPlanRequest request, boolean isUpdate) {

        if (request.semesters().size() > 2) {
            throw new OperationNotPermittedException("you can not add more than two semesters ");
        }
        checkSemesterExist(request);

        Map<String, Test> testCache = new HashMap<>();

        if (Semester.checkDuplicationSemester(request.semesters())) {
            throw new DataDuplicationException("Can not have duplication of semester !!");
        }
        request.semesters().forEach((semesterReq) -> {
            Semester semester = semesterMapper.toEntity(semesterReq, isUpdate);
            List<Module> modules = this.getModule(semesterReq.modules(), semester, testCache, isUpdate);
            semester.setModules(new HashSet<>(modules));
            semesterRepo.save(semester);
        });
        return 1;
    }
    @Transactional
    public Integer updateStudyPlan(StudyPlanRequest request, boolean isUpdate) {
        Integer id = request.semesters().get(0).levelId();


        Level level = levelRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Level not found with id " + id)
        );

        for (Semester sem : level.getSemesters()) {
            deleteStudyPlan(sem.getId());
        }

        addFullStudyPlan(request, false);


        return 0;
    }



    public Integer saveStudyPlan(MultipartFile studyPlanFile, Integer levelId) {
        Level level = levelRepo.findById(levelId).orElseThrow(() ->
                new EntityNotFoundException("Level not found with id " + levelId)
        );
        String studyPlanPath = studyPlanFileService.saveFile(studyPlanFile, levelId);
        if (level.getStudyPlan() != null && !level.getStudyPlan().isEmpty()) {
            studyPlanFileService.deleteFile(level.getStudyPlan());
        }
        level.setStudyPlan(studyPlanPath);
        levelRepo.save(level);
        return 1;
    }

    @Transactional
    Integer deleteStudyPlan(Integer id) {

        Semester semester = semesterRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Semester not found with id " + id));
        if (semester != null) {
            entityManager.remove(semester);
        }
        return id;
    }




    private void checkSemesterExist(StudyPlanRequest request) {
        request.semesters().forEach((semester) -> {
            Integer id = (semester.id() != null) ? semester.id() : 0;
            if (semesterRepo.checkIfSemesterNameAlreadyExist(semester.name(), id, semester.levelId())) {
                throw new DataDuplicationException("semester with name " + semester.name() + " is already exist ");
            }
        });
    }


    private List<Module> getModule(List<ModuleRequest> modulesRequest, Semester semester, Map<String, Test> testCache, boolean isUpdated) {
        if (Module.checkDuplicationModule(modulesRequest)) {
            throw new DataDuplicationException("Can not have duplication of module !!");
        }
        return modulesRequest.stream().map((moduleReq) -> {
            Module module = moduleMapper.toEntity(moduleReq, semester, isUpdated);
            List<Subject> subjects = this.getSubjects(moduleReq.subjects(), module, testCache, isUpdated);
            module.setSubjects(new HashSet<>(subjects));
            return module;
        }).collect(Collectors.toList());
    }

    private List<Subject> getSubjects(List<SubjectStudyPlanRequest> subjectsRequest,
                                      Module module,
                                      Map<String, Test> testCache,
                                      boolean isUpdated
    ) {

        if (Subject.checkDuplicationSubject(subjectsRequest)) {
            throw new DataDuplicationException("Can not have duplication of subject !!");
        }
        return subjectsRequest.stream().map((subjectReq) -> {
            Subject subject = subjectMapper.toEntity(subjectReq, module, isUpdated);
            List<SubjectType> subjectTypes = this.getSubjectTypes(subjectReq.subjectTypes(), subject, isUpdated);
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
                    String testKey = testReq.testDuraionId() + "_" + testReq.coefficientId() + "_" + testReq.testTypeId();

                    return testCache.computeIfAbsent(testKey, key ->
                            testService.findTestByDuraionAndCOefficientAndType(testReq)
                                    .orElseGet(() -> testMapper.toEntity(testReq))
                    );

                })
                .collect(Collectors.toList());
    }

    private List<SubjectType> getSubjectTypes(List<SubjectTypeStudyPlanRequest> subjectTypesRequest,
                                              Subject subject,
                                              boolean isUpdate) {
        if (Subject.checkDuplicateSubjectType(subjectTypesRequest)) {
            throw new DataDuplicationException("Can not have duplication of type for a subject !!");
        }
        return subjectTypesRequest.stream().map((subjectTypeReq) ->
                subjectTypeMapper.toEntity(subjectTypeReq, subject, isUpdate)
        ).collect(Collectors.toList());
    }


}