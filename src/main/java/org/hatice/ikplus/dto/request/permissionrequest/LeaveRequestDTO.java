package org.hatice.ikplus.dto.request.permissionrequest;


import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestDTO {
	private Long leaveTypeId;
	private LocalDate startDate;
	private LocalDate endDate;
}