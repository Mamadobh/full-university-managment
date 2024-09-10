package com.global.university.module;

import com.global.university.base.BaseService;
import com.global.university.exception.OperationNotPermittedException;
import com.global.university.level.Level;
import com.global.university.level.LevelRepo;
import com.global.university.level.LevelService;
import com.global.university.module.*;
import com.global.university.moduleType.ModuleTypeService;
import com.global.university.semester.SemesterService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ModuleService extends BaseService<Module, Integer, ModuleRequest, ModuleResponse> {
    @Autowired
    private SemesterService semesterService;
    @Autowired
    ModuleRepo moduleRepo;
    @Autowired
    private ModuleMapper mapper;
    @Autowired
    private ModuleTypeService moduleTypeService;



    @Override
    public Integer update(ModuleRequest request, Integer id) {
        exist(id);
        semesterService.exist(request.semesterId());
        moduleTypeService.exist(request.moduleTypeId());
        return moduleRepo.save(mapper.toEntity(request, true)).getId();
    }


}