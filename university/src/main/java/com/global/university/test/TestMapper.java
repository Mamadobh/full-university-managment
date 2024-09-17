package com.global.university.test;

import com.global.university.coefficient.CoeffcientRepo;
import com.global.university.coefficient.Coefficient;
import com.global.university.coefficient.CoefficientResponse;
import com.global.university.common.Mapper;
import com.global.university.testDuration.TestDuration;
import com.global.university.testDuration.TestDurationRepo;
import com.global.university.testDuration.TestDurationResponse;
import com.global.university.testType.TestType;
import com.global.university.testType.TestTypeRepo;
import com.global.university.testType.TestTypeResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class TestMapper implements Mapper<Test, Integer, TestRequest, TestResponse> {
    private final TestTypeRepo testTypeRepo;
    private final CoeffcientRepo coeffcientRepo;
    private final TestDurationRepo testDurationRepo;

    @Override
    public Test toEntity(TestRequest request, boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return Test.builder()
                .id(id)
                .typeTest(
                        TestType.builder()
                                .id(request.testTypeId())
                                .build()
                )
                .coefficient(
                        Coefficient.builder()
                                .id(request.coefficientId())
                                .build()
                )
                .testDuration(
                        TestDuration.builder()
                                .id(request.testDuraionId())
                                .build()
                )
                .build();
    }


    @Override
    public TestResponse toResponse(Test entity) {
        return TestResponse.builder()
                .id(entity.getId())
                .testType(TestTypeResponse.builder()
                        .id(entity.getTypeTest().getId())
                        .testType(entity.getTypeTest().getTestType())
                        .build())
                .testDuration(TestDurationResponse.builder()
                        .id(entity.getTestDuration().getId())
                        .testDuration(entity.getTestDuration().getTestDuration())
                        .build())
                .coefficient(CoefficientResponse.builder()
                        .id(entity.getCoefficient().getId())
                        .coefficient(entity.getCoefficient().getCoefficient())
                        .build())
                .build();
    }

    public Test toEntity(TestRequest request) {
        return Test.builder()
                .testDuration(
                        testDurationRepo.findById(request.testDuraionId()).orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Data not Found with id " +
                                                request.testDuraionId() +
                                                " Please verify !!"))
                )
                .typeTest(
                        testTypeRepo.findById(request.testTypeId()).orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Data not Found with id " +
                                                request.testTypeId() +
                                                " Please verify !!"))
                )
                .coefficient(
                        coeffcientRepo.findById(request.coefficientId()).orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Data not Found with id " +
                                                request.coefficientId() +
                                                " Please verify !!"))
                )
                .subjects(new HashSet<>())
                .build();
    }
}
