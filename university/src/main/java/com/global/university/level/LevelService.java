package com.global.university.level;

import com.global.university.base.BaseService;
import com.global.university.exception.OperationNotPermittedException;
import com.global.university.response.PageResponse;
import com.global.university.speciality.SpecialityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.global.university.level.LevelSpecification.*;

@Service
@Slf4j
public class LevelService extends BaseService<Level, Integer, LevelRequest, LevelResponse> {
    @Autowired
    private SpecialityService specialityService;
    @Autowired
    LevelRepo levelRepo;
    @Autowired
    private LevelMapper mapper;


    @Override
    public Integer update(LevelRequest request, Integer id) {
        exist(id);
        specialityService.exist(request.specialityId());
        return levelRepo.save(mapper.toEntity(request, true)).getId();
    }

    public ResponseEntity<byte[]> findStudyPlanPdf(Integer levelId) {
        LevelResponse level = this.findById(levelId);
        if (level.getStudyPlan() == null) {
            throw new OperationNotPermittedException("can not fetch not exist study plan for level with id " + levelId);
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + level.getName() + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(
                        level.getStudyPlan()
                );
    }

    public PageResponse<LevelDetailsReponse> findAllWithDetails(int pageNumber,
                                                                int size,
                                                                String specialityLikeFilter,
                                                                String trackLikeFilter,
                                                                String departmentLikeFilter

    ) {
        log.error("StringUtils.isBlank(specialityLikeFilter)  " + StringUtils.isBlank(specialityLikeFilter));
        log.error("specialityLikeFilter " + specialityLikeFilter);
        log.error("departmentLikeFilter " + departmentLikeFilter);
        log.error("trackLikeFilter " + trackLikeFilter);
        Specification<Level> filterLevels = Specification
                .where(StringUtils.isBlank(specialityLikeFilter) ? null : specialityLike(specialityLikeFilter))
                .and(StringUtils.isBlank(trackLikeFilter) ? null : trackLike(trackLikeFilter))
                .and(StringUtils.isBlank(departmentLikeFilter) ? null : departmentLike(departmentLikeFilter));


        Pageable page = PageRequest.of(pageNumber, size, Sort.by("createdDate").descending());
        Page<Level> entities = levelRepo.findAll(filterLevels, page);
        List<LevelDetailsReponse> entitiesReponse = entities.stream().map(mapper::toResponseDetails).toList();
        PageResponse<LevelDetailsReponse> pageEntitiesResponse = new PageResponse<LevelDetailsReponse>(
                entitiesReponse,
                entities.getNumber(),
                entities.getSize(),
                entities.getTotalElements(),
                entities.getTotalPages(),
                entities.isFirst(),
                entities.isLast(),
                0,
                0

        );
        pageEntitiesResponse.record(pageNumber, size);
        return pageEntitiesResponse;

    }
}