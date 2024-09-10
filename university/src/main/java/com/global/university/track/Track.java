package com.global.university.track;

import com.global.university.base.BaseEntity;
import com.global.university.department.Department;
import com.global.university.speciality.Speciality;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Track extends BaseEntity<Integer> {
    @Column(unique = true)
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "department_id",nullable = false)
    private Department department;


    @OneToMany(mappedBy = "track")
    private Set <Speciality> specialties;



}
