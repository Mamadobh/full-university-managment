package com.global.university.student;


import com.global.university.base.BaseRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> , BaseRepo<Student, Integer> {
    @Query("""
                SELECT st FROM Student st
                WHERE  st.email = :email
                AND st.cin=:cin
            """)
    Optional<Student> findByEmailAndCin(
            @Param("email") String email,
            @Param("cin") String cin

    );

    @Query("""
                SELECT st FROM Student st
                WHERE  st.email = :email
                AND st.passport_number= :passport_number
            """)
    Optional<Student> findByEmailAndNumPassport(
            @Param("email") String email,
            @Param("passport_number") String passport_number
    );



    @Query("""
            SELECT COUNT(st)>0 FROM Student st
            where st.id != :id AND
            (st.num_tel= :num_tel
            OR st.email= :email
            OR st.cin= :cin
            OR st.passport_number = :passport_number)
            """)
    boolean checkExist(@Param("id") Integer id,
                       @Param("num_tel") String numTel,
                       @Param("email") String email,
                       @Param("cin") String cin,
                       @Param("passport_number") String passport_number
    );


}
