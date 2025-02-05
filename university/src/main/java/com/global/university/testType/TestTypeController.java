package com.global.university.testType;

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
@RequestMapping("back-office/test-types")
@RequiredArgsConstructor
public class TestTypeController {

    private final TestTypeService testTypeService;

    @GetMapping("")
    public ResponseEntity<Response<PageResponse<TestTypeResponse>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "99999") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<TestTypeResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(testTypeService.findAll(page, size))
                        .build()
        );

    }

    @PostMapping("")
    public ResponseEntity<Response<Integer>> save(@Validated({Default.class}) @RequestBody TestTypeRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(testTypeService.save(request))
                        .build()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<Response<Integer>> update(@Validated({OnUpdate.class, Default.class}) @RequestBody TestTypeRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(testTypeService.update(request, request.id()))
                        .build()
        );
    }

    @GetMapping("/{test-type-id}")
    public ResponseEntity<Response<TestTypeResponse>> findById(@PathVariable("test-type-id") Integer testTypeId) {
        return ResponseEntity.status(OK).body(
                Response.<TestTypeResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(testTypeService.findById(testTypeId))
                        .build()
        );
    }
    @DeleteMapping("/{testType-id}")
    public ResponseEntity<Response<Integer>> deleteById(@PathVariable("testType-id") Integer testTypeId) {
        Integer deletedTestTypeId = testTypeService.deleteById(testTypeId);
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(deletedTestTypeId)
                        .build()
        );
    }
}
