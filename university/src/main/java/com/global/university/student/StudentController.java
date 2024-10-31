package com.global.university.student;

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
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("")
    public ResponseEntity<Response<PageResponse<StudentResponse>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<StudentResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(studentService.findAll(page, size))
                        .build()
        );

    }

    @PostMapping("")
    public ResponseEntity<Response<Integer>> save(@Validated({Default.class}) @RequestBody StudentRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(studentService.save(request))
                        .build()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<Response<Integer>> update(@Validated({OnUpdate.class, Default.class}) @RequestBody StudentRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(studentService.update(request, request.id()))
                        .build()
        );
    }

    @GetMapping("/{student-id}")
    public ResponseEntity<Response<StudentResponse>> findById(@PathVariable("student-id") Integer studentId) {
        return ResponseEntity.status(OK).body(
                Response.<StudentResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(studentService.findById(studentId))
                        .build()
        );
    }
    @DeleteMapping("/{student-id}")
    public ResponseEntity<Response<Integer>> deleteById(@PathVariable("student-id") Integer studentId) {
        Integer deletedStudentId = studentService.deleteById(studentId);
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(deletedStudentId)
                        .build()
        );
    }
}
