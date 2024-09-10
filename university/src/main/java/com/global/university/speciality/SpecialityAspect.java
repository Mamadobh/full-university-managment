package com.global.university.speciality;

import com.global.university.track.TrackRequest;
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
public class SpecialityAspect {
    private final TrackService trackService;

    @Before("execution(* com.global.university.base.BaseService.save(..)) && args(request)")
    public void beforeSave(SpecialityRequest request) {
        trackService.exist(request.trackId());
    }


}
