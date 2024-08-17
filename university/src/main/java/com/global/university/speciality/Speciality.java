package com.global.university.speciality;

import com.global.university.base.BaseEntity;
import com.global.university.level.Level;
import com.global.university.track.Track;
import jakarta.persistence.*;
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
public class Speciality extends BaseEntity<Integer> {
    @Column(unique = true)
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "track_id")
    private Track track;


    @OneToMany(mappedBy = "speciality")
    private Set<Level> levels;
}
