package com.global.university.department;

import com.global.university.base.BaseService;
import com.global.university.track.TrackRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService  extends BaseService<Department,Integer,DepartmentRequest,DepartmentResponse> {

}
