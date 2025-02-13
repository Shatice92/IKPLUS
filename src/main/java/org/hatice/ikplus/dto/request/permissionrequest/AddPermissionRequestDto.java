package org.hatice.ikplus.dto.request.permissionrequest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.leaveandassetenums.LeaveStatus;
import org.hatice.ikplus.enums.leaveandassetenums.TypeLeaves;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPermissionRequestDto {
	private Long employeeId;
	private LeaveStatus status;
}