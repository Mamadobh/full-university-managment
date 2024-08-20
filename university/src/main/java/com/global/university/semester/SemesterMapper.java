package com.global.university.speciality;

import com.global.university.common.Mapper;
import com.global.university.track.Track;
import org.springframework.stereotype.Service;

@Service
public class SpecialityMapper implements Mapper<Speciality, Integer, SpecialityRequest, SpecialityResponse> {

    @Override
    public Speciality toEntity(SpecialityRequest request, boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return Speciality.builder()
                .id(id)
                .name(request.name())
                .description(request.description())
                .track(
                        Track.builder()
                                .id(request.trackId())
                                .build()
                )
                .build();
    }


    @Override
    public SpecialityResponse toResponse(Speciality entity) {
        return SpecialityResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .trackId(entity.getTrack().getId())
                .build();
    }


}
