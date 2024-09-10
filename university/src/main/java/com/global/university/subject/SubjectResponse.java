package com.global.university.subject;

import com.global.university.test.Test;
import com.global.university.test.TestResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectResponse {
    private Integer id;
    private String name;
    private Integer ModuleId;
    private Double coefficient;
    private List<TestResponse> tests;
}
