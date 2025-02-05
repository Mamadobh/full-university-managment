package com.global.university.resource;

import com.global.university.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourcesService extends BaseService<Resource, Integer, ResourcesRequest, ResourcesResponse> {
    private final ResourcesRepo resourcesRepo;
    private final ResourcesMapper resourcesMapper;

    @Transactional
    @Override
    public Integer save(ResourcesRequest resourcesRequest) {
        return super.save(resourcesRequest);
    }


    @Transactional
    public Integer saveAll(List<ResourcesRequest> request) {
        List<Resource> resources = request.stream().map((res) -> resourcesMapper.toEntity(res, false)).toList();
        resourcesRepo.saveAll(resources);
        return 1;
    }

}
