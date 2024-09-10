package com.global.university.track;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TrackResponse {
    private Integer id;
    private String name;
    private String description;
    private Integer departmentId;
}

