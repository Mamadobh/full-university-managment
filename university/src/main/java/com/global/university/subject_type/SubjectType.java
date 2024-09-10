package com.global.university.subject_type;

import com.global.university.base.BaseEntity;
import com.global.university.sessionNumber.SessionNumber;
import com.global.university.subject.Subject;
import com.global.university.teaching.Teaching;
import com.global.university.typeSbj.Type;
import jakarta.persistence.*;
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
@Table(name = "subject_type",
        uniqueConstraints = @UniqueConstraint(columnNames = {"subject_id", "type_id", "session_number_id"})
)
public class SubjectType extends BaseEntity<Integer> {
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "session_number_id")
    private SessionNumber sessionNumber;

    @OneToMany(mappedBy = "subjectType")
    private Set<Teaching> teachings;

}
