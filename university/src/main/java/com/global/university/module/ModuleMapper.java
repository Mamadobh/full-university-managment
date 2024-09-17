package com.global.university.module;

import com.global.university.common.Mapper;
import com.global.university.moduleType.ModuleType;
import com.global.university.moduleType.ModuleTypeRepo;
import com.global.university.semester.Semester;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModuleMapper implements Mapper<Module, Integer, ModuleRequest, ModuleResponse> {
    private final ModuleTypeRepo moduleTypeRepo;

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

    public Module toEntity(ModuleRequest request, Semester semester) {
        return Module.builder()
                .name(request.name())
                .moduleType(
                        moduleTypeRepo.findById(request.moduleTypeId()).orElseThrow(() ->
                                new EntityNotFoundException(" " +
                                        "Data not Found with id " +
                                        request.moduleTypeId() +
                                        " Please verify !!"))
                )
                .semester(semester)
                .description(request.description())
                .build();
    }


}
