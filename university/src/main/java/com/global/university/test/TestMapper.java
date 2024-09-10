package com.global.university.testType;

import com.global.university.common.Mapper;
import org.springframework.stereotype.Service;

@Service
public class TestTypeMapper implements Mapper<TestType, Integer, TestTypeRequest, TestTypeResponse> {

    @Override
    public TestType toEntity(TestTypeRequest request, boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return TestType.builder()
                .id(id)
                .testType(request.testType())
                .build();
    }


    @Override
    public TestTypeResponse toResponse(TestType entity) {
        return TestTypeResponse.builder()
                .id(entity.getId())
                .testType(entity.getTestType())
                .build();
    }


}
