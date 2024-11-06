package com.global.university.permission;

import com.global.university.base.BaseRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.security.Permissions;

@Repository
public interface PermissionRepo extends JpaRepository<Permission, Integer>, BaseRepo<Permission, Integer> {

}
