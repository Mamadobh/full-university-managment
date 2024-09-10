package com.global.university.moduleType;

import com.global.university.base.BaseRepo;
import com.global.university.module.Module;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleTypeRepo extends BaseRepo<ModuleType, Integer> {


}
