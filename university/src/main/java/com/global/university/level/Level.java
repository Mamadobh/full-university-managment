package com.global.university.level;

import com.global.university.base.BaseEntity;
import com.global.university.module.Module;
import com.global.university.semester.Semester;
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
@NamedEntityGraph(name = "loadSpeciality", attributeNodes = @NamedAttributeNode("speciality"))
public class Level extends BaseEntity<Integer> {
    @Column(unique = true)
    private String name;
    private String description;
    private String studyPlan;

    @ManyToOne()
    @JoinColumn(name = "Speciality_id")
    private Speciality speciality;

    @OneToMany(mappedBy = "level", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.REMOVE)
    private Set<Semester> semesters;

    String getLevelSpecialityWithTrack() {
        return this.speciality.getName() + "-" + this.speciality.getTrack().getName();
    }
}
