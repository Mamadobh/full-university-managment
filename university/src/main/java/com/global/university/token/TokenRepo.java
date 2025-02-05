package com.global.university.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepo extends JpaRepository<Token, Integer> {


    @Query("""
            SELECT t From Token  t
            INNER join User u  ON  u.id=t.user.id
            WHERE t.user.id =:userId
            AND (t.revoked=FALSE OR t.expired=FALSE)
             """)
    List<Token> findAllValidToken(@Param("userId") Integer userId);

    Optional<Token> findByToken( String token);
}
