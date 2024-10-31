package com.global.university.level;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
    public class LevelResponse {
        private Integer id;
        private String name;
        private String description;
        private Integer specialityId;
        private byte[] studyPlan;
    }
