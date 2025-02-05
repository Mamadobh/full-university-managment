package com.global.university.permission;

import com.global.university.base.BaseRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepo extends JpaRepository<Permission, Integer>, BaseRepo<Permission, Integer> , JpaSpecificationExecutor<Permission> {



}
