package com.global.university.speciality;

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
@RequestMapping("back-office/specialities")
@RequiredArgsConstructor
public class SpecialityController {

    private final SpecialityService specialityService;

    @GetMapping("")
    public ResponseEntity<Response<PageResponse<SpecialityResponse>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "99999") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<SpecialityResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(specialityService.findAll(page, size))
                        .build()
        );

    }

    @PostMapping("")
    public ResponseEntity<Response<Integer>> save(@Validated({Default.class}) @RequestBody SpecialityRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(specialityService.save(request))
                        .build()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<Response<Integer>> update(@Validated({OnUpdate.class, Default.class}) @RequestBody SpecialityRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(specialityService.update(request, request.id()))
                        .build()
        );
    }

    @GetMapping("/{speciality-id}")
    public ResponseEntity<Response<SpecialityResponse>> findById(@PathVariable("speciality-id") Integer specialityId) {
        return ResponseEntity.status(OK).body(
                Response.<SpecialityResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(specialityService.findById(specialityId))
                        .build()
        );
    }

    @DeleteMapping("/{speciality-id}")
    public ResponseEntity<Response<Integer>> deleteById(@PathVariable("speciality-id") Integer specialityId) {
        Integer deletedSpecialityId = specialityService.deleteById(specialityId);
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(deletedSpecialityId)
                        .build()
        );
    }
}
