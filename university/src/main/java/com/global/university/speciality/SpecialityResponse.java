package com.global.university.speciality;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecialityResponse {
    private Integer id;
    private String name;
    private String description;

}
