package com.global.university.resource;
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
@RequestMapping("back-office/resources")
@RequiredArgsConstructor
public class ResoourcesControlller {

    private final ResourcesService departmentService;

    @GetMapping("")
    public ResponseEntity<Response<PageResponse<ResourcesResponse>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "99999") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<ResourcesResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(departmentService.findAll(page, size))
                        .build()
        );

    }

//    @PostMapping("")
//    public ResponseEntity<Response<Integer>> save(@RequestBody @Validated({Default.class}) ResourcesRequest request) {
//        return ResponseEntity.status(OK).body(
//                Response.<Integer>builder()
//                        .success(true)
//                        .status(OK.toString())
//                        .data(departmentService.save(request))
//                        .build()
//        );
//    }

//    @GetMapping("/{department-id}")
//    public ResponseEntity<Response<ResourcesResponse>> findById(@PathVariable("department-id") Integer departmentId) {
//        return ResponseEntity.status(OK).body(
//                Response.<ResourcesResponse>builder()
//                        .success(true)
//                        .status(OK.toString())
//                        .data(departmentService.findById(departmentId))
//                        .build()
//        );
//    }

//    @PostMapping("/update")
//    public ResponseEntity<Response<Integer>> update( @Validated({OnUpdate.class, Default.class}) @RequestBody ResourcesRequest request)  {
//        return ResponseEntity.status(OK).body(
//                Response.<Integer>builder()
//                        .success(true)
//                        .status(OK.toString())
//                        .data(departmentService.update(request,request.id()))
//                        .build()
//        );
//    }
//    @DeleteMapping("/{department-id}")
//    public ResponseEntity<Response<Integer>> deleteById(@PathVariable("department-id") Integer departmentId) {
//        Integer deletedResourcesId = departmentService.deleteById(departmentId);
//        return ResponseEntity.status(OK).body(
//                Response.<Integer>builder()
//                        .success(true)
//                        .status(OK.toString())
//                        .data(deletedResourcesId)
//                        .build()
//        );
//    }

}
