package com.global.university.semester;


import com.global.university.base.BaseRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepo extends JpaRepository<Semester, Integer>, BaseRepo<Semester, Integer> {

    @Query("""
                      SELECT COUNT(s)>0 FROM Semester s
                      WHERE LOWER(TRIM(s.name)) = LOWER(TRIM(:name))
                      AND s.id != :id
                      AND s.level.id = :levelId
                      
                      """)
    boolean checkIfSemesterNameAlreadyExist(
            @Param("name") String name,
            @Param("id") Integer id,
            @Param("levelId") Integer levelId
    );
}
