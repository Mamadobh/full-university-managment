package com.global.university.teacher;

import com.global.university.base.BaseService;
import com.global.university.exception.DataDuplicationException;
import com.global.university.module.Module;
import com.global.university.module.*;
import com.global.university.moduleType.ModuleTypeService;
import com.global.university.person.PersonRepo;
import com.global.university.semester.SemesterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService extends BaseService<Teacher, Integer, TeacherRequest, TeacherResponse> {
    private final TeacherRepo teacherRepo;
    private final PersonRepo personRepo;

    @Override
    public Integer save(TeacherRequest request) {
        checkTeacherData(request);
        return super.save(request);
    }

    private void checkTeacherData(TeacherRequest request) {
        String cin = request.cin() != null ? request.cin() : "w";
        String passportNumber = request.passport_number() != null ? request.passport_number() : "w";
        Integer id = request.id() != null ? request.id() : 0;
        boolean isExist = personRepo.checkExist(
                id,
                request.num_tel(),
                request.email(),
                cin,
                passportNumber);
        if (isExist) {
            throw new DataDuplicationException("Can not add teacher with information already exist !!");
        }
    }


    @Override
    public Integer update(TeacherRequest request, Integer id) {
        checkTeacherData(request);
        return super.update(request, id);
    }


}