package com.global.university.module;

import com.global.university.common.Mapper;
import com.global.university.moduleType.ModuleType;
import com.global.university.semester.Semester;
import com.global.university.speciality.Speciality;
import org.springframework.stereotype.Service;

@Service
public class ModuleMapper implements Mapper<Module, Integer, ModuleRequest, ModuleResponse> {

    @Override
    public Module toEntity(ModuleRequest request, boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return Module.builder()
                .id(id)
                .name(request.name())
                .description(request.description())
                .semester(
                        Semester.builder()
                                .id(request.semesterId())
                                .build()
                )
                .moduleType(
                        ModuleType.builder()
                                .id(request.moduleTypeId())
                                .build()
                )
                .build();
    }


    @Override
    public ModuleResponse toResponse(Module entity) {
        return ModuleResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .semesterId(entity.getSemester().getId())
                .moduleTypeId(entity.getModuleType() != null ? entity.getModuleType().getId() : null)
                .build();
    }


}
