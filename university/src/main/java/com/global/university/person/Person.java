package com.global.university.person;

import com.global.university.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    protected String firstname;
    protected String lastname;

    protected String cin;
    protected String passport_number;
    protected String nationality;
    @Column(unique = true)
    protected String num_tel;
    protected LocalDate dateOfBirth;
    protected String place_of_birth;
    @Column(unique = true)
    protected String email;

    public String getfullName() {
        return firstname + " " + lastname;
    }

}
