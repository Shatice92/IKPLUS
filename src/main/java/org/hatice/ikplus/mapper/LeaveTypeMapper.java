package org.hatice.ikplus.mapper;

import org.hatice.ikplus.dto.request.permissionrequest.AddLeaveTypeRequestDto;
import org.hatice.ikplus.dto.request.permissionrequest.UpdateLeaveTypeRequestDto;
import org.hatice.ikplus.dto.response.permissionresponse.LeaveTypeResponse;
import org.hatice.ikplus.entity.leaveandassetmanagement.LeaveTypes;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LeaveTypeMapper {
	LeaveTypeMapper INSTANCE = Mappers.getMapper(LeaveTypeMapper.class);
	
	LeaveTypes fromAddLeaveTypeRequestDto(AddLeaveTypeRequestDto dto);
	
	void updateLeaveTypeFromDto(UpdateLeaveTypeRequestDto dto, @MappingTarget LeaveTypes leaveType);
	
	LeaveTypeResponse toLeaveTypeResponse(LeaveTypes leaveType);
}