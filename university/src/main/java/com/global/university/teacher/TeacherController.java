package com.global.university.teacher;

import com.global.university.teacher.TeacherRequest;
import com.global.university.teacher.TeacherResponse;
import com.global.university.teacher.TeacherService;
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
@RequestMapping("teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("")
    public ResponseEntity<Response<PageResponse<TeacherResponse>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<TeacherResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(teacherService.findAll(page, size))
                        .build()
        );

    }

    @PostMapping("")
    public ResponseEntity<Response<Integer>> save(@Validated({Default.class}) @RequestBody TeacherRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(teacherService.save(request))
                        .build()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<Response<Integer>> update(@Validated({OnUpdate.class, Default.class}) @RequestBody TeacherRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(teacherService.update(request, request.id()))
                        .build()
        );
    }

    @GetMapping("/{teacher-id}")
    public ResponseEntity<Response<TeacherResponse>> findById(@PathVariable("teacher-id") Integer teacherId) {
        return ResponseEntity.status(OK).body(
                Response.<TeacherResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(teacherService.findById(teacherId))
                        .build()
        );
    }
    @DeleteMapping("/{teacher-id}")
    public ResponseEntity<Response<Integer>> deleteById(@PathVariable("teacher-id") Integer teacherId) {
        Integer deletedTeacherId = teacherService.deleteById(teacherId);
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(deletedTeacherId)
                        .build()
        );
    }
}
