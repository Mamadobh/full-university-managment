package com.global.university.semester;

import com.global.university.common.Mapper;
import com.global.university.level.Level;
import com.global.university.level.LevelRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SemesterMapper implements Mapper<Semester, Integer, SemesterRequest, SemesterResponse> {
    private final LevelRepo levelRepo;

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
                .level(
                        levelRepo.findById(request.levelId()).orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Data not Found with id " +
                                                request.levelId() +
                                                " Please verify !!"))
                )
                .build();
    }
}
