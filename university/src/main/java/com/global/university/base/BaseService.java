package com.global.university.base;

import com.global.university.common.Mapper;
import com.global.university.department.DepartmentResponse;
import com.global.university.response.PageResponse;
import com.global.university.response.Response;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.MappedSuperclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@MappedSuperclass
public abstract class BaseService<T extends BaseEntity<ID>, ID, DtoRequest, DtoResponse> {
    @Autowired
    private BaseRepo<T, ID> baseRepo;
    @Autowired
    private Mapper<T, ID, DtoRequest, DtoResponse> mapper;


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

    public ID save(DtoRequest request) {
        T entity = mapper.toEntity(request);
        return baseRepo.save(entity).getId();
    }

    public DtoResponse findById(ID id) {
        T entity = baseRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(" Data not Found with id " + id + " Please verify !!"));
        return mapper.toResponse(entity);
    }

    public ID update(ID id) {
        T entity = baseRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(" Data not Found with id " + id + " Please verify !!"));
        return baseRepo.save(entity).getId();
    }

    public ID deleteById(ID id) {
        T entity = baseRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(" Data not Found with id " + id + " Please verify !!"));
        baseRepo.deleteById(id);
        return id;
    }
}
