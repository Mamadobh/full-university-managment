package com.global.university.semester;

import com.global.university.level.LevelDetailsReponse;
import com.global.university.module.ModuleStudyPlanResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SemesterStudyPlanResponse {
    private Integer id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LevelDetailsReponse level;
    private List<ModuleStudyPlanResponse> modules;


}
