package com.global.university.role;

import com.global.university.base.BaseRepo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer>, BaseRepo<Role,Integer> {
   @Override
   @EntityGraph(value = "role-permissions-graph")
    Optional<Role> findById(Integer id);

    @EntityGraph(value = "role-permissions-graph")
    Optional<Role> findByName(String role);
    @Override
    @EntityGraph(value = "role-permissions-graph")
    List<Role> findAll();
}
