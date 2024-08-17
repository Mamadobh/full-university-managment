package com.global.university.department;


import com.global.university.response.PageResponse;
import com.global.university.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("departments")
@RequiredArgsConstructor
public class DepartmentControlller {

    private final DepartmentService departmentService;

    @GetMapping("")
    public ResponseEntity<Response<PageResponse<DepartmentResponse>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<DepartmentResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(departmentService.findAll(page, size))
                        .build()
        );

    }

    @PostMapping("")
    public ResponseEntity<Response<Integer>> save(@RequestBody @Valid DepartmentRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(departmentService.save(request))
                        .build()
        );
    }

    @GetMapping("/{department-id}")
    public ResponseEntity<Response<DepartmentResponse>> findById(@PathVariable("department-id") Integer departmentId) {
        return ResponseEntity.status(OK).body(
                Response.<DepartmentResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(departmentService.findById(departmentId))
                        .build()
        );
    }

    @PostMapping("/{department-id}")
    public ResponseEntity<Response<Integer>> update(@PathVariable("department-id") Integer departmentId) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(departmentService.update(departmentId))
                        .build()
        );
    }

    @DeleteMapping("/{department-id}")
    public ResponseEntity<Response<Integer>> deleteById(@PathVariable("department-id") Integer departmentId) {
        Integer deletedDepartmentId = departmentService.deleteById(departmentId);
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(deletedDepartmentId)
                        .build()
        );
    }

}
