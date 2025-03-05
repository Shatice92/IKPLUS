package org.hatice.ikplus.controller.companycontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.request.companyrequest.CompanyRequestDto;
import org.hatice.ikplus.dto.request.companyrequest.RequestProcessor;
import org.hatice.ikplus.dto.response.BaseResponse;

import org.hatice.ikplus.dto.response.TokenInfo;
import org.hatice.ikplus.dto.response.companyresponse.CompanyResponse;
import org.hatice.ikplus.service.companyservice.CompanyService;
import org.hatice.ikplus.util.JwtManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


import static org.hatice.ikplus.constant.Endpoints.*;

	@CrossOrigin("*")
	@RestController
	@RequestMapping(Endpoints.ADMIN_COMPANIES)
	@RequiredArgsConstructor
	public class CompanyController {
		private final CompanyService companyService;
		private final JwtManager jwtManager;
		
		@PostMapping(Endpoints.SAVE)
		public ResponseEntity<BaseResponse<Boolean>> createCompany(
				@RequestHeader("Authorization") String authorizationHeader,
				@RequestBody CompanyRequestDto companyRequestDTO) {
			// Authorization header'ından token'ı al
			String token = authorizationHeader.replace("Bearer ", "");
			
			// Token'ı doğrula ve authId'yi al
			Optional<TokenInfo> tokenInfoOpt = jwtManager.validateToken(token);
			if (tokenInfoOpt.isEmpty()) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Geçersiz token
			}
			TokenInfo tokenInfo = tokenInfoOpt.get();
			// 3. Servis katmanına DTO'yu göndererek kaydetme işlemini başlat
			companyService.createCompany(companyRequestDTO, tokenInfo);
			
			return ResponseEntity.ok(
					BaseResponse.<Boolean>builder()
					            .data(true)
					            .message("Asset başarıyla oluşturuldu.")
					            .code(200)
					            .success(true)
					            .build()
			);
		}
		
		@PutMapping(APPROVE)
		public ResponseEntity<BaseResponse<CompanyResponse>> approveCompany(
				@RequestHeader("Authorization") String authorizationHeader,
				@PathVariable Long id) {
			return processRequest(authorizationHeader, () -> {
				CompanyResponse responseDTO = companyService.approveCompany(id);
				return ResponseEntity.ok(createSuccessResponse(responseDTO, "Şirket onaylandı."));
			});
		}
		
		@PutMapping(REJECT)
		public ResponseEntity<BaseResponse<CompanyResponse>> rejectCompany(
				@RequestHeader("Authorization") String authorizationHeader,
				@PathVariable Long id) {
			return processRequest(authorizationHeader, () -> {
				CompanyResponse responseDTO = companyService.rejectCompany(id);
				return ResponseEntity.ok(createSuccessResponse(responseDTO, "Şirket reddedildi."));
			});
		}
		
		@GetMapping(LIST)
		public ResponseEntity<BaseResponse<List<CompanyResponse>>> getAllCompanies(
				@RequestHeader("Authorization") String authorizationHeader) {
			return processRequest(authorizationHeader, () -> {
				List<CompanyResponse> companies = companyService.getAllCompanies();
				return ResponseEntity.ok(createSuccessResponse(companies, "Şirketler başarıyla getirildi."));
			});
		}
		
		@GetMapping(GETBYID)
		public ResponseEntity<BaseResponse<CompanyResponse>> getCompanyById(
				@RequestHeader("Authorization") String authorizationHeader,
				@PathVariable Long id) {
			return processRequest(authorizationHeader, () -> {
				CompanyResponse company = companyService.getCompanyById(id);
				return ResponseEntity.ok(createSuccessResponse(company, "Şirket başarıyla getirildi."));
			});
		}
		
		@DeleteMapping(DELETE)
		public ResponseEntity<BaseResponse<Boolean>> deleteCompany(
				@RequestHeader("Authorization") String authorizationHeader,
				@PathVariable Long id) {
			return processRequest(authorizationHeader, () -> {
				companyService.deleteCompany(id);
				return ResponseEntity.ok(createSuccessResponse(true, "Şirket başarıyla silindi."));
			});
		}
		
		
		
		private <T> ResponseEntity<BaseResponse<T>> processRequest(
				String authorizationHeader, RequestProcessor<T> processor) {
			String token = authorizationHeader.replace("Bearer ", "");
			Optional<TokenInfo> tokenInfoOpt = jwtManager.validateToken(token);
			if (tokenInfoOpt.isEmpty()) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN)
				                     .body((BaseResponse<T>) createErrorResponse("Yetkisiz erişim: Geçersiz token"));
			}
			return processor.process();
		}
		
		private <T> BaseResponse<T> createSuccessResponse(T data, String message) {
			return BaseResponse.<T>builder()
			                   .data(data)
			                   .message(message)
			                   .code(200)
			                   .success(true)
			                   .build();
		}
		
		private BaseResponse<Boolean> createErrorResponse(String message) {
			return BaseResponse.<Boolean>builder()
			                   .data(false)
			                   .message(message)
			                   .code(403)
			                   .success(false)
			                   .build();
		}
		

	
}