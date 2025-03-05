package org.hatice.ikplus.dto.request.companyrequest;

import org.hatice.ikplus.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;

@FunctionalInterface
public interface RequestProcessor<T> {
	ResponseEntity<BaseResponse<T>> process();
}