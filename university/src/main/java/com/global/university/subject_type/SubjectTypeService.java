package com.global.university.subject_type;

import com.global.university.base.BaseService;
import com.global.university.sessionNumber.SessionNumberService;
import com.global.university.typeSbj.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SubjectTypeService extends BaseService<SubjectType, Integer, SubjectTypeRequest, SubjectTypeResponse> {
    @Autowired
    private SessionNumberService sessionNumberService;
    @Autowired
    private TypeService typeService;
    @Autowired
    SubjectTypeRepo subjectTypeRepo;
    @Autowired
    private SubjectTypeMapper mapper;




    @Override
    public Integer update(SubjectTypeRequest request, Integer id) {
        exist(id);
        typeService.exist(request.typeId());
        sessionNumberService.exist(request.numberSessionId());
        return subjectTypeRepo.save(mapper.toEntity(request, true)).getId();
    }


}