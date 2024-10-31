package com.global.university.student;

import com.global.university.base.BaseService;
import com.global.university.exception.DataDuplicationException;
import com.global.university.person.PersonRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService extends BaseService<Student, Integer, StudentRequest, StudentResponse> {
    private final StudentRepo studentRepo;
    private final PersonRepo personRepo;

    @Override
    public Integer save(StudentRequest request) {
        checkStudentData(request);
        return super.save(request);
    }

    private void checkStudentData(StudentRequest request) {
        String cin = request.cin() != null ? request.cin() : "w";
        String passportNumber = request.passport_number() != null ? request.passport_number() : "w";
        Integer id = request.id() != null ? request.id() : 0;
        boolean isExist = personRepo.checkExistStudent(
                id,
                request.num_tel(),
                request.email(),
                cin,
                passportNumber,
                request.registered_number()
        );
        if (isExist) {
            throw new DataDuplicationException("Can not add student with information already exist !!");
        }
    }


    @Override
    public Integer update(StudentRequest request, Integer id) {
        checkStudentData(request);
        return super.update(request, id);
    }


}