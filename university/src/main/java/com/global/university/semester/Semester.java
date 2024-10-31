package com.global.university.semester;


import com.global.university.base.BaseEntity;
import com.global.university.level.Level;
import com.global.university.module.Module;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Semester extends BaseEntity<Integer> {

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne()
    @JoinColumn(name = "level_id")
    private Level level;

    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Module> modules = new HashSet<>();

    public static boolean checkDuplicationSemester(List<SemesterStudyPlanRequest> semesters) {
        Set<String> semestersName = new HashSet<>();
        for (SemesterStudyPlanRequest semester : semesters) {
            if (!semestersName.add(semester.name())) {
                return true;
            }
        }
        return false;
    }

}
