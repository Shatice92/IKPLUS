package org.hatice.ikplus.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
	USER_NOT_FOUND(1001, "User not found", HttpStatus.NOT_FOUND),
	EMPLOYEE_NOT_FOUND(2001, "Employee not found", HttpStatus.NOT_FOUND),
	
	USERLIST_EMPTY(1002, "UserList is Empty", HttpStatus.NOT_FOUND),
	EMPLOYEELIST_EMPTY(2002, "EmployeeList is Empty", HttpStatus.NOT_FOUND),
	VALIDATION_ERROR(400,"Validation Errors, Please check validation rules",HttpStatus.BAD_REQUEST),
	INTERNAL_SERVER_ERROR(500,"Server Error, Try Again Later",HttpStatus.INTERNAL_SERVER_ERROR),
	EMPLOYEE_ALREADY_ACTIVE(2002, "Employee already active", HttpStatus.BAD_REQUEST ),
	EMPLOYEE_ALREADY_PASSIVE(2003, "Employee already passive", HttpStatus.BAD_REQUEST ),
	PASSWORD_MISMATCH(3000,"Girilen şifreler uyuşmamaktadır.",HttpStatus.BAD_REQUEST),
	INVALID_TOKEN(5000,"Invalid Token",HttpStatus.BAD_REQUEST ),
	INVALID_CREDENTIALS(1003, "Geçersiz email veya şifre.", HttpStatus.UNAUTHORIZED),
	
	
	
	LEAVE_NOT_FOUND(4001, "Leave not found", HttpStatus.NOT_FOUND),
	LEAVELIST_EMPTY(4002, "LeaveList is Empty", HttpStatus.NOT_FOUND),
	LEAVE_ALREADY_APPROVED(4003, "Leave already approved", HttpStatus.BAD_REQUEST),
	LEAVE_ALREADY_REJECTED(4004, "Leave already rejected", HttpStatus.BAD_REQUEST),
	
	
	
	PERMISSION_NOT_FOUND(5001, "Permission not found", HttpStatus.NOT_FOUND),

	
	LEAVE_TYPE_NOT_FOUND(3001, "Leave Type not found", HttpStatus.NOT_FOUND),
	LEAVE_TYPE_ALREADY_EXISTS(3002, "Leave type already exists", HttpStatus.BAD_REQUEST),
	INVALID_LEAVE_TYPE(3003, "Invalid leave type", HttpStatus.BAD_REQUEST),
	
	
	ASSET_NOT_FOUND(3001, "Asset not found", HttpStatus.NOT_FOUND),
	ASSETLIST_EMPTY(3002, "AssetList is Empty", HttpStatus.NOT_FOUND),
	ASSET_ALREADY_ACTIVE(3003, "Asset already active", HttpStatus.BAD_REQUEST),
	ASSET_ALREADY_INACTIVE(3004, "Asset already inactive", HttpStatus.BAD_REQUEST);
	
	
	
	
	int code;
	String message;
	HttpStatus httpStatus;
}