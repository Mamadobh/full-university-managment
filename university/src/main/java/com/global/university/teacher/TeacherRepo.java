package com.global.university.teacher;

import com.global.university.base.BaseRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Integer>, BaseRepo<Teacher, Integer> {
    @Query("""
                SELECT t FROM Teacher t
                WHERE  t.email = :email
                AND t.cin=:cin
            """)
    Optional<Teacher> findByEmailAndCin(
            @Param("email") String email,
            @Param("cin") String cin

    );

    @Query("""
                SELECT t FROM Teacher t
                WHERE  t.email = :email
                AND t.passport_number= :passport_number
            """)
    Optional<Teacher> findByEmailAndNumPassport(
            @Param("email") String email,
            @Param("passport_number") String passport_number
    );

    @Query("""
            SELECT COUNT(t)>0 FROM Teacher t
            where t.num_tel= :num_tel
            """)
    boolean checkExistByNumTel(@Param("num_tel") String numTel);

    @Query("""
            SELECT COUNT(t)>0 FROM Teacher t
            where t.id != :id AND
            (t.num_tel= :num_tel
            OR t.email= :email
            OR t.cin= :cin
            OR t.passport_number = :passport_number)
                        
            """)
    boolean checkExist(@Param("id") Integer id,
                       @Param("num_tel") String numTel,
                       @Param("email") String email,
                       @Param("cin") String cin,
                       @Param("passport_number") String passport_number
    );


}
