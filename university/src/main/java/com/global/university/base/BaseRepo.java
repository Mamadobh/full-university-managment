package com.global.university.base;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.awt.print.Book;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepo <T,ID> extends JpaRepository <T,ID>{

    @Modifying
    @Query("DELETE FROM #{#entityName}  entity WHERE entity.id = :id")
    void deleteById(@Param("id") ID id);
}
