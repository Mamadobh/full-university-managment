package com.global.university.authentication;

import com.global.university.person.Person;
import com.global.university.role.Role;
import com.global.university.role.RoleRepo;
import com.global.university.security.JwtService;
import com.global.university.student.Student;
import com.global.university.student.StudentRepo;
import com.global.university.teacher.Teacher;
import com.global.university.teacher.TeacherRepo;
import com.global.university.user.User;
import com.global.university.user.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import static com.global.university.role.FixedRoleEnum.STUDENT;
import static com.global.university.role.FixedRoleEnum.TEACHER;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TeacherRepo teacherRepo;
    private final StudentRepo studentRepo;
    private final PasswordEncoder passwordEncoder;

    public Integer register(RegistrationRequest request) {
        String role_name = request.type() == 0 ? TEACHER.name() : STUDENT.name();
        Person person;
        if (request.type() == 0) {
            person = checkTeacher(request);
        } else {
            person = checkStudent(request);
        }
        Role role = roleRepo.findByName(role_name)
                .orElseThrow(() -> new IllegalStateException("Role User was not initialized"));
        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .passport_Number(request.passportNumber())
                .cin(request.cin())
                .roles(Set.of(role))
                .accountLocked(false)
                .enabled(true)
                .person(person)
                .build();
        return userRepo.save(user).getId();
    }

    private Teacher checkTeacher(RegistrationRequest request) {
        Optional<Teacher> teacher = Optional.empty();

        if (request.cin() != null) {
            teacher = teacherRepo.findByEmailAndCin(request.email(), request.cin());
        } else if (request.passportNumber() != null) {
            teacher = teacherRepo.findByEmailAndNumPassport(request.email(), request.passportNumber());
        }

        return teacher.orElseThrow(() -> new EntityNotFoundException(" Teacher not Found  with entred data "));
    }


    private Student checkStudent(RegistrationRequest request) {

        Optional<Student> student = Optional.empty();
        if (request.cin() != null) {
            student = studentRepo.findByEmailAndCin(request.email(),
                    request.cin()
            );
        } else if (request.passportNumber() != null) {
            student = studentRepo.findByEmailAndNumPassport(request.email(),
                    request.passportNumber()
            );
        }
        return student.orElseThrow(() -> new EntityNotFoundException(" Student not Found  with entred data "));
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("fullName", user.getPerson().getfullName());
        var jwtToken = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .id(user.getId())
                .build();
    }
}
