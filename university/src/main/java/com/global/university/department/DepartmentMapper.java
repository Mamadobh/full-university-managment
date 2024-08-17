package com.global.university.department;

import com.global.university.common.Mapper;
import org.springframework.stereotype.Service;

@Service
public class DepartmentMapper implements Mapper<Department, Integer, DepartmentRequest, DepartmentResponse> {


    @Override
    public Department toEntity(DepartmentRequest request) {
        return Department.builder()
                .name(request.name())
                .description(request.description())
                .build();
    }

    @Override
    public DepartmentResponse toResponse(Department entity) {
        return DepartmentResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

}
