package com.global.university.teaching;

import com.global.university.base.BaseEntity;
import com.global.university.subject_type.SubjectType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Teaching extends BaseEntity<Integer> {


    @ManyToOne
    @JoinColumn(name = "subject_type_id")
    private SubjectType subjectType;
}
