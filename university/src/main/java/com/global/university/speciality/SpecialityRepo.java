package com.global.university.speciality;

import com.global.university.base.BaseRepo;
import com.global.university.level.Level;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialityRepo extends JpaRepository<Speciality, Integer>, BaseRepo<Speciality, Integer> {

}
