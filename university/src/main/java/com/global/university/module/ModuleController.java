package com.global.university.module;

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
@RequestMapping("back-office/modules")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    @GetMapping("")
    public ResponseEntity<Response<PageResponse<ModuleResponse>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<ModuleResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(moduleService.findAll(page, size))
                        .build()
        );

    }

    @PostMapping("")
    public ResponseEntity<Response<Integer>> save(@Validated({Default.class}) @RequestBody ModuleRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(moduleService.save(request))
                        .build()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<Response<Integer>> update(@Validated({OnUpdate.class, Default.class}) @RequestBody ModuleRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(moduleService.update(request, request.id()))
                        .build()
        );
    }

    @GetMapping("/{module-id}")
    public ResponseEntity<Response<ModuleResponse>> findById(@PathVariable("module-id") Integer moduleId) {
        return ResponseEntity.status(OK).body(
                Response.<ModuleResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(moduleService.findById(moduleId))
                        .build()
        );
    }
    @DeleteMapping("/{module-id}")
    public ResponseEntity<Response<Integer>> deleteById(@PathVariable("module-id") Integer moduleId) {
        Integer deletedModuleId = moduleService.deleteById(moduleId);
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(deletedModuleId)
                        .build()
        );
    }
}
