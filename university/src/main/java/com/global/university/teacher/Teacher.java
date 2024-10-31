package com.global.university.teacher;


import com.global.university.person.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "TEACHER_ID")
@EqualsAndHashCode(callSuper = true)
public class Teacher extends Person {
    private String diploma;
    private String rank;
    private LocalDate diploma_year;


}
