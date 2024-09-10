package com.global.university.semester;

import com.global.university.base.BaseService;
import com.global.university.exception.OperationNotPermittedException;
import com.global.university.level.Level;
import com.global.university.level.LevelRepo;
import com.global.university.level.LevelResponse;
import com.global.university.level.LevelService;
import com.global.university.track.TrackService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SemesterService extends BaseService<Semester, Integer, SemesterRequest, SemesterResponse> {
    @Autowired
    private LevelService levelService;
    @Autowired
    SemesterRepo semesterRepo;
    @Autowired
    private SemesterMapper mapper;
    @Autowired
    private LevelRepo levelRepo;

    @Override
    public Integer save(SemesterRequest request) {
        Level level = levelRepo.findById(request.levelId()).orElseThrow(() -> new EntityNotFoundException(" Data not Found with id " + request.levelId() + " Please verify !!"));
        if (level.getSemesters().size() >= 2) {
            throw new OperationNotPermittedException("Each Level can not have more than 2 semester");
        }
        return super.save(request);
    }

    @Override
    public Integer update(SemesterRequest request, Integer id) {
        exist(id);
        levelService.exist(request.levelId());
        return semesterRepo.save(mapper.toEntity(request, true)).getId();
    }


}