package com.global.university.subject;

import com.global.university.coefficient.Coefficient;
import com.global.university.common.Mapper;
import com.global.university.module.Module;
import com.global.university.test.Test;
import com.global.university.test.TestMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectMapper implements Mapper<Subject, Integer, SubjectRequest, SubjectResponse> {
    private TestMapper mapper;

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
                .tests(new HashSet<Test>(
                        Arrays.stream(request.tests()).map(testId -> {
                            return Test.builder().id(testId).build();
                        }).toList()
                ))
                .build();
    }


    @Override
    public SubjectResponse toResponse(Subject entity) {
        return SubjectResponse.builder()
                .id(entity.getId())
                .ModuleId(entity.getModule().getId())
                .coefficient(entity.getCoefficient().getCoefficient())
                .name(entity.getName())
                .tests(
                        entity.getTests().stream().map(mapper::toResponse).collect(Collectors.toList())
                )
                .build();
    }


}
