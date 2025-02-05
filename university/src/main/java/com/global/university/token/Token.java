package com.global.university.token;


import com.global.university.base.BaseEntity;
import com.global.university.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Token extends BaseEntity<Integer> {
    @Column(columnDefinition = "TEXT")
    private String token;
    private boolean revoked;
    private boolean expired;
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
