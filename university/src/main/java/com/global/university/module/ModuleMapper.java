package com.global.university.module;

import com.global.university.common.Mapper;
import com.global.university.moduleType.ModuleType;
import com.global.university.moduleType.ModuleTypeRepo;
import com.global.university.moduleType.ModuleTypeResponse;
import com.global.university.semester.Semester;
import com.global.university.subject.SubjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModuleMapper implements Mapper<Module, Integer, ModuleRequest, ModuleResponse> {
    private final ModuleTypeRepo moduleTypeRepo;
    private final SubjectMapper subjectMapper;


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
        Integer id = request.moduleTypeId();
        ModuleType moduleType = moduleTypeRepo.findById(request.moduleTypeId()).orElseThrow(() ->
                new EntityNotFoundException(" " +
                        "Data not Found with id " +
                        request.moduleTypeId() +
                        " Please verify !!"));
        return Module.builder()
                .name(request.name())
                .moduleType(
                        moduleType
                )
                .semester(semester)
                .description(request.description())
                .build();
    }

    public ModuleStudyPlanResponse toResponseStudyPlan(Module entity) {
        return ModuleStudyPlanResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .moduleType(
                        ModuleTypeResponse.builder()
                                .id(entity.getModuleType().getId())
                                .type(entity.getModuleType().getType())
                                .build()
                )
                .subjects(entity.getSubjects().stream().map(subjectMapper::toResponse).collect(Collectors.toList()))
                .build();
    }

}
