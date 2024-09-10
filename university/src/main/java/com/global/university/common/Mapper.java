package com.global.university.common;

import com.global.university.base.BaseEntity;
import org.springframework.stereotype.Component;

@Component
public interface Mapper<T extends BaseEntity<ID>, ID, DtoRequest, DtoResponse> {
    T toEntity(DtoRequest request,boolean isUpdate);

    DtoResponse toResponse(T entity);
}
