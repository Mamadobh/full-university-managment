package com.global.university.track;

import com.global.university.base.BaseService;
import com.global.university.department.DepartmentRequest;
import com.global.university.department.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackService extends BaseService<Track, Integer, TrackRequest, TrackResponse> {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    TrackRepo trackRepo;
    @Autowired
    private TrackMapper mapper;



    @Override
    public Integer update(TrackRequest request, Integer id) {
        exist(id);
        departmentService.exist(request.departmentId());
        return trackRepo.save(mapper.toEntity(request, true)).getId();

    }
}
