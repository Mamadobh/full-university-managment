package com.global.university.module;

import com.global.university.base.BaseRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepo extends BaseRepo<Module, Integer> {

    @Modifying
    @Query("DELETE FROM Module m WHERE m.id = :id")
    void deleteById(@Param("id") Long id);
}
