package org.hatice.ikplus.dto.request.permissionrequest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.leaveandassetenums.LeaveStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePermissionRequestDto {
	private Long id;
	private LeaveStatus status;
}