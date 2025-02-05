package com.global.university.authentication;

import com.global.university.person.Person;
import com.global.university.person.PersonRequest;
import com.global.university.role.Role;
import com.global.university.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Mapper {
    private final PasswordEncoder passwordEncoder;

     public Person toPerson(PersonRequest request){
         return Person.builder()
                 .firstname(request.firstname())
                 .lastname(request.lastname())
                 .nationality(request.nationality())
                 .dateOfBirth(request.dateOfBirth())
                 .cin(request.cin())
                 .passport_number(request.passportNumber())
                 .place_of_birth(request.place_of_birth())
                 .num_tel(request.num_tel())
                 .email(request.email())
                 .build();
     }

     public User toBackOfficeUser(AdminRegistraionRequest request,Person person ){
         return User.builder()
                 .email(request.person().email())
                 .password(passwordEncoder.encode(request.password()))
                 .passport_Number(request.person().passportNumber())
                 .cin(request.person().cin())
                 .roles(request.roles().stream().map(roleId ->
                                 Role.builder().id(roleId).build())
                         .collect(Collectors.toSet()))
                 .accountLocked(false)
                 .enabled(true)
                 .person(person)
                 .build();
     }
}
