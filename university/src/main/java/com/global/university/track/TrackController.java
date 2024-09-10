package com.global.university.track;

import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import com.global.university.response.PageResponse;
import com.global.university.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("tracks")
@RequiredArgsConstructor
public class TrackController {
    private final TrackService trackService;

    @GetMapping("")
    public ResponseEntity<Response<PageResponse<TrackResponse>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "999999") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<TrackResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(trackService.findAll(page, size))
                        .build()
        );

    }
@PostMapping("")
public ResponseEntity<Response<Integer>> save(@RequestBody @Validated({ Default.class}) TrackRequest request) {
    return ResponseEntity.status(OK).body(
            Response.<Integer>builder()
                    .success(true)
                    .status(OK.toString())
                    .data(trackService.save(request))
                    .build()
    );
}
    @PostMapping("/update")
    public ResponseEntity<Response<Integer>> update(  @Validated({OnUpdate.class, Default.class}) @RequestBody TrackRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(trackService.update(request, request.id()))
                        .build()
        );
    }

    @GetMapping("/{track-id}")
    public ResponseEntity<Response<TrackResponse>> findById(@PathVariable("track-id") Integer trackId) {
        return ResponseEntity.status(OK).body(
                Response.<TrackResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(trackService.findById(trackId))
                        .build()
        );
    }
    @DeleteMapping("/{track-id}")
    public ResponseEntity<Response<Integer>> deleteById(@PathVariable("track-id") Integer trackId) {
        Integer deletedTrackId = trackService.deleteById(trackId);
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(deletedTrackId)
                        .build()
        );
    }
}
