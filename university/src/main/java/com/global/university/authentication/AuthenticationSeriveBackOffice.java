package com.global.university.authentication;


import com.global.university.permission.Permission;
import com.global.university.person.Person;
import com.global.university.person.PersonRepo;
import com.global.university.person.PersonRequest;
import com.global.university.role.Role;
import com.global.university.role.RoleServices;
import com.global.university.security.JwtService;
import com.global.university.token.Token;
import com.global.university.token.TokenRepo;
import com.global.university.token.TokenType;
import com.global.university.user.User;
import com.global.university.user.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationSeriveBackOffice {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenRepo tokenRepo;
    private final RoleServices roleServices;
    private final UserRepo userRepo;
    private final PersonRepo personRepo;
    private final Mapper mapper;

    @Transactional
    public Integer register(AdminRegistraionRequest request) {

        request.roles().forEach(roleServices::exist);
        Person person;
        if (request.personId() != null) {
            if (!personRepo.existsById(request.personId())) {
                throw new EntityNotFoundException(" Data not Found with id " + request.personId() + " Please verify !!");
            }
            person = Person.builder()
                    .id(request.personId())
                    .build();
        } else {
            person = personRepo.save(mapper.toPerson(request.person()));
        }
        User user = mapper.toBackOfficeUser(request, person);
        return userRepo.save(user).getId();
    }

    public AdminAuthenticationResponse authenticate(AuthenticationRequest request) {


        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("fullName", user.getPerson().getfullName());
        var jwtToken = jwtService.generateToken(claims, user);
        revokedAllUserTokens(user);
        savedUserToken(user, jwtToken);
        Set<Role> roles = user.getRoles();

        return AdminAuthenticationResponse.builder()
                .token(jwtToken)
                .id(user.getId())
                .resources(rolesPermissionByResources(roles))
                .roles(roles.stream().map(Role::getName).collect(Collectors.toSet()))
                .build();
    }


    private Map<String, Set<String>> rolesPermissionByResources(Set<Role> roles) {
        Map<String, Set<String>> resourcePermissions = new HashMap<>();

        for (Role role : roles) {
            for (Permission permission : role.getPermissions()) {
                String resourceName = permission.getResource().getName();
                String permissionName = permission.getName();

                resourcePermissions
                        .computeIfAbsent(resourceName, k -> new HashSet<>())
                        .add(permissionName);
            }
        }

        return resourcePermissions;
    }

    private void savedUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepo.save(token);
    }

    private void revokedAllUserTokens(User user) {
        var validUserTokens = tokenRepo.findAllValidToken(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        tokenRepo.saveAll(validUserTokens);
    }

}
