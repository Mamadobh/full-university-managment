package com.global.university.testDuration;

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
@RequestMapping("test-durations")
@RequiredArgsConstructor
public class TestDurationController {

    private final TestDurationService testDurationService;

    @GetMapping("")
    public ResponseEntity<Response<PageResponse<TestDurationResponse>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "99999") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<TestDurationResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(testDurationService.findAll(page, size))
                        .build()
        );

    }

    @PostMapping("")
    public ResponseEntity<Response<Integer>> save(@Validated({Default.class}) @RequestBody TestDurationRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(testDurationService.save(request))
                        .build()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<Response<Integer>> update(@Validated({OnUpdate.class, Default.class}) @RequestBody TestDurationRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(testDurationService.update(request, request.id()))
                        .build()
        );
    }

    @GetMapping("/{test-duration-id}")
    public ResponseEntity<Response<TestDurationResponse>> findById(@PathVariable("test-duration-id") Integer testDurationId) {
        return ResponseEntity.status(OK).body(
                Response.<TestDurationResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(testDurationService.findById(testDurationId))
                        .build()
        );
    }
    @DeleteMapping("/{test-duration-id}")
    public ResponseEntity<Response<Integer>> deleteById(@PathVariable("test-duration-id") Integer testDurationId) {
        Integer deletedTestDurationId = testDurationService.deleteById(testDurationId);
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(deletedTestDurationId)
                        .build()
        );
    }
}
