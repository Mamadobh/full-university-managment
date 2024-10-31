package com.global.university.teacher;

import com.global.university.common.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherMapper implements Mapper<Teacher, Integer, TeacherRequest, TeacherResponse> {

    @Override
    public Teacher toEntity(TeacherRequest request, boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return Teacher.builder()
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
                .diploma_year(request.diploma_year())
                .rank(request.rank())
                .build();
    }


    @Override
    public TeacherResponse toResponse(Teacher entity) {
        return TeacherResponse.builder()
                .id(entity.getId())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .email(entity.getEmail())
                .cin(entity.getCin())
                .passport_Number(entity.getPassport_number())
                .num_tel(entity.getNum_tel())
                .place_of_birth(entity.getPlace_of_birth())
                .dateOfBirth(entity.getDateOfBirth())
                .diploma_year(entity.getDiploma_year())
                .diploma(entity.getDiploma())
                .nationality(entity.getNationality())
                .rank(entity.getRank())
                .build();
    }


}
