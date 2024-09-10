package com.global.university.semester;


import com.global.university.base.BaseEntity;
import com.global.university.department.Department;
import com.global.university.level.Level;
import com.global.university.module.Module;
import com.global.university.speciality.Speciality;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@NamedEntityGraph(name = "loadLevel",attributeNodes = @NamedAttributeNode("level"))
public class Semester extends BaseEntity<Integer> {

    @Column(unique = true)
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne()
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;


    @OneToMany(mappedBy = "semester")
    private Set<Module> modules;
}
