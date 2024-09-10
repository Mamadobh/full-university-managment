package com.global.university.semester;

import com.global.university.semester.SemesterRequest;
import com.global.university.semester.SemesterResponse;
import com.global.university.semester.SemesterService;
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
@RequestMapping("semesters")
@RequiredArgsConstructor
public class SemesterController {

    private final SemesterService semesterService;

    @GetMapping("")
    public ResponseEntity<Response<PageResponse<SemesterResponse>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<SemesterResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(semesterService.findAll(page, size))
                        .build()
        );

    }

    @PostMapping("")
    public ResponseEntity<Response<Integer>> save(@Validated({Default.class}) @RequestBody SemesterRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(semesterService.save(request))
                        .build()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<Response<Integer>> update(@Validated({OnUpdate.class, Default.class}) @RequestBody SemesterRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(semesterService.update(request, request.id()))
                        .build()
        );
    }

    @GetMapping("/{semester-id}")
    public ResponseEntity<Response<SemesterResponse>> findById(@PathVariable("semester-id") Integer semesterId) {
        return ResponseEntity.status(OK).body(
                Response.<SemesterResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(semesterService.findById(semesterId))
                        .build()
        );
    }
    @DeleteMapping("/{semester-id}")
    public ResponseEntity<Response<Integer>> deleteById(@PathVariable("semester-id") Integer semesterId) {
        Integer deletedSemesterId = semesterService.deleteById(semesterId);
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(deletedSemesterId)
                        .build()
        );
    }
}
