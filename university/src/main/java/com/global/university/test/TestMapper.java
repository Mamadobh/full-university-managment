package com.global.university.test;

import com.global.university.coefficient.Coefficient;
import com.global.university.coefficient.CoefficientResponse;
import com.global.university.common.Mapper;
import com.global.university.testDuration.TestDuration;
import com.global.university.testDuration.TestDurationResponse;
import com.global.university.testType.TestType;
import com.global.university.testType.TestTypeResponse;
import org.springframework.stereotype.Service;

@Service
public class TestMapper implements Mapper<Test, Integer, TestRequest, TestResponse> {

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


}
