package com.global.university.typeSbj;

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
@RequestMapping("back-office/uitypes")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;

    @GetMapping("")
    public ResponseEntity<Response<PageResponse<TypeResponse>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "99999") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<TypeResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(typeService.findAll(page, size))
                        .build()
        );

    }

    @PostMapping("")
    public ResponseEntity<Response<Integer>> save(@Validated({Default.class}) @RequestBody TypeRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(typeService.save(request))
                        .build()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<Response<Integer>> update(@Validated({OnUpdate.class, Default.class}) @RequestBody TypeRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(typeService.update(request, request.id()))
                        .build()
        );
    }

    @GetMapping("/{type-id}")
    public ResponseEntity<Response<TypeResponse>> findById(@PathVariable("type-id") Integer testTypeId) {
        return ResponseEntity.status(OK).body(
                Response.<TypeResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(typeService.findById(testTypeId))
                        .build()
        );
    }
    @DeleteMapping("/{type-id}")
    public ResponseEntity<Response<Integer>> deleteById(@PathVariable("type-id") Integer testTypeId) {
        Integer deletedTypeId = typeService.deleteById(testTypeId);
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(deletedTypeId)
                        .build()
        );
    }
}
