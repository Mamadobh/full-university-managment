package com.global.university.level;

import com.global.university.common.Mapper;
import com.global.university.file.FileUtils;
import com.global.university.semester.SemesterRequest;
import com.global.university.semester.SemesterStudyPlanRequest;
import com.global.university.speciality.Speciality;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LevelMapper implements Mapper<Level, Integer, LevelRequest, LevelResponse> {

    @Override
    public Level toEntity(LevelRequest request, boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return Level.builder()
                .id(id)
                .name(request.name())
                .description(request.description())
                .speciality(
                        Speciality.builder()
                                .id(request.specialityId())
                                .build()
                )
                .build();
    }


    @Override
    public LevelResponse toResponse(Level entity) {
        return LevelResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .studyPlan(FileUtils.readFileFromLocation(entity.getStudyPlan()))
                .specialityId(entity.getSpeciality().getId())
                .build();
    }

    public LevelDetailsReponse toResponseDetails(Level entity) {
        return LevelDetailsReponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .department(entity.getSpeciality().getTrack().getDepartment().getName())
                .specialityWithTrack(entity.getLevelSpecialityWithTrack())
                .build();
    }


}
