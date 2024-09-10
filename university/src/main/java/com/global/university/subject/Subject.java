package com.global.university.matiere;

import com.global.university.base.BaseEntity;
import com.global.university.module.Module;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Matiere extends BaseEntity<Integer> {
    private String name;
    private Double coefficient;
    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;


}
