package com.global.university.module;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleResponse {
    private Integer id;
    private String name;
    private String description;
    private Integer semesterId;
    private Integer moduleTypeId;

}
