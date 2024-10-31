package com.global.university.authentication;


import com.global.university.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Response<?>> register(@RequestBody @Valid RegistrationRequest request) {
        return ResponseEntity.accepted().body(
                Response.builder()
                        .success(true)
                        .status(OK.toString())
                        .data(authenticationService.register(request))
                        .build()
        );
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Response<AuthenticationResponse>> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity.accepted().body(
                Response.<AuthenticationResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(authenticationService.authenticate(request))
                        .build()
        );
    }
}
