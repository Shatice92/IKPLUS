package org.hatice.ikplus.dto.request.leavesandassetrequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.leaveandassetenums.AssetStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAssetRequestDto {
	private Long employeeId;
	private Long companyManagerId;
	private String name;
	private String serialNumber;
	private LocalDateTime assignedDate;
	private String assetType;
	private AssetStatus status;
	private LocalDateTime dueDate;
}