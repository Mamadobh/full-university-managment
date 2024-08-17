package com.global.university.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Response<T> {
    private boolean success;
    private String status;
    private T data;
    private  ExceptionResponse error;
}
