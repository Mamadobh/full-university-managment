package com.global.university.user;

import com.global.university.base.BaseEntity;
import com.global.university.person.Person;
import com.global.university.role.Role;
import com.global.university.token.Token;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Log4j2
public class User extends BaseEntity<Integer> implements Principal, UserDetails {
    private String cin;
    private String passport_Number;
    private String photoPath;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean accountLocked;
    private boolean enabled;
    @OneToOne
    private Person person;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var rol = roles.stream()
                .flatMap(role -> {
                    Stream<SimpleGrantedAuthority> rolePermissions = role.getPermissions().stream()
                            .map(permission -> new SimpleGrantedAuthority(permission.getName()));
                    Stream<SimpleGrantedAuthority> roleName = Stream.of(new SimpleGrantedAuthority("ROLE_" + role.getName()));
                    return Stream.concat(rolePermissions, roleName);
                })
                .collect(Collectors.toSet());
        log.error("roles " + rol.toString());
        return rol;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getName() {
        return null;
    }


}
