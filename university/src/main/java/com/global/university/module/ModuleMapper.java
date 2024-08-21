package com.global.university.level;

import com.global.university.common.Mapper;
import com.global.university.speciality.Speciality;
import org.springframework.stereotype.Service;

@Service
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
                .specialityId(entity.getSpeciality().getId())
                .build();
    }


}
