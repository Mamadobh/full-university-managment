package com.global.university.level;

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
@RequestMapping("levels")
@RequiredArgsConstructor
public class LevelController {

    private final LevelService levelService;
    private final StudyPlanService studyPlanService;

    @PostMapping("/study-plan")
    public ResponseEntity<Response<?>> test(@Validated({Default.class}) @RequestBody StudyPlanRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(studyPlanService.AddFullStudyPlan(request))
                        .build()
        );
    }

    @GetMapping("")
    public ResponseEntity<Response<PageResponse<LevelResponse>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<LevelResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(levelService.findAll(page, size))
                        .build()
        );


    }


    @GetMapping("/details")
    public ResponseEntity<Response<PageResponse<LevelDetailsReponse>>> findAllLevelWithDetails(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(required = false) String specialityLike,
            @RequestParam(required = false) String trackLike,
            @RequestParam(required = false) String departmentLike
    ) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<LevelDetailsReponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(levelService.findAllWithDetails(page, size, specialityLike, trackLike, departmentLike))
                        .build()
        );
    }

    @PostMapping("")
    public ResponseEntity<Response<Integer>> save(@Validated({Default.class}) @RequestBody LevelRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(levelService.save(request))
                        .build()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<Response<Integer>> update(@Validated({OnUpdate.class, Default.class}) @RequestBody LevelRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(levelService.update(request, request.id()))
                        .build()
        );
    }

    @GetMapping("/{level-id}")
    public ResponseEntity<Response<LevelResponse>> findById(@PathVariable("level-id") Integer levelId) {
        return ResponseEntity.status(OK).body(
                Response.<LevelResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(levelService.findById(levelId))
                        .build()
        );
    }

    @DeleteMapping("/{level-id}")
    public ResponseEntity<Response<Integer>> deleteById(@PathVariable("level-id") Integer levelId) {
        Integer deletedLevelId = levelService.deleteById(levelId);
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(deletedLevelId)
                        .build()
        );
    }
}
