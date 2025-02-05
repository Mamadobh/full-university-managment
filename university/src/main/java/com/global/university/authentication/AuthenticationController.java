package com.global.university.authentication;


import com.global.university.response.Response;
import com.global.university.validationGroup.Default;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final AuthenticationSeriveBackOffice authenticationSeriveBackOffice;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Response<?>> register(@Validated({Default.class}) @RequestBody  RegistrationRequest request) {
        return ResponseEntity.accepted().body(
                Response.builder()
                        .success(true)
                        .status(OK.toString())
                        .data(authenticationService.register(request))
                        .build()
        );
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Response<AuthenticationResponse>> authenticate(@Validated({Default.class})  @RequestBody AuthenticationRequest request) {
        return ResponseEntity.accepted().body(
                Response.<AuthenticationResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(authenticationService.authenticate(request))
                        .build()
        );
    }

    @PostMapping("/back-office/authenticate")
    public ResponseEntity<Response<AdminAuthenticationResponse>> backOfficeAuthenticate(@Validated({Default.class}) @RequestBody AuthenticationRequest request) {
        return ResponseEntity.accepted().body(
                Response.<AdminAuthenticationResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(authenticationSeriveBackOffice.authenticate(request))
                        .build()
        );
    }

    @PostMapping("/back-office/register")
    public ResponseEntity<Response<Integer>> backOfficeRegister(@Validated({Default.class}) @RequestBody AdminRegistraionRequest request) {
        return ResponseEntity.accepted().body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(authenticationSeriveBackOffice.register(request))
                        .build()
        );
    }
}
