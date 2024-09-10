package com.global.university.subject_type;

import com.global.university.common.Mapper;
import com.global.university.sessionNumber.SessionNumber;
import com.global.university.subject.Subject;
import com.global.university.typeSbj.Type;
import org.springframework.stereotype.Service;

@Service
public class SubjectTypeMapper implements Mapper<SubjectType, Integer, SubjectTypeRequest, SubjectTypeResponse> {

    @Override
    public SubjectType toEntity(SubjectTypeRequest request, boolean isUpdate) {
        Integer id = isUpdate ? request.id() : null;
        return SubjectType.builder()
                .id(id)
                .type(Type.builder()
                        .id(request.typeId())
                        .build())
                .sessionNumber(SessionNumber.builder()
                        .id(request.numberSessionId())
                        .build())
                .subject(Subject.builder()
                        .id(request.subjectId())
                        .build())
                .build();
    }


    @Override
    public SubjectTypeResponse toResponse(SubjectType entity) {
        return SubjectTypeResponse.builder()
                .id(entity.getId())
                .numberOfSessionId(
                        entity.getSessionNumber().getId()
                )
                .numberOfSession(
                        entity.getSessionNumber().getSessionNumber()
                )
                .typeId(
                        entity.getType().getId()
                )
                .type(
                        entity.getType().getSubjectType()
                )
                .build();
    }


}