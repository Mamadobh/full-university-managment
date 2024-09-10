package com.global.university.typeTest;


import com.global.university.base.BaseEntity;
import com.global.university.test.Test;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class TypeTest  extends BaseEntity<Integer> {
    private String typeTest;

    @OneToMany(mappedBy = "typeTest")
    private Set<Test> tests;


}
