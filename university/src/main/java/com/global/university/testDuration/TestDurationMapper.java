package com.global.university.testDuration;

import com.global.university.common.Mapper;
import com.global.university.testDuration.TestDuration;
import com.global.university.testDuration.TestDurationRequest;
import com.global.university.testDuration.TestDurationResponse;
import org.springframework.stereotype.Service;

@Service
public class TestDurationMapper implements Mapper<TestDuration, Integer, TestDurationRequest, TestDurationResponse> {

    @Override
    public TestDuration toEntity(TestDurationRequest request, boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return TestDuration.builder()
                .id(id)
                .testDuration(request.testDuration())
                .build();
    }


    @Override
    public TestDurationResponse toResponse(TestDuration entity) {
        return TestDurationResponse.builder()
                .id(entity.getId())
                .testDuration(entity.getTestDuration())
                .build();
    }


}
