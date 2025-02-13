package org.hatice.ikplus.controller.leavesandassetscontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.permissionrequest.AddLeaveTypeRequestDto;
import org.hatice.ikplus.dto.request.permissionrequest.UpdateLeaveTypeRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.permissionresponse.LeaveTypeResponse;
import org.hatice.ikplus.entity.leaveandassetmanagement.LeaveTypes;

import org.hatice.ikplus.service.leavesandassetsservice.LeaveTypesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/leave-types")
@RequiredArgsConstructor
public class LeaveTypesController {
	private final LeaveTypesService leaveTypeService;
	
	@GetMapping
	public ResponseEntity<BaseResponse<List<LeaveTypeResponse>>> getAllLeaveTypes() {
		return ResponseEntity.ok(BaseResponse.<List<LeaveTypeResponse>>builder()
		                                     .data(leaveTypeService.getAllLeaveTypes())
		                                     .message("All leave types retrieved successfully.")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BaseResponse<LeaveTypeResponse>> getLeaveTypeById(@PathVariable Long id) {
		return ResponseEntity.ok(BaseResponse.<LeaveTypeResponse>builder()
		                                     .data(leaveTypeService.getLeaveTypeById(id))
		                                     .message("Leave type retrieved successfully.")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	@PostMapping
	public ResponseEntity<BaseResponse<Boolean>> createLeaveType(@RequestBody AddLeaveTypeRequestDto dto) {
		leaveTypeService.createLeaveType(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .data(true)
		                                     .message("Leave type created successfully.")
		                                     .code(201)
		                                     .success(true)
		                                     .build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BaseResponse<Boolean>> updateLeaveType(@PathVariable Long id, @RequestBody UpdateLeaveTypeRequestDto dto) {
		leaveTypeService.updateLeaveType(id, dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .data(true)
		                                     .message("Leave type updated successfully.")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<BaseResponse<Boolean>> deleteLeaveType(@PathVariable Long id) {
		leaveTypeService.deleteLeaveType(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .data(true)
		                                     .message("Leave type deleted successfully.")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
}