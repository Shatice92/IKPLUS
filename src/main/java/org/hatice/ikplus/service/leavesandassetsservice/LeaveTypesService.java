package org.hatice.ikplus.service.leavesandassetsservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.permissionrequest.AddLeaveTypeRequestDto;
import org.hatice.ikplus.dto.request.permissionrequest.UpdateLeaveTypeRequestDto;
import org.hatice.ikplus.dto.response.permissionresponse.LeaveTypeResponse;
import org.hatice.ikplus.entity.leaveandassetmanagement.LeaveTypes;
import org.hatice.ikplus.enums.leaveandassetenums.TypeLeaves;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.mapper.LeaveTypeMapper;
import org.hatice.ikplus.repository.leaveandassetrepository.LeaveTypesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaveTypesService {
	private final LeaveTypesRepository leaveTypeRepository;
	
	/**
	 * Yeni bir izin tipi oluşturur.
	 * Aynı isimde bir LeaveType varsa hata fırlatır.
	 */
	public LeaveTypeResponse createLeaveType(AddLeaveTypeRequestDto dto) {
		// Aynı isimde bir izin tipi olup olmadığını kontrol et
		Optional<LeaveTypes> existingLeaveType = leaveTypeRepository.findByLeavesName(dto.getLeavesName());
		if (existingLeaveType.isPresent()) {
			throw new IKPlusException(ErrorType.LEAVE_TYPE_ALREADY_EXISTS);
		}
		
		// Enum değerinin geçerli olup olmadığını kontrol et
		if (!isValidLeaveType(dto.getLeavesName())) {
			throw new IKPlusException(ErrorType.INVALID_LEAVE_TYPE);
		}
		
		LeaveTypes leaveType = LeaveTypeMapper.INSTANCE.fromAddLeaveTypeRequestDto(dto);
		LeaveTypes savedLeaveType = leaveTypeRepository.save(leaveType);
		return LeaveTypeMapper.INSTANCE.toLeaveTypeResponse(savedLeaveType);
	}
	
	/**
	 * Mevcut bir izin tipini günceller.
	 */
	public LeaveTypeResponse updateLeaveType(Long id, UpdateLeaveTypeRequestDto dto) {
		LeaveTypes existingLeaveType = leaveTypeRepository.findById(id)
		                                                  .orElseThrow(() -> new IKPlusException(ErrorType.LEAVE_TYPE_NOT_FOUND));
		
		LeaveTypeMapper.INSTANCE.updateLeaveTypeFromDto(dto, existingLeaveType);
		LeaveTypes updatedLeaveType = leaveTypeRepository.save(existingLeaveType);
		
		return LeaveTypeMapper.INSTANCE.toLeaveTypeResponse(updatedLeaveType);
	}
	
	/**
	 * ID'ye göre izin tipini döndürür.
	 */
	public LeaveTypeResponse getLeaveTypeById(Long id) {
		LeaveTypes leaveType = leaveTypeRepository.findById(id)
		                                          .orElseThrow(() -> new IKPlusException(ErrorType.LEAVE_TYPE_NOT_FOUND));
		
		return LeaveTypeMapper.INSTANCE.toLeaveTypeResponse(leaveType);
	}
	
	/**
	 * Tüm izin tiplerini döndürür.
	 */
	public List<LeaveTypeResponse> getAllLeaveTypes() {
		return leaveTypeRepository.findAll()
		                          .stream()
		                          .map(LeaveTypeMapper.INSTANCE::toLeaveTypeResponse)
		                          .collect(Collectors.toList());
	}
	
	/**
	 * İzin tipini siler (Soft Delete için isDeleted flag kullanılabilir).
	 */
	
	public void deleteLeaveType(Long id) {
		LeaveTypes leaveType = leaveTypeRepository.findById(id)
		                                          .orElseThrow(() -> new IKPlusException(ErrorType.LEAVE_TYPE_NOT_FOUND));
		
		leaveTypeRepository.delete(leaveType);
	}
	
	/**
	 * Enum değerinin geçerli olup olmadığını kontrol eder.
	 */
	private boolean isValidLeaveType(TypeLeaves leavesName) {
		for (TypeLeaves type : TypeLeaves.values()) {
			if (type == leavesName) {
				return true;
			}
		}
		return false;
	}
}