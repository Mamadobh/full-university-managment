package com.global.university.resource;

import com.global.university.base.BaseRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourcesRepo extends JpaRepository<Resource, Integer>, BaseRepo<Resource, Integer> {

}
