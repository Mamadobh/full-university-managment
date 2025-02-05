package com.global.university.subject_type;

import com.global.university.response.PageResponse;
import com.global.university.response.Response;
import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("back-office/subject-types")
@RequiredArgsConstructor
public class SubjectTypeController {

    private final SubjectTypeService subjectTypeService;

    @GetMapping("")
    public ResponseEntity<Response<PageResponse<SubjectTypeResponse>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "99999") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<SubjectTypeResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(subjectTypeService.findAll(page, size))
                        .build()
        );

    }

    @PostMapping("")
    public ResponseEntity<Response<Integer>> save(@Validated({Default.class}) @RequestBody SubjectTypeRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(subjectTypeService.save(request))
                        .build()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<Response<Integer>> update(@Validated({OnUpdate.class, Default.class}) @RequestBody SubjectTypeRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(subjectTypeService.update(request, request.id()))
                        .build()
        );
    }

    @GetMapping("/{subject-type-id}")
    public ResponseEntity<Response<SubjectTypeResponse>> findById(@PathVariable("subject-type-id") Integer subjectTypeId) {
        return ResponseEntity.status(OK).body(
                Response.<SubjectTypeResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(subjectTypeService.findById(subjectTypeId))
                        .build()
        );
    }
    @DeleteMapping("/{subject-type-id}")
    public ResponseEntity<Response<Integer>> deleteById(@PathVariable("subject-type-id") Integer subjectTypeId) {
        Integer deletedSubjectTypeId = subjectTypeService.deleteById(subjectTypeId);
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(deletedSubjectTypeId)
                        .build()
        );
    }
}
