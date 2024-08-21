package com.global.university.level;

import com.global.university.base.BaseRepo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.Optional;

@Repository
public interface LevelRepo extends BaseRepo<Level, Integer> {

    @Query("""
            SELECT level FRom Level  level
            WHERE level.id =:levelId
            """)
    Optional<Level> findByIdWithoutJoin(@Param("levelId") Integer levelId);
}
