package com.global.university.sessionNumber;

import com.global.university.common.Mapper;
import org.springframework.stereotype.Service;

@Service
public class SessionNumberMapper implements Mapper<SessionNumber, Integer, SessionNumberRequest, SessionNumberResponse> {

    @Override
    public SessionNumber toEntity(SessionNumberRequest request, boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return SessionNumber.builder()
                .id(id)
                .sessionNumber(request.numberOfSession())
                .build();
    }


    @Override
    public SessionNumberResponse toResponse(SessionNumber entity) {
        return SessionNumberResponse.builder()
                .id(entity.getId())
                .numberOfSession(entity.getSessionNumber())
                .build();
    }


}
