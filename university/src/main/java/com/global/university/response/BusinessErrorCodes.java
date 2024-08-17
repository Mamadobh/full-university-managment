package com.global.university.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum BusinessErrorCodes {
    NO_CODE(0, NOT_IMPLEMENTED, "No code "),
    INCORRECT_CURRENT_PASSWORD(300, BAD_REQUEST, "current password is incorrect "),
    NEW_PASSWORD_DOES_NOT_MATCH(301, BAD_REQUEST, "the new password does not match"),
    ACCOUNT_LOCKED(302, FORBIDDEN, "User account is locked"),

    ACCOUNT_DISABLED(303, FORBIDDEN, "User account is disabled"),

    INVALID_INPUT_DATA(422, UNPROCESSABLE_ENTITY, "The entered data does not match the required rules"),
    DATA_NOT_FOUND(404, NOT_FOUND, "Data Not Found "),
    INTERNAL_SERVER_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error, please contact admin !");


    @Getter
    private final int code;
    @Getter
    private final String description;
    @Getter
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}