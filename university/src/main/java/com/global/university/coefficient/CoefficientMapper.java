package com.global.university.moduleType;

import com.global.university.common.Mapper;
import com.global.university.module.Module;
import com.global.university.module.ModuleRequest;
import com.global.university.module.ModuleResponse;
import com.global.university.semester.Semester;
import org.springframework.stereotype.Service;

@Service
public class ModuleTypeMapper implements Mapper<ModuleType, Integer, ModuleTypeRequest, ModuleTypeResponse> {

    @Override
    public ModuleType toEntity(ModuleTypeRequest request, boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return ModuleType.builder()
                .id(id)
                .type(request.type())
                .build();
    }


    @Override
    public ModuleTypeResponse toResponse(ModuleType entity) {
        return ModuleTypeResponse.builder()
                .id(entity.getId())
                .type(entity.getType())
                .build();
    }


}
