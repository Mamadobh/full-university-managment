package com.global.university.testDuration;

import com.global.university.base.BaseEntity;
import com.global.university.test.Test;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class TestDuration extends BaseEntity<Integer> {
    @Column(unique = true)
    private Double testDuration;

    @OneToMany(mappedBy = "testDuration")
    Set<Test> tests;

}
