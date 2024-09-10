package com.global.university.test;

import com.global.university.base.BaseRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepo extends BaseRepo<Test, Integer> {

    @Query("""
            SELECT COUNT(t)>0  FROM Test  t
            WHERE t.typeTest.id=:testTypeId
            AND t.testDuration.id=:testDurationId
            AND t.coefficient.id=:coefficientIdId
            AND t.id != :testId
                        """)
    boolean existTestRowWithTypeAndCoefficientAndDuratioon(@Param("testTypeId") Integer testTypeId,
                                                           @Param("testDurationId") Integer testDurationId,
                                                           @Param("coefficientIdId") Integer coefficientIdId,
                                                           @Param("testId") Integer testId

    );
}
