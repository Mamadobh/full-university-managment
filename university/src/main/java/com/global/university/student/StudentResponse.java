package com.global.university.student;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
    private Integer id;
    private String firstname;
    private String lastname;
    private String cin;
    private String passport_Number;
    private String nationality;
    private String num_tel;
    private LocalDate dateOfBirth;
    private String place_of_birth;
    private String email;
    private String diploma;
    private String registered_number;
    private String status;
}