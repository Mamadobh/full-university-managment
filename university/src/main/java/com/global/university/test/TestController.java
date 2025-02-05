package com.global.university.test;

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
@RequestMapping("back-office/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("")
    public ResponseEntity<Response<PageResponse<TestResponse>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "99999") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<TestResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(testService.findAll(page, size))
                        .build()
        );

    }

    @PostMapping("")
    public ResponseEntity<Response<Integer>> save(@Validated({Default.class}) @RequestBody TestRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(testService.save(request))
                        .build()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<Response<Integer>> update(@Validated({OnUpdate.class, Default.class}) @RequestBody TestRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(testService.update(request, request.id()))
                        .build()
        );
    }

    @GetMapping("/{test-id}")
    public ResponseEntity<Response<TestResponse>> findById(@PathVariable("test-id") Integer testId) {
        return ResponseEntity.status(OK).body(
                Response.<TestResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(testService.findById(testId))
                        .build()
        );
    }
    @DeleteMapping("/{test-id}")
    public ResponseEntity<Response<Integer>> deleteById(@PathVariable("test-id") Integer testId) {
        Integer deletedTestId = testService.deleteById(testId);
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(deletedTestId)
                        .build()
        );
    }
}
