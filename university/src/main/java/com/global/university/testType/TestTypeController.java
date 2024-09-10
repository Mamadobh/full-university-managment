package com.global.university.coefficient;

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
@RequestMapping("coefficients")
@RequiredArgsConstructor
public class CoefficientController {

    private final CoeffcientService coefficientService;

    @GetMapping("")
    public ResponseEntity<Response<PageResponse<CoefficientResponse>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "99999") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<CoefficientResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(coefficientService.findAll(page, size))
                        .build()
        );

    }

    @PostMapping("")
    public ResponseEntity<Response<Integer>> save(@Validated({Default.class}) @RequestBody CoefficientRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(coefficientService.save(request))
                        .build()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<Response<Integer>> update(@Validated({OnUpdate.class, Default.class}) @RequestBody CoefficientRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(coefficientService.update(request, request.id()))
                        .build()
        );
    }

    @GetMapping("/{coefficient-id}")
    public ResponseEntity<Response<CoefficientResponse>> findById(@PathVariable("coefficient-id") Integer coefficientId) {
        return ResponseEntity.status(OK).body(
                Response.<CoefficientResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(coefficientService.findById(coefficientId))
                        .build()
        );
    }
    @DeleteMapping("/{coefficient-id}")
    public ResponseEntity<Response<Integer>> deleteById(@PathVariable("coefficient-id") Integer coefficientId) {
        Integer deletedCoefficientId = coefficientService.deleteById(coefficientId);
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(deletedCoefficientId)
                        .build()
        );
    }
}
