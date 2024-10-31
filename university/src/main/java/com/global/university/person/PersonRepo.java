package com.global.university.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
    @Query("""
            SELECT COUNT(p)>0 FROM Person p
            where p.id != :id AND
            (p.num_tel= :num_tel
            OR p.email= :email
            OR p.cin= :cin
            OR p.passport_number = :passport_number)
            """)
    boolean checkExist(@Param("id") Integer id,
                       @Param("num_tel") String numTel,
                       @Param("email") String email,
                       @Param("cin") String cin,
                       @Param("passport_number") String passport_number
    );

    @Query("""
            SELECT COUNT(p)>0 FROM Person p
             LEFT JOIN Student s ON p.id=s.id
            where p.id != :id AND
            (p.num_tel= :num_tel
            OR p.email= :email
            OR p.cin= :cin
            OR p.passport_number = :passport_number
            Or s.registered_number =:registered_number
            )
                      
            """)
    boolean checkExistStudent(@Param("id") Integer id,
                              @Param("num_tel") String numTel,
                              @Param("email") String email,
                              @Param("cin") String cin,
                              @Param("passport_number") String passport_number,
                              @Param("registered_number") String registered_number
    );

}
