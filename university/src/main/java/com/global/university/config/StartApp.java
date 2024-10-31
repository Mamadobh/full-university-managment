package com.global.university.config;

import com.global.university.authentication.AuthenticationRequest;
import com.global.university.authentication.AuthenticationService;
import com.global.university.authentication.RegistrationRequest;
import com.global.university.role.RoleRequest;
import com.global.university.role.RoleServices;
import com.global.university.student.Student;
import com.global.university.student.StudentRepo;
import com.global.university.teacher.Teacher;
import com.global.university.teacher.TeacherRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;

import static com.global.university.role.FixedRoleEnum.STUDENT;
import static com.global.university.role.FixedRoleEnum.TEACHER;


@Component
@RequiredArgsConstructor
@Log4j2

public class StartApp implements CommandLineRunner {
    private final RoleServices roleServices;
    private final TeacherRepo teacherRepo;
    private final StudentRepo studentRepo;
    private final AuthenticationService authenticationService;

    @Override
    public void run(String... args) throws Exception {
        roleServices.save(new RoleRequest(null, STUDENT.name(), new HashSet<>()));
        roleServices.save(new RoleRequest(null, TEACHER.name(), new HashSet<>()));
        teacherRepo.save(Teacher.builder()
                .firstname("Med")
                .lastname("bh")
                .cin("135077610")
                .passport_number(null) // Assuming passport number is optional
                .nationality("tunisienne")
                .num_tel("531200430")
                .dateOfBirth(LocalDate.of(2001, 9, 19))
                .place_of_birth("Djerba")
                .email("teacher@gmail.com")
                .diploma("Software engineering")
                .rank("expert")
                .diploma_year(LocalDate.of(2020, 5, 25))
                .build());
        studentRepo.save(Student.builder()
                .firstname("Med")
                .lastname("bh")
                .cin("135077611")
                .passport_number(null) // Assuming passport number is optional
                .nationality("tunisienne")
                .num_tel("531200431")
                .dateOfBirth(LocalDate.of(2001, 9, 19))
                .place_of_birth("Djerba")
                .email("student@gmail.com")
                .diploma("Software engineering")
                .status("New")
                .registered_number("123456789")
                .build());
        RegistrationRequest rq = RegistrationRequest.builder()
                .cin("135077611")
                .email("student@gmail.com")
                .type(1)
                .password("med123456")
                .passwordConfirmation("med123456")
                .build();
        AuthenticationRequest ra = AuthenticationRequest.builder()
                .email("student@gmail.com")
                .password("med123456")
                .build();

        authenticationService.register(rq);
        log.info(authenticationService.authenticate(ra).getToken());

    }
}
