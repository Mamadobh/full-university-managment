package com.global.university.role;

import com.global.university.base.BaseRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer>, BaseRepo<Role,Integer> {

    public Optional<Role> findByName(String role);
}
