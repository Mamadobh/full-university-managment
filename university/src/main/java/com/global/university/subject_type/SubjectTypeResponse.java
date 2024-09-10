package com.global.university.subject_type;

import com.global.university.sessionNumber.SessionNumberResponse;
import com.global.university.typeSbj.TypeResponse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectTypeResponse {
    private Integer id;
    private TypeResponse type;
    private SessionNumberResponse numberOfSession;
;
}
