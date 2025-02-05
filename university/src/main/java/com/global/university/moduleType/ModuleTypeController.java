package com.global.university.moduleType;

import com.global.university.moduleType.ModuleTypeRequest;
import com.global.university.moduleType.ModuleTypeResponse;
import com.global.university.moduleType.ModuleTypeService;
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
@RequestMapping("back-office/module-types")
@RequiredArgsConstructor
public class ModuleTypeController {

    private final ModuleTypeService moduleTypeService;

    @GetMapping("")
    public ResponseEntity<Response<PageResponse<ModuleTypeResponse>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "999999") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<ModuleTypeResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(moduleTypeService.findAll(page, size))
                        .build()
        );

    }

    @PostMapping("")
    public ResponseEntity<Response<Integer>> save(@Validated({Default.class}) @RequestBody ModuleTypeRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(moduleTypeService.save(request))
                        .build()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<Response<Integer>> update(@Validated({OnUpdate.class, Default.class}) @RequestBody ModuleTypeRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(moduleTypeService.update(request, request.id()))
                        .build()
        );
    }

    @GetMapping("/{moduleType-id}")
    public ResponseEntity<Response<ModuleTypeResponse>> findById(@PathVariable("moduleType-id") Integer moduleTypeId) {
        return ResponseEntity.status(OK).body(
                Response.<ModuleTypeResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(moduleTypeService.findById(moduleTypeId))
                        .build()
        );
    }
    @DeleteMapping("/{moduleType-id}")
    public ResponseEntity<Response<Integer>> deleteById(@PathVariable("moduleType-id") Integer moduleTypeId) {
        Integer deletedModuleTypeId = moduleTypeService.deleteById(moduleTypeId);
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(deletedModuleTypeId)
                        .build()
        );
    }
}
