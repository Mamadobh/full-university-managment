package com.global.university.typeSbj;

import com.global.university.base.BaseEntity;
import com.global.university.subject_type.SubjectType;
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
public class Type extends BaseEntity<Integer> {
    @Column(unique = true)
    private String subjectType;

    @OneToMany(mappedBy = "type")
    private Set<SubjectType> subjectTypes;
}
