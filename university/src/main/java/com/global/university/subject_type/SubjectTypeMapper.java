package com.global.university.subject_type;

import com.global.university.common.Mapper;
import com.global.university.sessionNumber.SessionNumber;
import com.global.university.sessionNumber.SessionNumberRepo;
import com.global.university.sessionNumber.SessionNumberResponse;
import com.global.university.subject.Subject;
import com.global.university.typeSbj.Type;
import com.global.university.typeSbj.TypeRepo;
import com.global.university.typeSbj.TypeResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectTypeMapper implements Mapper<SubjectType, Integer, SubjectTypeRequest, SubjectTypeResponse> {

    private final TypeRepo typeRepo;
    private final SessionNumberRepo sessionNumberRepo;

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
                .numberOfSession(
                        SessionNumberResponse.builder()
                                .id(entity.getSessionNumber().getId())
                                .numberOfSession(entity.getSessionNumber().getSessionNumber())
                                .build()
                )


                .type(
                        TypeResponse.builder()
                                .id(entity.getType().getId())
                                .subjectType(entity.getType().getSubjectType())
                                .build()
                )
                .build();
    }

    public SubjectType toEntity(SubjectTypeStudyPlanRequest request, Subject subject) {
        return SubjectType.builder()
                .type(
                        typeRepo.findById(request.typeId()).orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Data not Found with id " +
                                                request.typeId() +
                                                " Please verify !!"))
                )
                .sessionNumber(
                        sessionNumberRepo.findById(request.numberSessionId()).orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Data not Found with id " +
                                                request.numberSessionId() +
                                                " Please verify !!"))
                )
                .subject(subject)
                .build();
    }


}
