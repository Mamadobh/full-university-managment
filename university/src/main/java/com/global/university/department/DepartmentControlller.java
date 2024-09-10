package com.global.university.department;


import com.global.university.response.PageResponse;
import com.global.university.response.Response;
import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
            @RequestParam(name = "size", defaultValue = "99999") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<DepartmentResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(departmentService.findAll(page, size))
                        .build()
        );

    }

    @PostMapping("")
    public ResponseEntity<Response<Integer>> save(@RequestBody @Validated({Default.class}) DepartmentRequest request) {
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

    @PostMapping("/update")
    public ResponseEntity<Response<Integer>> update( @Validated({OnUpdate.class, Default.class}) @RequestBody DepartmentRequest request)  {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(departmentService.update(request,request.id()))
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
