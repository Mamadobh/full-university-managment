package com.global.university.department;

import com.global.university.base.BaseEntity;
import com.global.university.track.Track;
import jakarta.persistence.Column;
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
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department extends BaseEntity<Integer> {

    @Column(unique = true)
    private String name;
    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "department")
    private Set<Track> tracks;
}
