package com.global.university.semester;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SemesterResponse {
    private  Integer id;

    private String name;
    private String description;

    private Integer levelId;

    private LocalDate startDate;
    private LocalDate endDate;
}
