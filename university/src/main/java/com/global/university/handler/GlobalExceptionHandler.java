package com.global.university.handler;

import com.global.university.response.BusinessErrorCodes;
import com.global.university.response.ExceptionResponse;
import com.global.university.response.Response;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.global.university.response.BusinessErrorCodes.DATA_NOT_FOUND;
import static com.global.university.response.BusinessErrorCodes.INVALID_INPUT_DATA;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handelException(MethodArgumentNotValidException exp) {
        Map<String, String> errors = new HashMap<>();
        exp.getBindingResult().getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });

        return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(
                Response.builder()
                        .success(false)
                        .status(INVALID_INPUT_DATA.getHttpStatus().toString())
                        .error(ExceptionResponse.builder()
                                .businessErrorCode(INVALID_INPUT_DATA.getCode())
                                .businessExceptionDescription(INVALID_INPUT_DATA.getDescription())
                                .errors(errors)
                                .build())
                        .build()
        );
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<Response> handleConstraintViolationException(ConstraintViolationException exp) {
//        Map<String, String> errors = new HashMap<>();
//        exp.getConstraintViolations().forEach(violation -> {
//            String field = violation.getPropertyPath().toString(); // Obtient le chemin de la propriété (ex: startDate)
//            String message = violation.getMessage(); // Obtient le message d'erreur
//            errors.put(field, message);
//        });
//        log.error("handeled violationException");
//
//        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
//                Response.builder()
//                        .success(false)
//                        .status(INVALID_INPUT_DATA.getHttpStatus().toString())
//                        .error(ExceptionResponse.builder()
//                                .businessErrorCode(INVALID_INPUT_DATA.getCode())
//                                .businessExceptionDescription(INVALID_INPUT_DATA.getDescription())
//                                .errors(errors)
//                                .build())
//                        .build()
//        );
//    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Response> handelException(EntityNotFoundException exp) {

        return ResponseEntity.status(DATA_NOT_FOUND.getHttpStatus()).body(
                Response.builder()
                        .success(false)
                        .status(DATA_NOT_FOUND.getHttpStatus().toString())
                        .error(ExceptionResponse.builder()
                                .businessErrorCode(DATA_NOT_FOUND.getCode())
                                .businessExceptionDescription(DATA_NOT_FOUND.getDescription())
                                .error(exp.getMessage())
                                .build())
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handelException(Exception exp) {
        exp.printStackTrace();

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
                Response.builder()
                        .success(false)
                        .status(INTERNAL_SERVER_ERROR.toString())
                        .error(ExceptionResponse
                                .builder()
                                .businessErrorCode(BusinessErrorCodes.INTERNAL_SERVER_ERROR.getCode())
                                .businessExceptionDescription(BusinessErrorCodes.INTERNAL_SERVER_ERROR.getDescription())
                                .error(exp.getMessage())
                                .build()
                        )
                        .build()
        );
    }


}
