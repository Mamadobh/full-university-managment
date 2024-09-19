package com.global.university.module;

import com.global.university.moduleType.ModuleTypeResponse;
import com.global.university.subject.SubjectResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleStudyPlanResponse {
    private Integer id;
    private String name;
    private String description;
    private ModuleTypeResponse moduleType;
    private List<SubjectResponse> subjects;
}
