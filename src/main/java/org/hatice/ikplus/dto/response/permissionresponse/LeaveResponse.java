package org.hatice.ikplus.dto.response.permissionresponse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.leaveandassetenums.LeaveStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaveResponse {
	private Long id;                   // Leave talebinin ID'si
	private Long employeeId;           // Çalışanın ID'si
	private Long leaveTypeId;          // İzin tipi ID'si
	private LocalDate startDate;       // Başlangıç tarihi
	private LocalDate endDate;         // Bitiş tarihi
	private Long approvedByUserId;     // Onaylayan kullanıcı ID'si
	private LocalDateTime createdAt;   // Oluşturulma tarihi
	private LocalDateTime updatedAt;   // Güncellenme tarihi
	private LeaveStatus status;        // İzin durumu (PENDING, APPROVED, REJECTED)
}