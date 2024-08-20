package com.global.university.speciality;

import com.global.university.base.BaseService;
import com.global.university.department.DepartmentService;
import com.global.university.track.TrackMapper;
import com.global.university.track.TrackRepo;
import com.global.university.track.TrackRequest;
import com.global.university.track.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialityService extends BaseService<Speciality, Integer, SpecialityRequest, SpecialityResponse> {
    @Autowired
    private TrackService trackService;
    @Autowired
    SpecialityRepo specialityRepo;
    @Autowired
    private SpecialityMapper mapper;


    @Override
    public Integer update(SpecialityRequest request, Integer id) {
        exist(id);
        trackService.exist(request.trackId());
        return specialityRepo.save(mapper.toEntity(request, true)).getId();
    }


}