package com.global.university.subject;

import com.global.university.coefficient.CoeffcientRepo;
import com.global.university.coefficient.Coefficient;
import com.global.university.coefficient.CoefficientResponse;
import com.global.university.common.Mapper;
import com.global.university.module.Module;
import com.global.university.subject_type.SubjectTypeMapper;
import com.global.university.test.Test;
import com.global.university.test.TestMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectMapper implements Mapper<Subject, Integer, SubjectRequest, SubjectResponse> {
    private final TestMapper mapper;
    private final CoeffcientRepo coeffcientRepo;
    private final SubjectTypeMapper subjectTypeMapper;

    @Override
    public Subject toEntity(SubjectRequest request, boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return Subject.builder()
                .id(id)
                .name(request.name())
                .coefficient(Coefficient.builder()
                        .id(request.coefficientId())
                        .build()
                )
                .module(Module.builder()
                        .id(request.moduleId())
                        .build()
                )
                .tests(new HashSet<>(
                        request.tests().stream().map(testId ->
                                Test.builder().id(testId).build()
                        ).toList()
                ))
                .build();
    }


    @Override
    public SubjectResponse toResponse(Subject entity) {
        return SubjectResponse.builder()
                .id(entity.getId())
                .coefficient(CoefficientResponse.builder()
                        .id(entity.getCoefficient().getId())
                        .coefficient(entity.getCoefficient().getCoefficient())
                        .build())
                .name(entity.getName())
                .tests(
                        entity.getTests().stream().map(mapper::toResponse).collect(Collectors.toList())
                )
                .subjectTypes(
                        entity.getSubjectTypes().stream().map(subjectTypeMapper::toResponse).collect(Collectors.toList())
                )
                .build();
    }


    public Subject toEntity(SubjectStudyPlanRequest request, Module module,boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return Subject.builder()
                .id(id)
                .name(request.name())
                .coefficient(
                        coeffcientRepo.findById(request.coefficientId()).orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Data not Found with id " +
                                                request.coefficientId() +
                                                " Please verify !!"))
                )
                .module(module)
                .build();
    }

}
