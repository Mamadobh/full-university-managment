package com.global.university.speciality;

import com.global.university.base.BaseRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityRepo extends JpaRepository<Speciality, Integer>, BaseRepo<Speciality, Integer> {
}
