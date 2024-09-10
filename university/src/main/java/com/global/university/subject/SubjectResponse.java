package com.global.university.subject_type;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectTypeResponse {
    private Integer id;
    private String type;
    private Integer typeId;
    private Double numberOfSession;
    private Integer numberOfSessionId;
}
