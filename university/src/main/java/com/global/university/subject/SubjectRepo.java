package com.global.university.subject;

import com.global.university.base.BaseRepo;
import com.global.university.module.Module;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepo extends BaseRepo<Subject, Integer> {


}
