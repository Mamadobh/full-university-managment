package com.global.university.user;

import com.global.university.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends BaseEntity<Integer>{
    private String firstname;
    private String lastname;
    private String cin;
    private String passport_Number;
    private String Nationality;
    private String num_tel;
    private LocalDate dateOfBirth;
    private String place_of_birth;
    private String email;

    public String getfullName() {
        return firstname + " " + lastname;
    }

}
