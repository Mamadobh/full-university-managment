
package com.global.university.role;

import com.global.university.response.PageResponse;
import com.global.university.response.Response;
import com.global.university.semester.SemesterRequest;
import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("back-office/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleServices roleService;



    @GetMapping("")
    public ResponseEntity<Response<PageResponse<RoleResponse>>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "99999") int size) {
        return ResponseEntity.status(OK).body(
                Response.<PageResponse<RoleResponse>>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(roleService.findAll(page, size))
                        .build()
        );

    }

    @PostMapping("")
    public ResponseEntity<Response<Integer>> save(@RequestBody @Validated({Default.class}) RoleRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(roleService.save(request))
                        .build()
        );
    }

    @GetMapping("/{role-id}")
    public ResponseEntity<Response<RoleResponse>> findById(@PathVariable("role-id") Integer roleId) {
        return ResponseEntity.status(OK).body(
                Response.<RoleResponse>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(roleService.findById(roleId))
                        .build()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<Response<Integer>> update(@Validated({OnUpdate.class, Default.class}) @RequestBody RoleRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(roleService.update(request, request.id()))
                        .build()
        );
    }

    @DeleteMapping("/{role-id}")
    public ResponseEntity<Response<Integer>> deleteById(@PathVariable("role-id") Integer roleId) {
        Integer deletedRoleId = roleService.deleteById(roleId);
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(deletedRoleId)
                        .build()
        );
    }
    @PostMapping("/add-role")
    public ResponseEntity<Response<Integer>> addRole(@RequestBody @Validated({Default.class}) ManagePermissionRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(roleService.addPermissionsToRole(request.roleId(), request.permissions()))
                        .build()
        );
    }

    @PostMapping("/remove-role")
    public ResponseEntity<Response<Integer>> removeRole(@RequestBody @Validated({Default.class}) ManagePermissionRequest request) {
        return ResponseEntity.status(OK).body(
                Response.<Integer>builder()
                        .success(true)
                        .status(OK.toString())
                        .data(roleService.removePermissionsRole(request.roleId(), request.permissions()))
                        .build()
        );
    }
}
