package com.global.university.semester;

import com.global.university.common.Mapper;
import com.global.university.level.Level;
import com.global.university.level.LevelMapper;
import com.global.university.level.LevelRepo;
import com.global.university.module.ModuleMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SemesterMapper implements Mapper<Semester, Integer, SemesterRequest, SemesterResponse> {
    private final LevelRepo levelRepo;
    private final ModuleMapper moduleMapper;
    private final LevelMapper levelMapper;

    @Override
    public Semester toEntity(SemesterRequest request, boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return Semester.builder()
                .id(id)
                .name(request.name())
                .description(request.description())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .level(
                        Level.builder()
                                .id(request.levelId())
                                .build()
                )
                .build();
    }


    @Override
    public SemesterResponse toResponse(Semester entity) {
        return SemesterResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .levelId(entity.getLevel().getId())
                .build();
    }

    public Semester toEntity(SemesterStudyPlanRequest request) {

        return Semester.builder()
                .name(request.name())
                .endDate(request.endDate())
                .startDate(request.startDate())
                .description(request.description())
                .level(
                        levelRepo.findById(request.levelId()).orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Data not Found with id " +
                                                request.levelId() +
                                                " Please verify !!"))
                )
                .build();
    }

    public SemesterStudyPlanResponse toResponseStudyPlan(Semester entity) {
        return SemesterStudyPlanResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .level(levelMapper.toResponseDetails(entity.getLevel()))
                .modules(entity.getModules().stream().map(moduleMapper::toResponseStudyPlan).collect(Collectors.toList()))
                .build();
    }
}
