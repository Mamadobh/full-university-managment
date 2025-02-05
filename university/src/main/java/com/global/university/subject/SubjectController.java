package com.global.university.subject;

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
@RequestMapping("back-office/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService  subjectService;

    @GetMapping("")
    public ResponseEntity<Response<PageResponse<SubjectResponse>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "99999") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<SubjectResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(subjectService.findAll(page, size))
                        .build()
        );

    }

    @PostMapping("")
    public ResponseEntity<Response<Integer>> save(@Validated({Default.class}) @RequestBody SubjectRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(subjectService.save(request))
                        .build()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<Response<Integer>> update(@Validated({OnUpdate.class, Default.class}) @RequestBody SubjectRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(subjectService.update(request, request.id()))
                        .build()
        );
    }

    @GetMapping("/{subject-id}")
    public ResponseEntity<Response<SubjectResponse>> findById(@PathVariable("subject-id") Integer subjectId) {
        return ResponseEntity.status(OK).body(
                Response.<SubjectResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(subjectService.findById(subjectId))
                        .build()
        );
    }
    @DeleteMapping("/{subject-id}")
    public ResponseEntity<Response<Integer>> deleteById(@PathVariable("subject-id") Integer subjectId) {
        Integer deletedSubjectId = subjectService.deleteById(subjectId);
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(deletedSubjectId)
                        .build()
        );
    }
}
