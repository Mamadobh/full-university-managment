package com.global.university.student;

import com.global.university.common.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentMapper implements Mapper<Student, Integer, StudentRequest, StudentResponse> {

    @Override
    public Student toEntity(StudentRequest request, boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return Student.builder()
                .id(id)
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .cin(request.cin())
                .passport_number(request.passport_number())
                .place_of_birth(request.place_of_birth())
                .dateOfBirth(request.dateOfBirth())
                .nationality(request.nationality())
                .num_tel(request.num_tel())
                .diploma(request.diploma())
                .registered_number(request.registered_number())
                .status(request.status())
                .build();
    }


    @Override
    public StudentResponse toResponse(Student entity) {
        return StudentResponse.builder()
                .id(entity.getId())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .email(entity.getEmail())
                .cin(entity.getCin())
                .passport_Number(entity.getPassport_number())
                .num_tel(entity.getNum_tel())
                .place_of_birth(entity.getPlace_of_birth())
                .dateOfBirth(entity.getDateOfBirth())
                .registered_number(entity.getRegistered_number())
                .diploma(entity.getDiploma())
                .nationality(entity.getNationality())
                .status(entity.getStatus())
                .build();
    }


}
