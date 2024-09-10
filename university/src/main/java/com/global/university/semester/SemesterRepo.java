package com.global.university.semester;


import com.global.university.base.BaseRepo;
import com.global.university.level.Level;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SemesterRepo  extends JpaRepository<Semester,Integer>, BaseRepo<Semester,Integer> {

}
