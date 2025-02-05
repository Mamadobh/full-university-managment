package com.global.university.sessionNumber;

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
@RequestMapping("back-office/session-numbers")
@RequiredArgsConstructor
public class SessionNumberController {

    private final SessionNumberService sessionNumberService;

    @GetMapping("")
    public ResponseEntity<Response<PageResponse<SessionNumberResponse>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "99999") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<SessionNumberResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(sessionNumberService.findAll(page, size))
                        .build()
        );

    }

    @PostMapping("")
    public ResponseEntity<Response<Integer>> save(@Validated({Default.class}) @RequestBody SessionNumberRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(sessionNumberService.save(request))
                        .build()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<Response<Integer>> update(@Validated({OnUpdate.class, Default.class}) @RequestBody SessionNumberRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(sessionNumberService.update(request, request.id()))
                        .build()
        );
    }

    @GetMapping("/{session-number-id}")
    public ResponseEntity<Response<SessionNumberResponse>> findById(@PathVariable("session-number-id") Integer sessionNumberId) {
        return ResponseEntity.status(OK).body(
                Response.<SessionNumberResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(sessionNumberService.findById(sessionNumberId))
                        .build()
        );
    }
    @DeleteMapping("/{session-number-id}")
    public ResponseEntity<Response<Integer>> deleteById(@PathVariable("session-number-id") Integer sessionNumberId) {
        Integer deletedSessionNumberId = sessionNumberService.deleteById(sessionNumberId);
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(deletedSessionNumberId)
                        .build()
        );
    }
}
