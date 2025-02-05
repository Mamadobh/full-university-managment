package com.global.university.authentication;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Builder
public class AdminAuthenticationResponse {
    private Integer id;
    private String token;
    private Map<String, Set<String>> resources;
    private Set<String> roles;

}
