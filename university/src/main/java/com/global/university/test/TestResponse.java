package com.global.university.test;

import com.global.university.coefficient.Coefficient;
import com.global.university.coefficient.CoefficientResponse;
import com.global.university.testDuration.TestDurationResponse;
import com.global.university.testType.TestTypeResponse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestResponse {
    private Integer id;

    private TestTypeResponse testType;

    private CoefficientResponse coefficient;
    private TestDurationResponse testDuration;

}
