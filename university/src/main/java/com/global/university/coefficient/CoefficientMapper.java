package com.global.university.coefficient;

import com.global.university.common.Mapper;
import org.springframework.stereotype.Service;

@Service
public class CoefficientMapper implements Mapper<Coefficient, Integer, CoefficientRequest, CoefficientResponse> {

    @Override
    public Coefficient toEntity(CoefficientRequest request, boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return Coefficient.builder()
                .id(id)
                .coefficient(request.coefficient())
                .build();
    }


    @Override
    public CoefficientResponse toResponse(Coefficient entity) {
        return CoefficientResponse.builder()
                .id(entity.getId())
                .coefficient(entity.getCoefficient())
                .build();
    }


}
