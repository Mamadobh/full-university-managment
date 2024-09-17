package com.global.university.test;

import com.global.university.base.BaseRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

    @Query("""
            SELECT test FROM Test  test
            WHERE test.testDuration.id=:testDurationId
            AND test.coefficient.id =:coefficientID
            And test.typeTest.id = :testTypeId
            """)
    Optional<Test> findByDurationAndCoficientAndType(
            @Param("testDurationId") Integer testDurationId,
            @Param("coefficientID") Integer coefficientID,
            @Param("testTypeId") Integer testTypeId
    );
}
