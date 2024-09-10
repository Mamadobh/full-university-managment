package com.global.university.track;

import com.global.university.common.Mapper;
import com.global.university.department.Department;
import org.springframework.stereotype.Service;

@Service
public class TrackMapper implements Mapper<Track, Integer, TrackRequest, TrackResponse> {


    @Override
    public Track toEntity(TrackRequest request, boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return Track.builder()
                .id(id)
                .name(request.name())
                .description(request.description())
                .department(
                        Department.builder()
                                .id(request.departmentId())
                                .build()
                )
                .build();
    }



    @Override
    public TrackResponse toResponse(Track entity) {
        return TrackResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .departmentId(entity.getDepartment().getId())
                .build();
    }


}
