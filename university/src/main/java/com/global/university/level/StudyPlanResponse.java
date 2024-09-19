package com.global.university.level;

import com.global.university.semester.SemesterStudyPlanResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyPlanResponse {
    List<SemesterStudyPlanResponse> semesters;
}
