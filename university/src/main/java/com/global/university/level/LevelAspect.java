package com.global.university.level;

import com.global.university.level.LevelRequest;
import com.global.university.speciality.SpecialityService;
import com.global.university.track.TrackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class LevelAspect {
    private final SpecialityService specialityService;

    @Before("execution(* com.global.university.base.BaseService.save(..)) && args(request)")
    public void beforeSave(LevelRequest request) {
        specialityService.exist(request.i());
    }


}
