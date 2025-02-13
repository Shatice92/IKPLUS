package org.hatice.ikplus.controller.leavesandassetscontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.request.permissionrequest.AddLeaveTypeRequestDto;
import org.hatice.ikplus.dto.request.permissionrequest.UpdateLeaveTypeRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.permissionresponse.LeaveTypeResponse;
import org.hatice.ikplus.entity.leaveandassetmanagement.LeaveTypes;

import org.hatice.ikplus.service.leavesandassetsservice.LeaveTypesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hatice.ikplus.constant.Endpoints.*;

@CrossOrigin("*")
@RestController
@RequestMapping(Endpoints.LEAVETYPES)
@RequiredArgsConstructor
public class LeaveTypesController {
	private final LeaveTypesService leaveTypeService;
	
	@GetMapping(LIST)
	public ResponseEntity<BaseResponse<List<LeaveTypeResponse>>> getAllLeaveTypes() {
		return ResponseEntity.ok(BaseResponse.<List<LeaveTypeResponse>>builder()
		                                     .data(leaveTypeService.getAllLeaveTypes())
		                                     .message("All leave types retrieved successfully.")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	
}