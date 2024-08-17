package com.global.university.track;

import com.global.university.base.BaseService;
import com.global.university.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackService extends BaseService<Track, Integer, TrackRequest, TrackResponse> {
    @Autowired
    private DepartmentService departmentService;


    @Override
    public Integer save(TrackRequest trackRequest) {
        departmentService.findById(trackRequest.departmentId());
        return super.save(trackRequest);
    }
}
