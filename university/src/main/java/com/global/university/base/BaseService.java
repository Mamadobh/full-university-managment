package com.global.university.base;

import com.global.university.common.Mapper;
import com.global.university.response.PageResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.MappedSuperclass;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@MappedSuperclass
@Log4j2
public abstract class BaseService<T extends BaseEntity<ID>, ID, DtoRequest, DtoResponse> {
    @Autowired
    private BaseRepo<T, ID> baseRepo;
    @Autowired
    private Mapper<T, ID, DtoRequest, DtoResponse> mapper;

    public ID save(DtoRequest request) {

        T entity = mapper.toEntity(request, false);
        log.error("  before repo ");

        return baseRepo.save(entity).getId();
    }

    public ID update(DtoRequest request, ID id) {
        exist(id);
        return baseRepo.save(mapper.toEntity(request, true)).getId();
    }

    public PageResponse<DtoResponse> findAll(int pageNumber, int size) {
        Pageable page = PageRequest.of(pageNumber, size, Sort.by("createdDate").descending());
        Page<T> entities = baseRepo.findAll(page);
        List<DtoResponse> entitiesReponse = entities.stream().map(mapper::toResponse).toList();
        PageResponse<DtoResponse> pageEntitiesResponse = new PageResponse<DtoResponse>(
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


    public DtoResponse findById(ID id) {
        T entity = baseRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(" Data not Found with id " + id + " Please verify !!"));
        return mapper.toResponse(entity);
    }

    @Transactional
    public ID deleteById(ID id) {
        exist(id);
        baseRepo.deleteById(id);
        return id;
    }

    public void exist(ID id) {
        if (!baseRepo.existsById(id)) {
            throw new EntityNotFoundException(" Data not Found with id " + id + " Please verify !!");
        }
    }
}
