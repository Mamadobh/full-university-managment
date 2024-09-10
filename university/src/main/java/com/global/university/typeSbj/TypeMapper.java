package com.global.university.typeSbj;

import com.global.university.common.Mapper;
import org.springframework.stereotype.Service;

@Service
public class TypeMapper implements Mapper<Type, Integer, TypeRequest, TypeResponse> {

    @Override
    public Type toEntity(TypeRequest request, boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return Type.builder()
                .id(id)
                .subjectType(request.subjectType())
                .build();
    }


    @Override
    public TypeResponse toResponse(Type entity) {
        return TypeResponse.builder()
                .id(entity.getId())
                .subjectType(entity.getSubjectType())
                .build();
    }


}
