package com.global.university.level;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LevelDetailsReponse {
    private Integer id;
    private String name;
    private String description;
    private String department;
    private String  specialityWithTrack;

}
